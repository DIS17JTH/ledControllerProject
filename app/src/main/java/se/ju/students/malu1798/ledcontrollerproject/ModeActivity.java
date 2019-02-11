package se.ju.students.malu1798.ledcontrollerproject;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        /*List View*/
        ListView listView = (ListView) findViewById(R.id.listView_mode_dynamic);
        //listView.setAdapter(new MyListAdapter());
        listView.setAdapter(
                new ArrayAdapter<Data.Human>(this, 0, Data.humans){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){

                        if(convertView == null){
                            LayoutInflater inflater = LayoutInflater.from(getContext());
                            convertView = inflater.inflate(R.layout.list_view_item, parent, true);

                            ViewHolder viewHolder = new ViewHolder();

                            viewHolder.idTextView = convertView.findViewById(R.id.t_listView_id);
                            viewHolder.idTextView = convertView.findViewById(R.id.t_listView_name);
                            viewHolder.idTextView = convertView.findViewById(R.id.t_listView_age);

                            convertView.setTag(viewHolder);
                        }

                        Data.Human human = getItem(position);
                        ((ViewHolder) convertView.getTag()).idTextView.setText(""+human.id);
                        ((ViewHolder) convertView.getTag()).nameTextView.setText(""+human.name);
                        ((ViewHolder) convertView.getTag()).ageTextView.setText(""+human.age);

                        return convertView;
                    }
                }
        );
    }

    public static class ViewHolder{
        public TextView idTextView;
        public TextView nameTextView;
        public TextView ageTextView;
    }

    @Override
    //if settings menu should show
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    //settings menu
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings:
                /*handle*/
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
