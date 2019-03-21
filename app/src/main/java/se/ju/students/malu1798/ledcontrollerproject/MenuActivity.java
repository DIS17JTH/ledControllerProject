package se.ju.students.malu1798.ledcontrollerproject;

import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

/*    @Override
    //settings menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                *//*handle*//*
                return true;
            case R.id.action_profile:

                return false;
            case R.id.swich_tilt:

                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
