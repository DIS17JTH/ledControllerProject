package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import petrov.kristiyan.colorpicker.ColorPicker;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PickColorActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener {

    ViewHolder viewHolder = new ViewHolder();


    SeekBar seekB_brightness;
    SeekBar seekB_red;
    SeekBar seekB_green;
    SeekBar seekB_blue;

    private int r = 255;
    private int g = 255;
    private int b = 255;
    private int brightness = 255;
    private Color color = new Color();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_color);

        //Image Views
        View v_top = findViewById(R.id.layout_top);

        //Color RGB layouts
        viewHolder.v_header = findViewById(R.id.layout_top);
        viewHolder.v_r = findViewById(R.id.t_r);
        viewHolder.v_g = findViewById(R.id.t_g);
        viewHolder.v_b = findViewById(R.id.t_b);

        //text views
        TextView t_red = findViewById(R.id.t_r);
        TextView t_green = findViewById(R.id.t_g);
        TextView t_blue = findViewById(R.id.t_b);
        //Seek bars
        seekB_brightness = (SeekBar) findViewById(R.id.seekBar_brightness);
        seekB_red = (SeekBar) findViewById(R.id.seekBar_r);
        seekB_green = (SeekBar) findViewById(R.id.seekBar_g);
        seekB_blue = (SeekBar) findViewById(R.id.seekBar_b);
        //Buttons
        Button b_mode = findViewById(R.id.b_mode);
        Button b_colorPicker = findViewById(R.id.b_colorPicker);
        Button b_profile = findViewById(R.id.b_profiles);

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

        b_profile.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        System.out.println("Button profile clicked");
                        Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                        startActivity(intent);
                    }
                }
        );


        /*
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //long running computation
                //final int result = compute();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //runs on thread that created the handler.

                    }
                });
            }
        }).start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //code to be executed in the new thread.

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        */

        seekB_brightness.setOnSeekBarChangeListener(this);
        seekB_red.setOnSeekBarChangeListener(this);
        seekB_green.setOnSeekBarChangeListener(this);
        seekB_blue.setOnSeekBarChangeListener(this);

        updateViewColors();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


        switch (seekBar.getId()) {
            case R.id.seekBar_r:
                System.out.println("--SeekBar onChange red " + progress + fromUser + seekBar);
                setR(progress);
                updateViewColors();
                break;
            case R.id.seekBar_g:
                System.out.println("--SeekBar onChange green");
                setG(progress);
                updateViewColors();
                break;
            case R.id.seekBar_b:
                System.out.println("--SeekBar onChange blue");
                setB(progress);
                updateViewColors();
                break;
            case R.id.seekBar_brightness:
                setBrightness(progress);
                //inverse
                //brightness = (brightness*(-1))+255;
                //setBrightness((progress*(-1))+255);
                updateViewColors();
                System.out.println("--SeekBar onChange brightness " + getBrightness());

                break;
            default:
                System.out.println("--SeekBar onChange default");
                break;
        }
    }

    private void updateViewColors(){
        viewHolder.v_header.setBackgroundColor(Color.rgb(colorConvertWithBrightness(getR()), colorConvertWithBrightness(getG()), colorConvertWithBrightness(getB())));
        viewHolder.v_r.setBackgroundColor(Color.rgb(colorConvertWithBrightness(getR()), 0, 0));
        viewHolder.v_g.setBackgroundColor(Color.rgb(0, colorConvertWithBrightness(getG()), 0));
        viewHolder.v_b.setBackgroundColor(Color.rgb(0, 0, colorConvertWithBrightness(getB())));
    }

    private void updateSeekBars(){
        seekB_red.setProgress(getR());
        seekB_green.setProgress(getG());
        seekB_blue.setProgress(getB());
        seekB_brightness.setProgress(getBrightness());
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
                        setColorWithHex(colors.get(position));
                        updateViewColors();
                        updateSeekBars();
                    }

                    @Override
                    public void onCancel() {
                        //??
                        //finish();
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

    /**
     * Converts a hex string to a color. If it can't be converted null is returned.
     * @param hex (i.e. #CCCCCCFF or CCCCCC)
     * @return Color
     */
    private void setColorWithHex(String hex) {
        String colorStr = hex;
        setR(Integer.valueOf( colorStr.substring( 1, 3 ), 16 ));
        setG(Integer.valueOf( colorStr.substring( 3, 5 ), 16 ));
        setB(Integer.valueOf( colorStr.substring( 5, 7 ), 16 ));
    }


}
