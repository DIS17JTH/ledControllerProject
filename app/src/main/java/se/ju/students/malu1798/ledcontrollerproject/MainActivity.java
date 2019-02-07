package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Button b_change_v = findViewById(R.id.b_change_view);
        b_change_v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("Button clicked");
                Intent intent = new Intent(v.getContext(), PickColorActivity.class);
                startActivity(intent);
            }
        });



    }

    /*
    @Override
    public void onClick(View view){
        //Button clicked
        System.out.println("Button change view clicked");
        Intent intent = new Intent(this, PickColorActivity.class);
        startActivity(intent);
    }
    */

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
