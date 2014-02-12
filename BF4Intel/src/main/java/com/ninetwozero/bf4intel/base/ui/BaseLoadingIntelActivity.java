package com.ninetwozero.bf4intel.base.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.utils.Result;

public abstract class BaseLoadingIntelActivity extends BaseIntelActivity implements LoaderManager.LoaderCallbacks<Result> {
    protected final Gson gson = GsonProvider.getInstance();

    @Override
    public abstract Loader<Result> onCreateLoader(int i, Bundle bundle);

    @Override
    public void onLoadFinished(Loader<Result> resultLoader, Result result) {
        if (result == Result.SUCCESS) {
            onLoadSuccess(resultLoader, result.getResultMessage());
        } else {
            onLoadFailure(resultLoader, result.getResultMessage());
        }
    }

    protected abstract void onLoadSuccess(final Loader<Result> resultLoader, final String resultMessage);

    public void onLoadFailure(final Loader loader, final String resultMessage) {
        Log.d(getClass().getSimpleName(), "[onLoadFailure] resultMessage => " + resultMessage);
    }

    @Override
    public void onLoaderReset(Loader<Result> resultLoader) {}

    public JsonObject extractFromJson(String json) {
        JsonParser parser = new JsonParser();
        return parser.parse(json).getAsJsonObject().getAsJsonObject("data");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.intel_activity, menu);
        optionsMenu = menu;
        return true;
    }

    protected void showLoadingStateInActionBar(boolean isLoading) {
        if (optionsMenu == null) {
            return;
        }

        final MenuItem refreshItem = optionsMenu.findItem(R.id.ab_action_refresh);
        if (refreshItem != null) {
            refreshItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            if (isLoading) {
                refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
            } else {
                refreshItem.setActionView(null);
            }
        }
    }
}