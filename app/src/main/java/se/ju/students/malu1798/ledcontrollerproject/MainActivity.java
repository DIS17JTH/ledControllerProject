package se.ju.students.malu1798.ledcontrollerproject;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import petrov.kristiyan.colorpicker.ColorPicker;

import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.net.InetAddress;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //Colors
    Colors colorsVar = new Colors();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Button b_change_v = findViewById(R.id.b_change_view);
        b_change_v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("Change view button clicked");
                EditText eT_ip = findViewById(R.id.eT_ip);
                EditText eT_port = findViewById(R.id.eT_port);
                Intent intent = new Intent(v.getContext(), PickColorActivity.class);

                /*WORKING HERE*/
                intent.putExtra("ip", eT_ip.getText().toString());
                int i_port = Integer.parseInt(eT_port.getText().toString());
                intent.putExtra("port", i_port);
                //intent.putCharSequenceArrayListExtra()
                //intent.putExtra("port", eT_port.getText());
                System.out.println("ip: " + eT_ip.getText() + " port: " + eT_port.getText() + " " + i_port);
                startActivity(intent);
            }
        });


        Button b_tcp = findViewById(R.id.b_tcp_view);
        b_tcp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("Change view button clicked");
                EditText eT_ip = findViewById(R.id.eT_ip);
                EditText eT_port = findViewById(R.id.eT_port);
                Intent intent = new Intent(v.getContext(), TcpActivity.class);

                /*WORKING HERE*/
                intent.putExtra("ip", eT_ip.getText().toString());
                int i_port = Integer.parseInt(eT_port.getText().toString());
                intent.putExtra("port", i_port);
                //intent.putExtra("port", eT_port.getText());
                System.out.println("ip: " + eT_ip.getText() + " port: " + eT_port.getText() + " " + i_port);
                startActivity(intent);
            }
        });

        Button b_pickC = findViewById(R.id.b_pickColor);
        b_pickC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                System.out.println("pick color Button clicked");
                openColorPicker();

            }
        });

/*        Button b_sign_in = findViewById(R.id.b_sign_in);
        b_sign_in.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.out.println("Login Button clicked");
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });*/

        handleWifi();

    }

    @Override
    protected void onResume() {
        super.onResume();
        handleWifi();

    }

    /*
    @Override
    protected void onSaveInstanceState(Boundle outState){
        super.onSaveInstanceState(outState);
    }
    */

    private void handleWifi(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (!checkConnection(getApplicationContext())){
                Log.i("WIFI", "wifi not connected");
                Toast.makeText(MainActivity.this, "WIFI not connected!", Toast.LENGTH_LONG).show();

                //TODO::make popup dialog instead of start activity directly
/*
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                // Set other dialog properties

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
*/

                new AlertDialog.Builder(this)
                        .setTitle("Enable Wifi")
                        .setMessage("You need Wifi connection to communicate with LED strip")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(getString(android.R.string.cancel), null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }else {
                Toast.makeText(MainActivity.this, "WIFI connected!", Toast.LENGTH_LONG).show();
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                int ip = wifiInfo.getIpAddress();
                String ipString = String.format("%d.%d.%d.", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff));//, (ip >> 24 & 0xff)); --last digit

                EditText eT_ip = findViewById(R.id.eT_ip);
                eT_ip.setText(ipString);

                EditText eT_port = findViewById(R.id.eT_port);
                eT_port.setText("8001");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static boolean checkConnection(@Nullable Context context) { //check if wifi is connected
        if(context != null){
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            Network network;
            for (int i = 0; i < networks.length; i++){
                network = networks[i];
                networkInfo = connectivityManager.getNetworkInfo(network);
                if ((networkInfo.getType() ==     ConnectivityManager.TYPE_WIFI) && (networkInfo.getState().equals(NetworkInfo.State.CONNECTED))) {
                    ConnectivityManager.setProcessDefaultNetwork(network);
                    return true;
                }
            }
        }
        return false;
    }

    private void openColorPicker() {
        String colorCode = "#258174";

        final ColorPicker cPicker = new ColorPicker(this);
        final ArrayList<String> arrayColorList = colorsVar.getColors();
        /*final ArrayList<String> colors = new ArrayList<>();
        colors.add("#258174");
        colors.add("#27AE60");
        colors.add("#3498DB");
        colors.add("#CB4335");
        colors.add("#34495E");
        colors.add("#F4D03F");*/

        cPicker.setColors(arrayColorList).setOnChooseColorListener(
                new ColorPicker.OnChooseColorListener() {

                    @Override
                    public void onChooseColor(int position, int color) {
                        if (position != -1) {
                            Log.d("COLOR", "color = " + position + " - " + color);
                            System.out.println("Position: " + position + " Color: " + color);
                            View v_background = findViewById(R.id.layout_main_id);
                            setColorWithHex(arrayColorList.get(position), v_background);
                        } else
                            return;
                    }

                    @Override
                    public void onCancel() {

                    }
                })
                .setColumns(5)
                .setRoundColorButton(true)
                .show();
    }

    private void setColorWithHex(String hex, View v) {
        String colorStr = hex;
        int r = Integer.valueOf(colorStr.substring(1, 3), 16);
        int g = Integer.valueOf(colorStr.substring(3, 5), 16);
        int b = Integer.valueOf(colorStr.substring(5, 7), 16);

        v.setBackgroundColor(Color.rgb(r, g, b));

    }

    /*
    @Override
    public void onClick(View view){
        //Button clicked
        System.out.println("Button change view clicked");
        Intent intent = new Intent(this, PickColorActivity.class);
        startActivity(intent);
    }
    */

    @Override
    //settings menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                /*handle*/
                return true;
            case R.id.action_profile:
                System.out.println("Profile menu button clicked");
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.swich_tilt:

                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    //if settings menu should show
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
