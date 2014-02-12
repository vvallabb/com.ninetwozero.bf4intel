package com.ninetwozero.bf4intel.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseListFragment;
import com.ninetwozero.bf4intel.interfaces.ListRowElement;
import com.ninetwozero.bf4intel.menu.ListRowType;
import com.ninetwozero.bf4intel.factories.ListRowFactory;
import com.ninetwozero.bf4intel.ui.adapters.NavigationDrawerListAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlatoonProfileFragment extends BaseListFragment {
    public PlatoonProfileFragment() {
    }


    public static PlatoonProfileFragment newInstance(final Bundle data) {
        final PlatoonProfileFragment fragment = new PlatoonProfileFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = this.layoutInflater.inflate(R.layout.fragment_platoon_profile, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        setListAdapter(new NavigationDrawerListAdapter(getActivity(), getItemsForMenu()));
        updateActionBar(getActivity(), "Chili-powered Zebras", R.drawable.test_platoon);
    }

    private List<ListRowElement> getItemsForMenu() {
        final List<ListRowElement> items = new ArrayList<ListRowElement>();
        items.add(ListRowFactory.create(ListRowType.HEADING, "BASIC INFORMATION"));
        items.add(ListRowFactory.create(ListRowType.PROFILE_SOLDIER, new Bundle()));
        items.add(ListRowFactory.create(ListRowType.HEADING, "PRESENTATION"));
        items.add(ListRowFactory.create(ListRowType.PROFILE_PLATOON, new Bundle()));
        return items;
    }
}
