
package se.ju.students.malu1798.ledcontrollerproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SelectModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_select);

        Button b_mode_strobe = findViewById(R.id.b_launch_strobe);
        Button b_mode_indled = findViewById(R.id.b_launch_individual_led_control);

        b_mode_strobe.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("Strobe mode button clicked");
                        Intent intent = new Intent(v.getContext(), StrobeActivity.class);
                        startActivity(intent);
                    }
                }
        );
        b_mode_indled.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("LED control mode button clicked");
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