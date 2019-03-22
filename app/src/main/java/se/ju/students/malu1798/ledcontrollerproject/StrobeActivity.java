
package se.ju.students.malu1798.ledcontrollerproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import se.ju.students.malu1798.ledcontrollerproject.TcpPackage.TcpClient;


public class StrobeActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    Button b_strobe_sin;
    Button b_strobe_sqr;
    Button b_strobe_tri;
    Button b_strobe_saw;
    Switch strobeSwitch;
    SeekBar seekBar_frequency;
    SeekBar seekBar_amplitude;
    SeekBar seekBar_height;
    SeekBar seekBar_offset;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_strobe);

        b_strobe_sin = findViewById(R.id.b_strobeSin);
        b_strobe_sqr = findViewById(R.id.b_strobeSqr);
        b_strobe_tri = findViewById(R.id.b_strobeTri);
        b_strobe_saw = findViewById(R.id.b_strobeSaw);
        strobeSwitch = findViewById(R.id.strobeSwitch);
        seekBar_frequency = findViewById(R.id.seekBar_frequency);
        seekBar_amplitude = findViewById(R.id.seekBar_amplitude);
        seekBar_height = findViewById(R.id.seekBar_height);
        seekBar_offset = findViewById(R.id.seekBar_offset);

        seekBar_frequency.setOnSeekBarChangeListener(this);
        seekBar_amplitude.setOnSeekBarChangeListener(this);
        seekBar_height.setOnSeekBarChangeListener(this);
        seekBar_offset.setOnSeekBarChangeListener(this);


        strobeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    System.out.println("Strobe Switch On");
                } else {
                    System.out.println("Strobe Switch Off");
                }
            }
        });


        b_strobe_sin.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        System.out.println("Sine Button Clicked");
                        selectButton(1);
                    }
                }
        );
        b_strobe_sqr.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        System.out.println("Square Button Clicked");
                        selectButton(2);
                    }
                }
        );
        b_strobe_tri.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        System.out.println("Triangle Button Clicked");
                        selectButton(3);
                    }
                }
        );
        b_strobe_saw.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        System.out.println("Sawtooth Button Clicked");
                        selectButton(4);
                    }
                }
        );
    }


    private void selectButton(int button) {
        b_strobe_sin.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        b_strobe_sqr.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        b_strobe_tri.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        b_strobe_saw.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        switch (button) {
            case 1:
                b_strobe_sin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case 2:
                b_strobe_sqr.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case 3:
                b_strobe_tri.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case 4:
                b_strobe_saw.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            default:

                break;

        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.seekBar_frequency:
                System.out.println("--SeekBar onStop frequency");
                break;
            case R.id.seekBar_amplitude:
                System.out.println("--SeekBar onStop amplitude");
                break;
            case R.id.seekBar_height:
                System.out.println("--SeekBar onStop height");
                break;
            case R.id.seekBar_offset:
                System.out.println("--SeekBar onStop offset");
                break;
            default:
                System.out.println("--SeekBar onStop default");
                break;
        }
        //tcp send after every stop tracking of seek bars
        /*
        for (TcpClient client : clients) {
            try {
                client.sendMessage(sendColorMode());
            } catch (RuntimeException e) {
                Log.e("MESSAGE", "could not send message", e);
            }
        }
        */

    }

    /**
     * handle back arrow in app bar
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}