package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import petrov.kristiyan.colorpicker.ColorPicker;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //Colors
    Colors colorsVar = new Colors();

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
                EditText eT_ip = findViewById(R.id.eT_ip);
                EditText eT_port = findViewById(R.id.eT_port);
                Intent intent = new Intent(v.getContext(), PickColorActivity.class);

                /*WORKING HERE*/
                if(eT_port.getText().toString() != null) {
                    System.out.print("DEBUG: " + eT_port.getText().toString() + " :END");
                    intent.putExtra("ip", eT_ip.getText().toString());
                    int i_port = Integer.parseInt(eT_port.getText().toString());
                    intent.putExtra("port", i_port);
                    //intent.putExtra("port", eT_port.getText());
                    System.out.println("ip: " + eT_ip.getText() + " port: " + eT_port.getText() + " " + i_port);
                }
                startActivity(intent);
            }
        });


        Button b_tcp = findViewById(R.id.b_tcp_view);
        b_tcp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("Change view button clicked");
                EditText eT_ip = findViewById(R.id.eT_ip);
                EditText eT_port = findViewById(R.id.eT_port);
                Intent intent = new Intent(v.getContext(), TcpActivity.class);

                /*WORKING HERE*/
                intent.putExtra("ip", eT_ip.getText().toString());
                int i_port = Integer.parseInt(eT_port.getText().toString());
                intent.putExtra("port", i_port);
                //intent.putExtra("port", eT_port.getText());
                System.out.println("ip: " + eT_ip.getText() + " port: " + eT_port.getText() + " " + i_port);
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

/*        Button b_sign_in = findViewById(R.id.b_sign_in);
        b_sign_in.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.out.println("Login Button clicked");
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });*/

    }

    /*
    @Override
    protected void onSaveInstanceState(Boundle outState){
        super.onSaveInstanceState(outState);
    }
    */

    //for setting color
    public void setColor() {

    }

    //for getting color
    public void getColor() {

    }

    private void openColorPicker() {
        String colorCode = "#258174";

        final ColorPicker cPicker = new ColorPicker(this);
        final ArrayList<String> arrayColorList = colorsVar.getColors();
        /*final ArrayList<String> colors = new ArrayList<>();
        colors.add("#258174");
        colors.add("#27AE60");
        colors.add("#3498DB");
        colors.add("#CB4335");
        colors.add("#34495E");
        colors.add("#F4D03F");*/

        cPicker.setColors(arrayColorList).setOnChooseColorListener(
                new ColorPicker.OnChooseColorListener() {

                    @Override
                    public void onChooseColor(int position, int color) {
                        if (position != -1) {
                            Log.d("COLOR", "color = " + position + " - " + color);
                            System.out.println("Position: " + position + " Color: " + color);
                            View v_background = findViewById(R.id.layout_main_id);
                            setColorWithHex(arrayColorList.get(position), v_background);
                        } else
                            return;
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
        int r = Integer.valueOf(colorStr.substring(1, 3), 16);
        int g = Integer.valueOf(colorStr.substring(3, 5), 16);
        int b = Integer.valueOf(colorStr.substring(5, 7), 16);

        v.setBackgroundColor(Color.rgb(r, g, b));

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
    //settings menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                /*handle*/
                return true;
            case R.id.action_profile:
                System.out.println("Profile menu button clicked");
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.swich_tilt:

                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    //if settings menu should show
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
