package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ModeSettingsActivity extends AppCompatActivity {
    private int currentProfile = 0;
    Profile profile = Profile.getProfiles().get(currentProfile);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_settings);

        Intent intentIN = getIntent();
        final int i = intentIN.getIntExtra("id", -1);

        TextView t_heading = findViewById(R.id.t_mS_title);
        TextView t_description = findViewById(R.id.t_mS_description);

        //Data.Human currentData = Data.humans.get(i);
        final Mode m = profile.getP_modes().get(i);
        t_heading.setText(m.get_modeName());
        t_description.setText(m.get_description());

        ArrayList<Setting> settingsArr = m.getMode_settings();

        ListView listView = (ListView) findViewById(R.id.listView_modeS_dynamic);
        listView.setAdapter(
                new ArrayAdapter<Setting>(this, 0, settingsArr) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        if (convertView == null) {
                            ViewHolder viewHolder = new ViewHolder();
                            LayoutInflater inflater = LayoutInflater.from(getContext());
                            convertView = inflater.inflate(R.layout.list_view_item, parent, false);

                            //ViewHolder viewHolder = new ViewHolder();

                            viewHolder.idTextView = convertView.findViewById(R.id.t_listView_id);
                            viewHolder.nameTextView = convertView.findViewById(R.id.t_listView_name);
                            viewHolder.ageTextView = convertView.findViewById(R.id.t_listView_age);

                            convertView.setTag(viewHolder);
                        }

                        //Mode mode = Profile.getProfiles().get(0).getP_modes().get(position);
                        //Mode modes = getItem(position);

                        Setting s = m.getMode_settings().get(position);
                        ((ViewHolder) convertView.getTag()).idTextView.setText("" + position);
                        ((ViewHolder) convertView.getTag()).nameTextView.setText("" + s.getS_name());
                        ((ViewHolder) convertView.getTag()).ageTextView.setText("" + s.getS_description());//+modes.get_modeName());

                        return convertView;
                    }
                }
        );
    }

    @Override
    //if settings menu should show
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    //settings menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                /*handle*/
                return true;
            case R.id.action_profile:

                return false;
            case R.id.swich_tilt:

                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
