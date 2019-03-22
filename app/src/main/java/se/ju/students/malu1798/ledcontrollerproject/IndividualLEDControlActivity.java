package se.ju.students.malu1798.ledcontrollerproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IndividualLEDControlActivity extends AppCompatActivity {

    boolean led1ischecked = true,
            led2ischecked = true,
            led3ischecked = true,
            led4ischecked = true,
            led5ischecked = true,
            led6ischecked = true,
            led7ischecked = true,
            led8ischecked = true,
            led9ischecked = true,
            led10ischecked = true; // Code written and given seal of quality by Karl Mannberg

    ImageButton b_LED1;
    ImageButton b_LED2;
    ImageButton b_LED3;
    ImageButton b_LED4;
    ImageButton b_LED5;
    ImageButton b_LED6;
    ImageButton b_LED7;
    ImageButton b_LED8;
    ImageButton b_LED9;
    ImageButton b_LED10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_led_control);



        b_LED1 = findViewById(R.id.b_led1);
        b_LED2 = findViewById(R.id.b_led2);
        b_LED3 = findViewById(R.id.b_led3);
        b_LED4 = findViewById(R.id.b_led4);
        b_LED5 = findViewById(R.id.b_led5);
        b_LED6 = findViewById(R.id.b_led6);
        b_LED7 = findViewById(R.id.b_led7);
        b_LED8 = findViewById(R.id.b_led8);
        b_LED9 = findViewById(R.id.b_led9);
        b_LED10 = findViewById(R.id.b_led10);

        b_LED1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        b_LED2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        b_LED3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        b_LED4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        b_LED5.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        b_LED6.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        b_LED7.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        b_LED8.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        b_LED9.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        b_LED10.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));


        b_LED1.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        buttonPressed(1);
                    }
                }
        );
        b_LED2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        buttonPressed(2);
                    }
                }
        );
        b_LED3.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        buttonPressed(3);
                    }
                }
        );
        b_LED4.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        buttonPressed(4);
                    }
                }
        );
        b_LED5.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        buttonPressed(5);
                    }
                }
        );
        b_LED6.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        buttonPressed(6);
                    }
                }
        );
        b_LED7.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        buttonPressed(7);
                    }
                }
        );
        b_LED8.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        buttonPressed(8);
                    }
                }
        );
        b_LED9.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        buttonPressed(9);
                    }
                }
        );
        b_LED10.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        buttonPressed(10);
                    }
                }
        );

    }

    private void buttonPressed(int button) {

        switch (button) {
            case 1:
                if (led1ischecked)
                    b_LED1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                else
                    b_LED1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                led1ischecked = !led1ischecked;
                break;
            case 2:
                if (led2ischecked)
                    b_LED2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                else
                    b_LED2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                led2ischecked = !led2ischecked;
                break;
            case 3:
                if (led3ischecked)
                    b_LED3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                else
                    b_LED3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                led3ischecked = !led3ischecked;
                break;
            case 4:
                if (led4ischecked)
                    b_LED4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                else
                    b_LED4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                led4ischecked = !led4ischecked;
                break;
            case 5:
                if (led5ischecked)
                    b_LED5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                else
                    b_LED5.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                led5ischecked = !led5ischecked;
                break;
            case 6:
                if (led6ischecked)
                    b_LED6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                else
                    b_LED6.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                led6ischecked = !led6ischecked;
                break;
            case 7:
                if (led7ischecked)
                    b_LED7.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                else
                    b_LED7.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                led7ischecked = !led7ischecked;
                break;
            case 8:
                if (led8ischecked)
                    b_LED8.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                else
                    b_LED8.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                led8ischecked = !led8ischecked;
                break;
            case 9:
                if (led9ischecked)
                    b_LED9.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                else
                    b_LED9.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                led9ischecked = !led9ischecked;
                break;
            case 10:
                if (led10ischecked)
                    b_LED10.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                else
                    b_LED10.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                led10ischecked = !led10ischecked;
                break;
        }


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
