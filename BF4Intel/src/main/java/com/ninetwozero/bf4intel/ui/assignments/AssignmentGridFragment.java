package com.ninetwozero.bf4intel.ui.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
import com.ninetwozero.bf4intel.dao.assignments.AssignmentsDAO;
import com.ninetwozero.bf4intel.events.assignments.AssignmentsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.json.assignments.Assignment;
import com.ninetwozero.bf4intel.json.assignments.SortedAssignmentContainer;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.AssignmentService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class AssignmentGridFragment
    extends BaseLoadingFragment
    implements AdapterView.OnItemClickListener {
    final Map<String, String> expansionNumberMapping = new HashMap<String, String>() {
        {
            put("xp0", "524288");
            put("xp1", "1048576");
            put("ghost1", "1048576");
            put("xp2", "2097152");
            put("ghost2", "2097152");
            put("xp3", "4194304");
            put("xp4", "8388608");
        }
    };

    private Map<String, List<Long>> expansionMap;

    public static AssignmentGridFragment newInstance(final Bundle data) {
        final AssignmentGridFragment fragment = new AssignmentGridFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_card_grid, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle arguments = getArgumentsBundle();
        Query.one(
            AssignmentsDAO.class,
            "SELECT * " +
            "FROM " + AssignmentsDAO.TABLE_NAME + " " +
            "WHERE soldierId = ? AND platformId = ? AND version = ?",
            arguments.getString(Keys.Soldier.ID, ""),
            arguments.getInt(Keys.Soldier.PLATFORM, 0),
            BuildConfig.VERSION_CODE
        ).getAsync(
            getLoaderManager(),
            new OneQuery.ResultHandler<AssignmentsDAO>() {
                @Override
                public boolean handleResult(AssignmentsDAO assignmentsDAO) {
                    if (assignmentsDAO == null) {
                        startLoadingData();
                        return true;
                    }

                    final SortedAssignmentContainer container = assignmentsDAO.getSortedAssignmentContainer();
                    expansionMap = container.getExpansions();
                    sendDataToGridView(view, container.getItems());
                    showLoadingState(false);
                    return true;
                }
            }
        );
    }

    @Override
    protected void startLoadingData() {
        if (isReloading || !Bf4Intel.isConnectedToNetwork()) {
            return;
        }

        showLoadingState(true);
        isReloading = true;

        final Intent intent = new Intent(getActivity(), AssignmentService.class);
        intent.putExtra(AssignmentService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        onRefreshEventReceived(event);
    }

    @Subscribe
    public void onAssignmentsRefreshed(AssignmentsRefreshedEvent event) {
        isReloading = false;
        showLoadingState(false);
    }

    private void initialize(View view) {
        setupErrorMessage(view);
        setupGrid(view);
    }

    private void setupGrid(final View view) {
        if (view == null) {
            return;
        }

        final GridView gridView = (GridView) view.findViewById(R.id.assignments_grid);
        gridView.setOnItemClickListener(this);
    }

    private void sendDataToGridView(final View view, List<Assignment> assignments) {
        final GridView gridView = (GridView) view.findViewById(R.id.assignments_grid);
        AssignmentsAdapter adapter = (AssignmentsAdapter) gridView.getAdapter();
        if (adapter == null) {
            adapter = new AssignmentsAdapter(getActivity());
            gridView.setAdapter(adapter);
        }
        adapter.setItems(assignments);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Assignment assignment = ((AssignmentsAdapter) adapterView.getAdapter()).getItem(i);
        final Bundle dataToPass = getArgumentsBundle();
        dataToPass.putSerializable(AssignmentDetailFragment.INTENT_ASSIGNMENT, assignment);
        dataToPass.putBoolean(
            AssignmentDetailFragment.INTENT_USER_HAS_EXPANSION, fetchExpansionStatus(assignment)
        );
        openDetailFragment(FragmentFactory.Type.SOLDIER_ASSIGNMENT_DETAILS, dataToPass, AssignmentDetailFragment.TAG);
    }

    private boolean fetchExpansionStatus(Assignment assignment) {
        final String key = expansionNumberMapping.get(assignment.getAward().getExpansionPack());
        if (expansionMap.containsKey(key)) {
            for (long value : expansionMap.get(key)) {
                if (value > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}