package se.ju.students.malu1798.ledcontrollerproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ModeActivity extends AppCompatActivity {

    private int currentProfile = 0;
    ArrayList<Mode> modesArr = Profile.profiles.get(currentProfile).getP_modes();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        TextView t = findViewById(R.id.t_mode_tile);

        /*List View*/
        ListView listView = (ListView) findViewById(R.id.listView_mode_dynamic);
        listView.setAdapter(
                new ArrayAdapter<Mode>(this, 0, modesArr) {
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

                        Mode mode = Profile.getProfiles().get(currentProfile).getP_modes().get(position);
                        //Mode modes = getItem(position);
                        ((ViewHolder) convertView.getTag()).idTextView.setText("" + position);
                        ((ViewHolder) convertView.getTag()).nameTextView.setText("" + mode.get_modeName());
                        ((ViewHolder) convertView.getTag()).ageTextView.setText("" + mode.get_description());//+modes.get_modeName());

                        return convertView;
                    }
                }
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //System.out.println("ListView clicked id: " + position);
                //Intent intent = new Intent(ModeActivity.this, ModeSettingsActivity.class);
                //intent.putExtra("id", position);
                //startActivity(intent);
            }
        });

    }


    @Override
    //if settings menu should show
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


/*
    @Override
    //settings menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                */
/*handle*//*

                return true;
            case R.id.action_profile:

                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}
