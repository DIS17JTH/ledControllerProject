package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class PickColorActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener {
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
        b_mode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("Button mode clicked");
                Intent intent = new Intent(v.getContext(), ModeActivity.class);
                startActivity(intent);
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
        switch (seekBar.getId()) {
            case R.id.seekBar_r:
                System.out.println("--SeekBar onChange red " + progress + fromUser + seekBar);
                v.setBackgroundColor(Color.rgb(getColorTranslated(progress),0x43,0x36));
                break;
            case R.id.seekBar_g:
                System.out.println("--SeekBar onChange green");
                break;
            case R.id.seekBar_b:
                System.out.println("--SeekBar onChange blue");
                break;
            case R.id.seekBar_brightness:
                System.out.println("--SeekBar onChange brightness");
                break;
            default:
                System.out.println("--SeekBar onChange default");
                break;
        }
    }

    private int getColorTranslated(int progress) {
        double fullRange = progress*2.55;
        return (int)fullRange;
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
