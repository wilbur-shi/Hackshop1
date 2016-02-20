package demo.android.com.menusandpreferencesdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextView userNameTextView;
    TextView servicesTextView;
    TextView notificationsTextView;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameTextView = (TextView) findViewById(R.id.username);
        servicesTextView = (TextView) findViewById(R.id.services);
        notificationsTextView = (TextView) findViewById(R.id.notifications);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        userNameTextView.setText(sharedPreferences.getString("username", "Error"));

        Set<String> servicesSet = sharedPreferences.getStringSet("services", null);
        if (servicesSet != null) {
            String[] servicesArray = servicesSet.toArray(new String[servicesSet.size()]);
            servicesTextView.setText(Arrays.toString(servicesArray));
        }
        notificationsTextView.setText(sharedPreferences.getBoolean("notifications", false) + "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.reload:
                userNameTextView.setText(sharedPreferences.getString("username", "Error"));
                Set<String> servicesSet = sharedPreferences.getStringSet("services", null);
                if (servicesSet != null) {
                    String[] servicesArray = servicesSet.toArray(new String[servicesSet.size()]);
                    servicesTextView.setText(Arrays.toString(servicesArray));
                }
                notificationsTextView.setText(sharedPreferences.getBoolean("notifications", false) + "");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
