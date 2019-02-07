package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class PickColorActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
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

        seekB_brightness.setOnSeekBarChangeListener(new OnSeekBarChangeListener());
        seekB_red.setOnSeekBarChangeListener(new OnSeekBarChangeListener());
        seekB_green.setOnSeekBarChangeListener(new OnSeekBarChangeListener());
        seekB_blue.setOnSeekBarChangeListener(new OnSeekBarChangeListener());

    }

    private class OnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            System.out.println("SeekBar Changed");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            System.out.println("SeekBar Start Tracking Touch");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            System.out.println("SeekBar Stop Tracking Touch");
        }
    }


    public void modeButtonClicked(View view){
        System.out.println("Button clicked");
        Intent intent = new Intent(this, ModeActivity.class);
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
