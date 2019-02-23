package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Intent;
import android.os.Bundle;
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
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intentIN = getIntent();
        int i = intentIN.getIntExtra("id", -1);

        //TextView t_heading = findViewById(R.id.t_m_s_title);

        //Data.Human currentData = Data.humans.get(i);
        //t_heading.setText(currentData.name);
        TextView t_p_title = findViewById(R.id.t_profile_tile);
        TextView t_p_amount = findViewById(R.id.t_profile_amount);
        int amount = Profile.profiles.size();
        t_p_amount.setText(Integer.toString(amount));

        ListView listView = (ListView) findViewById(R.id.listView_profile_dynamic);
        listView.setAdapter(
                new ArrayAdapter<Profile>(this, 0, Profile.profiles){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){

                        if(convertView == null){
                            ViewHolder viewHolder = new ViewHolder();
                            LayoutInflater inflater = LayoutInflater.from(getContext());
                            convertView = inflater.inflate(R.layout.list_view_item, parent, false);

                            //ViewHolder viewHolder = new ViewHolder();

                            viewHolder.idTextView = convertView.findViewById(R.id.t_listView_id);
                            viewHolder.nameTextView = convertView.findViewById(R.id.t_listView_name);
                            viewHolder.ageTextView = convertView.findViewById(R.id.t_listView_age);

                            convertView.setTag(viewHolder);
                        }

                        Profile profile = Profile.profiles.get(position);
                                //getItem(position);
                        ((ViewHolder) convertView.getTag()).idTextView.setText(""+ position);
                        ((ViewHolder) convertView.getTag()).nameTextView.setText("" + profile.getP_name());
                        ((ViewHolder) convertView.getTag()).ageTextView.setText(""+position);

                        return convertView;
                    }
                }
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("ListView clicked id: " + position);
                Data.Human clickedData = Data.humans.get(position);
                Intent intent = new Intent(ProfileActivity.this, ModeSettingsActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
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
