package com.ninetwozero.bf4intel.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingIntelActivity;
import com.ninetwozero.bf4intel.factories.BundleFactory;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.json.login.SoldierListingRequest;
import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.about.AppInfoActivity;
import com.ninetwozero.bf4intel.ui.activities.MainActivity;
import com.ninetwozero.bf4intel.ui.search.SearchActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;

import nl.qbusict.cupboard.DatabaseCompartment;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class LoginActivity extends BaseLoadingIntelActivity {
    public static final int REQUEST_PROFILE = 0;

    private static final String RESET_PASSWORD_LINK = "https://signin.ea.com/p/web/resetPassword";
    private static final int GAME_ID_BF4 = 2048;

    private Bundle profileBundle;
    private View loginStatusView;
    private TextView alertText;
    private EditText searchField;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayNetworkNotice(Bf4Intel.isConnectedToNetwork());
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SearchActivity.REQUEST_SEARCH && resultCode == Activity.RESULT_OK) {
            final Profile profile = (Profile) data.getSerializableExtra(SearchActivity.RESULT_SEARCH_RESULT);
            cupboard().withDatabase(getWritableDatabase()).put(profile);

            profileBundle = BundleFactory.createForProfile(profile);
            loadSoldiers(profileBundle);
        }
    }

    private void loadSoldiers(final Bundle bundle) {
        showLoadingOverlay(true);
        requestQueue.add(
            new SimpleGetRequest<SoldierListingRequest>(
                UrlFactory.buildSoldierListURL(bundle.getString(Keys.Profile.ID)),
                this
            ) {
                private int bf4SoldierCount;

                @Override
                protected SoldierListingRequest doParse(String json) {
                    final JsonObject baseObject = extractFromJson(json);
                    final SoldierListingRequest request = gson.fromJson(baseObject, SoldierListingRequest.class);

                    final SQLiteDatabase database = getWritableDatabase();
                    final DatabaseCompartment connection = cupboard().withDatabase(database);
                    database.beginTransaction();

                    for (SummarizedSoldierStats stats : request.getSoldiers()) {
                        if (stats.getGameId() == GAME_ID_BF4) {
                            if (bf4SoldierCount == 0) {
                                connection.delete(SummarizedSoldierStats.class, "userId = ?", stats.getUserId());
                            }
                            connection.put(stats);
                            bf4SoldierCount++;
                        }
                    }

                    database.setTransactionSuccessful();
                    database.endTransaction();
                    return request;
                }

                @Override
                protected void deliverResponse(SoldierListingRequest response) {
                    if (bf4SoldierCount > 0) {
                        storeFirstPersonaInPreferences(response.getSoldiers().get(0));
                        setResult(Activity.RESULT_OK, new Intent().putExtras(profileBundle));
                    } else {
                        setResult(Activity.RESULT_CANCELED);
                    }
                    finish();
                }

                private void storeFirstPersonaInPreferences(SummarizedSoldierStats soldierStats) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putLong(Keys.Menu.LATEST_PERSONA, soldierStats.getPersonaId());
                    editor.putInt(Keys.Menu.LATEST_PERSONA_POSITION, 0);
                    editor.commit();
                }
            }
        );
    }

    private void initialize() {
        setupFromPreExistingData();
        setupLayout();
    }

    private void setupFromPreExistingData() {
        setTitle(R.string.title_login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (SessionStore.isLoggedIn() && Bf4Intel.isConnectedToNetwork()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void setupLayout() {
        setupMenu();
        setupForm();
        setupButton();
    }

    private void setupMenu() {
        findViewById(R.id.button_menu).setOnClickListener(
            new OnClickListener() {
                @Override
                public void onClick(View view) {
                    final PopupMenu menu = new PopupMenu(LoginActivity.this, view);
                    menu.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                Intent intent = null;
                                if (item.getItemId() == R.id.menu_about) {
                                    intent = new Intent(LoginActivity.this, AppInfoActivity.class);
                                } else if (item.getItemId() == R.id.menu_reset_password) {
                                    intent = new Intent(Intent.ACTION_VIEW).setData(
                                        Uri.parse(RESET_PASSWORD_LINK)
                                    );
                                }

                                if (intent != null) {
                                    startActivity(intent);
                                }
                                return true;
                            }
                        }
                    );
                    menu.inflate(R.menu.activity_login);
                    menu.show();
                }
            }
        );
    }

    private void setupForm() {
        alertText = (TextView) findViewById(R.id.login_alert);
        loginStatusView = findViewById(R.id.login_status);
        searchField = (EditText) findViewById(R.id.search_term);

        searchField.setOnEditorActionListener(
            new EditText.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    if (id == EditorInfo.IME_ACTION_SEND) {
                        onFormSubmitted();
                        return true;
                    }
                    return false;
                }
            }
        );
    }

    private void setupButton() {
        findViewById(R.id.button_search_account).setOnClickListener(
            new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onFormSubmitted();
                }
            }
        );
    }

    private void onFormSubmitted() {
        final EditText searchField = (EditText) findViewById(R.id.search_term);
        final String searchTerm = searchField.getText().toString();
        if ("".equals(searchTerm) || searchTerm.length() < 3) {
            searchField.setError(getString(R.string.msg_search_error_length));
            return;
        }

        startActivityForResult(
            new Intent(LoginActivity.this, SearchActivity.class).putExtra(
                SearchActivity.QUERY,
                searchTerm
            ),
            SearchActivity.REQUEST_SEARCH
        );
    }

    private void displayNetworkNotice(final boolean isConnected) {
        alertText.setVisibility(isConnected ? View.GONE : View.VISIBLE);
        findViewById(R.id.button_search_account).setEnabled(isConnected);
    }

    private void showLoadingOverlay(final boolean show) {
        loginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
