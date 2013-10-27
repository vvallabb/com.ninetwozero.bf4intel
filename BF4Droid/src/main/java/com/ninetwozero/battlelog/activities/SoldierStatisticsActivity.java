package com.ninetwozero.battlelog.activities;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.factories.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

public class SoldierStatisticsActivity extends Activity implements ActionBar.TabListener {
    public static final String INTENT_ID = "soldierId";

    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.soldier_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {
        final FragmentManager manager = getFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.activity_root, mFragments.get(tab.getPosition()));
        transaction.commit();
    }

    @Override
    public void onTabUnselected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {
    }

    private void initialize() {
        setupFragments();
        setupActionBar();
    }

    private void setupFragments() {
        mFragments = new ArrayList<Fragment>();
        mFragments.add(FragmentFactory.get(FragmentFactory.Type.SOLDIER_STATS, getBundleForWeapons()));
        mFragments.add(FragmentFactory.get(FragmentFactory.Type.SOLDIER_STATS, getBundleForVehicles()));
        mFragments.add(FragmentFactory.get(FragmentFactory.Type.SOLDIER_STATS, getBundleForReports()));
    }

    private Bundle getBundleForWeapons() {
        final Bundle bundle = new Bundle();
        bundle.putString("TEST", "This is the weapons tab");
        return bundle;
    }

    private Bundle getBundleForVehicles() {
        final Bundle bundle = new Bundle();
        bundle.putString("TEST", "This is the vehicles tab");
        return bundle;
    }

    private Bundle getBundleForReports() {
        final Bundle bundle = new Bundle();
        bundle.putString("TEST", "This is the reports tab");
        return bundle;
    }

    private void setupActionBar() {
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(true);

        final int[] titles = new int[] { R.string.weapons, R.string.vehicles, R.string.reports };
        for (int i = 0; i < titles.length; i++) {
            actionBar.addTab(
                    actionBar.newTab().setText(titles[i]).setTabListener(this)
            );
        }
    }
}
