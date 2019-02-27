package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorProfileActivity extends AppCompatActivity {
    //Colors
    Colors colorsVar = new Colors();

    private int currentMode = 0;
    ArrayList<Mode> modesArr = Profile.profiles.get(currentMode).getP_modes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add Profile", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //test code
        //Profile.profiles.get(0).getP_modes().add(new Mode("Test"));

        ListView listView = (ListView) findViewById(R.id.listView_colorP_dynamic);
        listView.setAdapter(
                new ArrayAdapter<Mode>(this, 0, modesArr){
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

                        Mode modes = getItem(position);
                        ((ViewHolder) convertView.getTag()).idTextView.setText(""+position);
                        ((ViewHolder) convertView.getTag()).nameTextView.setText(""+modes.get_modeName());
                        ((ViewHolder) convertView.getTag()).ageTextView.setText(""+modes.get_modeName());

                        return convertView;
                    }
                }
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("ListView clicked id: " + position);
                Data.Human clickedData = Data.humans.get(position);
                Intent intent = new Intent(ColorProfileActivity.this, ModeSettingsActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
