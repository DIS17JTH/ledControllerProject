package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import petrov.kristiyan.colorpicker.ColorPicker;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //Colors
    Colors colorsVar = new Colors();

    ArrayList<String> ipList = new ArrayList<>();
    ViewHolder viewHolder = new ViewHolder();

    ArrayAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ipList);

        listView = findViewById(R.id.list_view_wifiScanList);
        listView.setAdapter(adapter);

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

                String inIp = eT_ip.getText().toString();
                boolean exist = false;

                for (String ip : ipList) {
                    if (ip.equals(inIp))
                        exist = true;
                }
                if (!exist)
                    ipList.add(inIp);
                /*
                for(int i : pickedPosition){
                    ipList.add(getText(getPosition(i)));
                }
                */

                if (ipList != null)
                    intent.putStringArrayListExtra("ipList", ipList);
                //intent.putExtra("port", eT_port.getText());
                System.out.println("ip: " + eT_ip.getText() + " port: " + eT_port.getText() + " " + i_port);
                startActivity(intent);
            }
        });

        Button buttonScan = findViewById(R.id.wifiScanButton);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Search started!", Toast.LENGTH_SHORT).show();
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        //wifi scan
                        ledDevicesWifiScan(1,254, 8001);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update UI
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "Search complete!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });

        handleWifi();

        //listView
        this.addToViewHolder();

    }

    private void addToViewHolder() {

        //viewHolder.t_r = findViewById(R.id.t_r);
    }


    private void ledDevicesWifiScan(int start, int end, int port) {
        String wifi = getDeviceIP(false);
        for (int s = start; s <= end; s++) {
            String ip = wifi + s;
            if (isPortOpen(ip, port, 100)) {
                ipList.add(ip);
                //Toast.makeText(MainActivity.this, "Device found: " + ip, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isPortOpen(final String ip, final int port, final int timeout) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            socket.close();
            return true;
        } catch (ConnectException ce) {
            ce.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
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

    private void handleWifi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (!checkConnection(getApplicationContext())) {
                Log.i("WIFI", "wifi not connected");
                Toast.makeText(MainActivity.this, "WIFI not connected!", Toast.LENGTH_LONG).show();

                //dialog for wifi enable
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

            } else {
                Toast.makeText(MainActivity.this, "WIFI connected!", Toast.LENGTH_LONG).show();
                String ipString = getDeviceIP(false);

                EditText eT_ip = findViewById(R.id.eT_ip);
                eT_ip.setText(ipString);

                EditText eT_port = findViewById(R.id.eT_port);
                eT_port.setText("8001");
            }
        }
    }

    public String getDeviceIP(boolean wholeIP) {
        if (wholeIP) { //whole ip address
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ip = wifiInfo.getIpAddress();
            return String.format("%d.%d.%d.%d", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));
        } else { //ip without last digits
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ip = wifiInfo.getIpAddress();
            return String.format("%d.%d.%d.", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff));//, (ip >> 24 & 0xff)); --last digit
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static boolean checkConnection(@Nullable Context context) { //check if wifi is connected
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            Network network;
            for (int i = 0; i < networks.length; i++) {
                network = networks[i];
                networkInfo = connectivityManager.getNetworkInfo(network);
                if ((networkInfo.getType() == ConnectivityManager.TYPE_WIFI) && (networkInfo.getState().equals(NetworkInfo.State.CONNECTED))) {
                    ConnectivityManager.setProcessDefaultNetwork(network);
                    return true;
                }
            }
        }
        return false;
    }

    /*private void openColorPicker() {
        String colorCode = "#258174";

        final ColorPicker cPicker = new ColorPicker(this);
        final ArrayList<String> arrayColorList = colorsVar.getColors();
        *//*final ArrayList<String> colors = new ArrayList<>();
        colors.add("#258174");
        colors.add("#27AE60");
        colors.add("#3498DB");
        colors.add("#CB4335");
        colors.add("#34495E");
        colors.add("#F4D03F");*//*

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
*/
/*
    private void setColorWithHex(String hex, View v) {
        String colorStr = hex;
        int r = Integer.valueOf(colorStr.substring(1, 3), 16);
        int g = Integer.valueOf(colorStr.substring(3, 5), 16);
        int b = Integer.valueOf(colorStr.substring(5, 7), 16);

        v.setBackgroundColor(Color.rgb(r, g, b));

    }
*/

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
