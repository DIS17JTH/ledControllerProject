package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<NetworkDevice> deviceList = new ArrayList<>();
    ViewHolder viewHolder = new ViewHolder();

    ArrayAdapter adapter;
    ListView listView;
    Button buttonScan;

    EditText eT_ip;
    EditText eT_port;

    int startIP = 1;
    int endIP = 254;
    int port = 8001;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializationOfViews();

        adapter = new ArrayAdapter<NetworkDevice>(this, 0, deviceList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    ViewHolder viewHolder = new ViewHolder();
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.list_view_layout_ip, parent, false);

                    //ViewHolder viewHolder = new ViewHolder();

                    viewHolder.ipTextView = convertView.findViewById(R.id.t_list_view_ip);
                    viewHolder.ipCheckBox = convertView.findViewById(R.id.chB_list_view_ip);

                    convertView.setTag(viewHolder);
                }

                String s = getItem(position).getIp();//getItem(position);
                Boolean b = getItem(position).getIsChecked();
                //Mode modes = getItem(position);
                ((ViewHolder) convertView.getTag()).ipTextView.setText("" + s);
                ((ViewHolder) convertView.getTag()).ipCheckBox.setChecked(b);

                return convertView;
            }
        };

        ListView listView = (ListView) findViewById(R.id.list_view_wifiScanList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("ListView clicked id: " + position);
                NetworkDevice nd = deviceList.get(position);
                boolean isChecked = nd.getIsChecked();
                nd.setIsChecked(!isChecked);
                adapter.notifyDataSetChanged();
            }
        });

        Button b_change_v = findViewById(R.id.b_change_view);
        b_change_v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PickColorActivity.class);

                port = Integer.parseInt(eT_port.getText().toString());
                intent.putExtra("port", port);

                String inIp = eT_ip.getText().toString();
                ArrayList<String> ipAddresses = null;
                //ADD devices
                if (deviceList != null) {
                    ipAddresses = new ArrayList<>();
                    boolean someoneChecked = false;
                    for (NetworkDevice nd : deviceList) {
                        if (nd.getIsChecked()) {
                            ipAddresses.add(nd.getIp());
                            someoneChecked = true;
                        }
                    }
                    if (deviceList.size() == 0 || !someoneChecked) { //add ip from edit text view
                        deviceList.add(new NetworkDevice(inIp));
                        ipAddresses.add(inIp);
                    }
                }


                intent.putStringArrayListExtra("networkDevices", ipAddresses);
                //intent.putExtra("port", eT_port.getText());
                //System.out.println("ip: " + eT_ip.getText() + " port: " + eT_port.getText() + " " + i_port);
                startActivity(intent);
            }
        });

        buttonScan = findViewById(R.id.wifiScanButton);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Search started!", Toast.LENGTH_SHORT).show();
                buttonScan.setEnabled(false);
                buttonScan.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                //run in background
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        //wifi scan
                        deviceList.clear();
                        deviceWifiScan(startIP, endIP, port);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update UI
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "Search complete!", Toast.LENGTH_SHORT).show();
                                buttonScan.setEnabled(true);
                                buttonScan.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                            }
                        });
                    }
                });
            }
        });

        //RUNTIME CONF. CHANGE HANDLE
        if (savedInstanceState != null) {

        } else {
            //handleWifi();
        }


    }

    private void initializationOfViews() {
        eT_ip = findViewById(R.id.eT_ip);
        eT_port = findViewById(R.id.eT_port);
    }

    /**
     * scan wifi for a specific port
     */
    private void deviceWifiScan(int start, int end, int port) {
        String wifi = getDeviceIP(false);
        for (int s = start; s <= end; s++) {
            String ip = wifi + s;
            if (isPortOpen(ip, port, 100)) {
                deviceList.add(new NetworkDevice(ip));
                //Log.i("LED DEVICE IP FOUND", ip);
            }
        }
    }

    /**
     * returns true if port is open
     */
    private boolean isPortOpen(final String ip, final int port, final int timeout) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            socket.close();
            return true;
        } catch (ConnectException ce) {
            //ce.printStackTrace();
            return false;
        } catch (Exception ex) {
            //ex.printStackTrace();
            return false;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        handleWifi();
        adapter.notifyDataSetChanged();
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
                //Log.i("WIFI", "wifi not connected");
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
                Toast.makeText(MainActivity.this, "WIFI connected!", Toast.LENGTH_SHORT).show();
                String ipString = getDeviceIP(false);

                EditText eT_ip = findViewById(R.id.eT_ip);
                eT_ip.setText(ipString);

                EditText eT_port = findViewById(R.id.eT_port);
                String defaultPort = "8001";
                eT_port.setText(defaultPort);
            }
        }
    }

    /**
     * @returns the phone ip either ccc.ccc.ccc. or ccc.ccc.ccc.ccc
     */
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

    @Override
    //if settings menu should show
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
