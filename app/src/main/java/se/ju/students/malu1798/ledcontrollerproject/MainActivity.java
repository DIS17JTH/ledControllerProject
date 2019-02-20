package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import petrov.kristiyan.colorpicker.ColorPicker;

import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Button b_change_v = findViewById(R.id.b_change_view);
        b_change_v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("Change view button clicked");
                Intent intent = new Intent(v.getContext(), PickColorActivity.class);
                startActivity(intent);
            }
        });

        Button b_pickC = findViewById(R.id.b_pickColor);
        b_pickC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("pick color Button clicked");
                openColorPicker();

            }
        });

        Button b_sign_in = findViewById(R.id.b_sign_in);
        b_sign_in.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.out.println("Login Button clicked");
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    /*
    @Override
    protected void onSaveInstanceState(Boundle outState){
        super.onSaveInstanceState(outState);
    }
    */

    //for setting color
    public void setColor(){

    }
    //for getting color
    public void getColor(){

    }

    public void openColorPicker() {
        String colorCode = "#258174";

        final ColorPicker cPicker = new ColorPicker(this);
        final ArrayList<String> colors = new ArrayList<>();
        colors.add("#258174");
        colors.add("#27AE60");
        colors.add("#3498DB");
        colors.add("#CB4335");
        colors.add("#34495E");
        colors.add("#F4D03F");

        cPicker.setColors(colors).setOnChooseColorListener(
                        new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position, int color) {
                System.out.println("Position: " + position + " Color: " + color);
                View v_background = findViewById(R.id.layout_main_id);
                setColorWithHex(colors.get(position), v_background);
            }

            @Override
            public void onCancel() {

            }
        })
                .setColumns(5)
                .setRoundColorButton(true)
                .show();
    }

    private void setColorWithHex(String hex, View v) {
        String colorStr = hex;
        int r  = Integer.valueOf( colorStr.substring( 1, 3 ), 16 );
        int g  = Integer.valueOf( colorStr.substring( 3, 5 ), 16 );
        int b  = Integer.valueOf( colorStr.substring( 5, 7 ), 16 );

        v.setBackgroundColor(Color.rgb(r,g,b));

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
