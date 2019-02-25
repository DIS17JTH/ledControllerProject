package se.ju.students.malu1798.ledcontrollerproject;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ClientUDPSendAndListen implements Runnable {
    @Override
    public void run() {
        boolean run = true;
        String ipVal = "192.168.1.205";
        int portVal = 8080;
        int port = 8080;

        try {
            DatagramSocket udpSocket = new DatagramSocket(portVal);
            InetAddress serverAddr = InetAddress.getByName(ipVal);
            byte[] buf = ("FILES").getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length,serverAddr, port);
            try {
                udpSocket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (run) {
                try {
                    byte[] message = new byte[8000];
                    DatagramPacket p = new DatagramPacket(message,message.length);
                    Log.i("UDP client: ", "about to wait to receive");
                    udpSocket.setSoTimeout(10000);
                    udpSocket.receive(packet);
                    String text = new String(message, 0, p.getLength());
                    Log.d("Received text", text);
                } catch (SocketTimeoutException e) {
                    Log.e("Timeout Exception","UDP Connection:",e);
                    run = false;
                    udpSocket.close();
                } catch (IOException e) {
                    Log.e(" UDP client has IOException", "error: ", e);
                    run = false;
                    udpSocket.close();
                }
            }
        } catch (SocketException e) {
            Log.e("Socket Open:", "Error:", e);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
