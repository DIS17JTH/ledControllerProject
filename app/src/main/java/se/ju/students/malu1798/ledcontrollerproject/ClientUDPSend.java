package se.ju.students.malu1798.ledcontrollerproject;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientUDPSend implements Runnable {

    @Override
    public void run() {
        try {
            int port = 8080;
            String ip = "192,168.1.205";
            DatagramSocket udpSocket = new DatagramSocket(port);
            InetAddress serverAddr = InetAddress.getByName(ip);
            byte[] buf = ("The String to Send").getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length,serverAddr, port);
            udpSocket.send(packet);
        } catch (SocketException e) {
            Log.e("Udp:", "Socket Error:", e);
        } catch (IOException e) {
            Log.e("Udp Send:", "IO Error:", e);
        }
    }
}
