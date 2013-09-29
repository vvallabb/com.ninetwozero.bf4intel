package com.ninetwozero.battlelog.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.abstractions.AbstractFragment;
import com.ninetwozero.battlelog.activities.SingleFragmentActivity;

public class ThreadCreationFragment extends AbstractFragment {
    public static final String FORUM_ID = "forumId";

    public ThreadCreationFragment() {
    }

    public static ThreadCreationFragment newInstance() {
        final ThreadCreationFragment fragment = new ThreadCreationFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = mInflater.inflate(R.layout.fragment_thread_creation, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_posting, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new) {
            doCreateThread();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize(final View view) {
        updateActionBar(getActivity(), "CREATE THREAD", R.drawable.ic_actionbar_forums);
    }

    private void doCreateThread() {
        final View root = getView();
        final EditText inputTitle = (EditText) root.findViewById(R.id.input_title);
        final EditText inputContent = (EditText) root.findViewById(R.id.input_content);

        final String title = inputTitle.getText().toString();
        final String content = inputContent.getText().toString();

        if ("".equals(title)) {
            inputTitle.setError("You need to enter a title!");
            return;
        }

        if ("".equals(content)) {
            inputContent.setError("You need to enter some content!");
            return;
        }

        showToast("TODO: Trigger API call (POST)");

        final Activity activity = getActivity();
        if (activity == null || !(activity instanceof SingleFragmentActivity)) {
            mFragmentManager.popBackStackImmediate();
        } else {
            activity.finish();
        }
    }
}
