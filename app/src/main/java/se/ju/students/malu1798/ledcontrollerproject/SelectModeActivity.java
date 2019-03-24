
package se.ju.students.malu1798.ledcontrollerproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SelectModeActivity extends AppCompatActivity {
    Switch audioSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_select);

        Button b_mode_strobe = findViewById(R.id.b_launch_strobe);
        Button b_mode_indled = findViewById(R.id.b_launch_individual_led_control);
        audioSwitch = findViewById(R.id.b_audio_sync);

        audioSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Clients.setControlSetting(ControlLedEnum.AUDIO_SYNC, 1);
                } else {
                    Clients.setControlSetting(ControlLedEnum.AUDIO_SYNC, 0);
                }
            }
        });

        b_mode_strobe.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), StrobeActivity.class);
                        startActivity(intent);
                    }
                }
        );
        b_mode_indled.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), IndividualLEDControlActivity.class);
                        startActivity(intent);
                    }
                }
        );

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