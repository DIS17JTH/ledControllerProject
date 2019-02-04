package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGHT_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        /*
        ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
        Button b = new Button(this);
        b.setText("Change View");
        b.setOnClickListener(this);

        rootView.addView(b);
        */
        //acticivity_main.findViewById(R.id.b_change_view);

        //String s = aContext.getString(R.string.app_name);

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

    public void changeViewButtonClicked(View view){
        System.out.println("Button clicked");
        Intent intent = new Intent(this, PickColorActivity.class);
        startActivity(intent);
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
