package se.ju.students.malu1798.ledcontrollerproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.net.CookieHandler;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import androidx.appcompat.app.AppCompatActivity;
import se.ju.students.malu1798.ledcontrollerproject.R;
import se.ju.students.malu1798.ledcontrollerproject.TcpPackage.TcpEvent;

public class TcpWifiScanActivity extends AppCompatActivity {

    private WifiManager wifiManager;
    private ListView listView;
    private Button buttonScan;
    private int size = 0;
    private List<ScanResult> results;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter adapter;
    private Thread wifiScanThread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_scan);
        buttonScan = findViewById(R.id.wifiScanButton);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanWifi();
            }
        });

        listView = findViewById(R.id.wifiScanList);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        scanWifi();
    }

    private void scanWifi() {
        arrayList.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(this, "Scanning WiFi ...", Toast.LENGTH_SHORT).show();
    }

    private class WifiThread extends Thread{
        @Override
        public void run() {
            try {
                WeakReference<Context> mContextRef;
                mContextRef = new WeakReference<>(getBaseContext());

                Context context = mContextRef.get();
                if (context != null) {
                    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    WifiInfo connectionInfo = wm.getConnectionInfo();
                    int ipAddress = connectionInfo.getIpAddress();
                    String ipString = Formatter.formatIpAddress(ipAddress);

                    String prefix = ipString.substring(0, ipString.lastIndexOf(".") + 1);
                    for (int i = 1; i < 255; i++) {
                        String testIp = prefix + String.valueOf(i);
                        InetAddress name = InetAddress.getByName(testIp);
                        String hostName = name.getCanonicalHostName();
                        if (name.isReachable(200))
                            arrayList.add(hostName);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            Toast.makeText(TcpWifiScanActivity.this, "Search complete!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } catch (Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        }
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //results = wifiManager.getScanResults();
            //unregisterReceiver(this);

            arrayList.add("test");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });


            wifiScanThread = new WifiThread();
            wifiScanThread.start();



            /*for (ScanResult scanResult : results) {
                arrayList.add(scanResult.SSID + " - " + scanResult.capabilities);

            }*/
        }
    };


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}