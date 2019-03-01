package se.ju.students.malu1798.ledcontrollerproject;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientUDPSend implements Runnable {
    int port = 9000;
    String ip = "192.168.1.40";
    boolean stop;

    public ClientUDPSend(String ip, int port){
        if(ip != null)
            this.ip = ip;
        if(port != 0)
            this.port = port;

        this.stop = false;
    }

    public ClientUDPSend(String ip){
        this(ip, 9000);
    }



    @Override
    public void run() {
        DatagramSocket udpSocket = null;
            try {
                udpSocket = new DatagramSocket(port);
                udpSocket.setSoTimeout(2000);
                InetAddress serverAddr = InetAddress.getByName(ip);
                byte[] buf = (" The String to Send").getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, port);
                udpSocket.send(packet);
                Log.i("Udp:", "UDP send data");
            } catch (SocketException e) {
                Log.e("Udp:", "Socket Error:", e);
                udpSocket.disconnect();
                stop = true;
            } catch (IOException e) {
                Log.e("Udp Send:", "IO Error:", e);
                udpSocket.disconnect();
                stop = true;
            }
    }
}
