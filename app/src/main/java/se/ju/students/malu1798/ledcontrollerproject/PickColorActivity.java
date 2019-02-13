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
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextClock;
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

        //Image Views
        View v_top = findViewById(R.id.layout_top);
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

        t_red.setBackgroundColor(Color.rgb(getR(), 0, 0));
        t_green.setBackgroundColor(Color.rgb(0, getG(), 0));
        t_blue.setBackgroundColor(Color.rgb(0, 0, getB()));
        v_top.setBackgroundColor(Color.rgb(getR(), getG(), getB()));

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
                setR(progress);
                v.setBackgroundColor(Color.rgb(colorConvertWithBrightness(getR()), colorConvertWithBrightness(getG()), colorConvertWithBrightness(getB())));
                v_r.setBackgroundColor(Color.rgb(colorConvertWithBrightness(getR()), 0, 0));
                break;
            case R.id.seekBar_g:
                System.out.println("--SeekBar onChange green");
                setG(progress);
                v.setBackgroundColor(Color.rgb(colorConvertWithBrightness(getR()), colorConvertWithBrightness(getG()), colorConvertWithBrightness(getB())));
                v_g.setBackgroundColor(Color.rgb(0, colorConvertWithBrightness(getG()), 0));
                break;
            case R.id.seekBar_b:
                System.out.println("--SeekBar onChange blue");
                setB(progress);
                v.setBackgroundColor(Color.rgb(colorConvertWithBrightness(getR()), colorConvertWithBrightness(getG()), colorConvertWithBrightness(getB())));
                v_b.setBackgroundColor(Color.rgb(0, 0, colorConvertWithBrightness(getB())));
                break;
            case R.id.seekBar_brightness:
                setBrightness(progress);
                //inverse
                //brightness = (brightness*(-1))+255;
                //setBrightness((progress*(-1))+255);


                v.setBackgroundColor(Color.rgb(colorConvertWithBrightness(getR()), colorConvertWithBrightness(getG()), colorConvertWithBrightness(getB())));
                v_r.setBackgroundColor(Color.rgb(colorConvertWithBrightness(getR()), 0, 0));
                v_g.setBackgroundColor(Color.rgb(0, colorConvertWithBrightness(getG()), 0));
                v_b.setBackgroundColor(Color.rgb(0, 0, colorConvertWithBrightness(getB())));
                System.out.println("--SeekBar onChange brightness " + getBrightness());


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


    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int colorConvertWithBrightness(int color) {
        return getValueSetByBrightness(color);
    }


    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getValueSetByBrightness(int in) {
        int out;
        //out = (int) ((in * 100 / (getBrightness() + 1))) / 100;
        out = (getBrightness() * in)/255;
        System.out.println("out Value brightness converter: " + out);
        return out;
    }

}
