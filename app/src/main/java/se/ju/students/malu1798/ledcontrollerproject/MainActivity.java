package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import petrov.kristiyan.colorpicker.ColorPicker;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
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
                System.out.println("Button clicked");
                Intent intent = new Intent(v.getContext(), PickColorActivity.class);
                startActivity(intent);
            }
        });

        Button b_pickC = findViewById(R.id.b_pickColor);
        b_pickC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("Button clicked");
                openColorPicker();

            }
        });



    }

    public void openColorPicker() {
        final ColorPicker cPicker = new ColorPicker(this);
        ArrayList<String> colors = new ArrayList<>();
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
            }

            @Override
            public void onCancel() {

            }
        })
                .setColumns(6)
                .setRoundColorButton(true)
                .show();

        /*
        final ColorPicker colorPicker = new ColorPicker(SampleActivity.this);
        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position, int color) {
                // put code
            }

            @Override
            public void onCancel() {
                // put code
            }
        })
                .addListenerButton("newButton", new ColorPicker.OnButtonListener() {
                    @Override
                    public void onClick(View v, int position, int color) {
                        // put code
                    }
                })
                .disableDefaultButtons(true)
                .setDefaultColor(Color.parseColor("#f84c44"))
                .setColumns(5)
                .setDialogFullHeight()
                .show();
        */

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
