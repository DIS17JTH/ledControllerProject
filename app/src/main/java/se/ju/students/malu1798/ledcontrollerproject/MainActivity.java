package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeViewButtonClicked(View view){
        System.out.println("Button clicked");
        Intent intent = new Intent(this, PickColorActivity.class);
        startActivity(intent);
    }

}
