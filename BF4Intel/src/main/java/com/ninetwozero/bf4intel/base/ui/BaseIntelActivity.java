package com.ninetwozero.bf4intel.base.ui;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.database.CupboardSQLiteOpenHelper;
import com.ninetwozero.bf4intel.resources.Keys;

public abstract class BaseIntelActivity extends FragmentActivity {
    protected Menu optionsMenu;
    protected SharedPreferences sharedPreferences;
    private Toast toast;
    protected CupboardSQLiteOpenHelper cupboardHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        reloadSession();
    }

    public void showToast(final String message) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToast(final int stringResource) {
        showToast(getString(stringResource));
    }

    private void reloadSession() {
        SessionStore.load(
            sharedPreferences.getString(Keys.SESSION_ID, null),
            sharedPreferences.getString(Keys.Profile.ID, null),
            sharedPreferences.getString(Keys.Profile.USERNAME, null),
            sharedPreferences.getString(Keys.Profile.GRAVATAR_HASH, null)
        );
    }

    public SQLiteDatabase getWriteableDatabase() {
        if (cupboardHelper == null) {
            cupboardHelper = new CupboardSQLiteOpenHelper(this);
        }
        return cupboardHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase() {
        if (cupboardHelper == null) {
            cupboardHelper = new CupboardSQLiteOpenHelper(this);
        }
        return cupboardHelper.getReadableDatabase();
    }
}
