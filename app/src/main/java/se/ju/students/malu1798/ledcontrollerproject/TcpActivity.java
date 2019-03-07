package se.ju.students.malu1798.ledcontrollerproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import se.ju.students.malu1798.ledcontrollerproject.TcpPackage.TcpClient;
import se.ju.students.malu1798.ledcontrollerproject.TcpPackage.TcpEvent;

import java.util.Observable;
import java.util.Observer;

public class TcpActivity extends AppCompatActivity implements Observer {

    TcpClient client;

    String ip = "192.168.1.210";
    int port = 9000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_test);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            setIp(bundle.getString("ip", "-1"));
            setPort(bundle.getInt("port", -1));
            System.out.println("ip: " + ip + " port: " + port);
        }

        this.client = new TcpClient(ip, port);
        this.client.addObserver(this);
        client.connect();

        TextView t_ip = findViewById(R.id.t_tcp_ip);
        TextView t_port = findViewById(R.id.t_tcp_port);

        t_ip.setText(getIp());
        t_port.setText(Integer.toString(getPort()));

        //client.sendMessage("This is test code sent to the server");


    }


    @Override
    public void update(Observable o, Object arg) {
        TcpEvent event = (TcpEvent) arg;

        switch (event.getTcpEventType()) {
            case MESSAGE_RECEIVED:
                //Do something
                Log.i("MASSAGE", "MESSAGE RECEIVED");
                break;
            case CONNECTION_ESTABLISHED:
                runOnUiThread(new Runnable() {
                    public void run() {
                        //Update ui
                        Log.i("CONNECTION", "CONNECTION_ESTABLISHED");
                        TextView t_status = findViewById(R.id.t_tcp_status);
                        t_status.setText("CONNECTION_ESTABLISHED");
                    }
                });
                break;

            case CONNECTION_STARTED:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("CONNECTION", "CONNECTION_STARTED");
                        TextView t_status = findViewById(R.id.t_tcp_status);
                        t_status.setText("CONNECTION_STARTED");
                    }
                });
                break;

            case CONNECTION_FAILED:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("CONNECTION", "CONNECTION_FAILED");
                        TextView t_status = findViewById(R.id.t_tcp_status);
                        t_status.setText("CONNECTION_FAILED");
                    }
                });
                break;

            case CONNECTION_LOST:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("CONNECTION", "CONNECTION_LOST");
                        TextView t_status = findViewById(R.id.t_tcp_status);
                        t_status.setText("CONNECTION_LOST");
                    }
                });
                break;

            case DISCONNECTED:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("CONNECTION", "DISCONNECTED");
                        TextView t_status = findViewById(R.id.t_tcp_status);
                        t_status.setText("DISCONNECTED");
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //client.disconnect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //client.disconnect();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    /*GETTERS AND SETTERS*/

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


}
