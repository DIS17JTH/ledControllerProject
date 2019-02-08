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
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PickColorActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener {
    private int r = 255;
    private int g = 255;
    private int b = 255;
    private int brightness = 255;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_color);

        //text views
        TextView t_red = findViewById(R.id.t_r);
        TextView t_green = findViewById(R.id.t_g);
        TextView t_blue = findViewById(R.id.t_b);
        //Seek bars
        SeekBar seekB_brightness = (SeekBar) findViewById(R.id.seekBar_brightness);
        SeekBar seekB_red = (SeekBar) findViewById(R.id.seekBar_r);
        SeekBar seekB_green = (SeekBar) findViewById(R.id.seekBar_g);
        SeekBar seekB_blue = (SeekBar) findViewById(R.id.seekBar_b);
        //Buttons
        Button b_mode = findViewById(R.id.b_mode);
        Button b_colorPicker = findViewById(R.id.b_colorPicker);

        b_mode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("Button mode clicked");
                Intent intent = new Intent(v.getContext(), ModeActivity.class);
                startActivity(intent);
            }
        });

        b_colorPicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("Button colorPicker clicked");
                openColorPicker();
            }
        });

        seekB_brightness.setOnSeekBarChangeListener(this);
        seekB_red.setOnSeekBarChangeListener(this);
        seekB_green.setOnSeekBarChangeListener(this);
        seekB_blue.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        View v = findViewById(R.id.layout_top);
        View v_r = findViewById(R.id.t_r);
        View v_g = findViewById(R.id.t_g);
        View v_b = findViewById(R.id.t_b);

        switch (seekBar.getId()) {
            case R.id.seekBar_r:
                System.out.println("--SeekBar onChange red " + progress + fromUser + seekBar);
                r = progress;
                v.setBackgroundColor(Color.argb(brightness, r, g, b));
                v_r.setBackgroundColor(Color.argb(brightness, r, 0, 0));
                break;
            case R.id.seekBar_g:
                System.out.println("--SeekBar onChange green");
                g = progress;
                v.setBackgroundColor(Color.argb(brightness, r, g, b));
                v_g.setBackgroundColor(Color.argb(brightness, 0, g, 0));
                break;
            case R.id.seekBar_b:
                System.out.println("--SeekBar onChange blue");
                b = progress;
                v.setBackgroundColor(Color.argb(brightness, r, g, b));
                v_b.setBackgroundColor(Color.argb(brightness, 0, 0, b));
                break;
            case R.id.seekBar_brightness:
                brightness = progress;
                v.setBackgroundColor(Color.argb(brightness, r, g, b));
                v_r.setBackgroundColor(Color.argb(brightness, r, 0, 0));
                v_g.setBackgroundColor(Color.argb(brightness, 0, g, 0));
                v_b.setBackgroundColor(Color.argb(brightness, 0, 0, b));
                System.out.println("--SeekBar onChange brightness " + progress);
                break;
            default:
                System.out.println("--SeekBar onChange default");
                break;
        }
    }

    public void openColorPicker() {
        String colorCode = "#258174";

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
                .setColumns(5)
                .setRoundColorButton(true)
                .show();
    }

    private int getColorFullRange(int procentage) {
        double fullRange = procentage * 2.55;
        return (int) fullRange;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.seekBar_r:
                System.out.println("--SeekBar onStart red");
                break;
            case R.id.seekBar_g:
                System.out.println("--SeekBar onStart green");
                break;
            case R.id.seekBar_b:
                System.out.println("--SeekBar onStart blue");
                break;
            case R.id.seekBar_brightness:
                System.out.println("--SeekBar onStart brightness");
                break;
            default:
                System.out.println("--SeekBar onStart default");
                break;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.seekBar_r:
                System.out.println("--SeekBar onStop red");
                break;
            case R.id.seekBar_g:
                System.out.println("--SeekBar onStop green");
                break;
            case R.id.seekBar_b:
                System.out.println("--SeekBar onStop blue");
                break;
            case R.id.seekBar_brightness:
                System.out.println("--SeekBar onStop brightness");
                break;
            default:
                System.out.println("--SeekBar onStop default");
                break;
        }
    }

    public void modeButtonClicked(View view) {
        System.out.println("Button clicked");
        Intent intent = new Intent(this, ModeActivity.class);
        startActivity(intent);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
