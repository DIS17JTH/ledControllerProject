package se.ju.students.malu1798.ledcontrollerproject;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientUDPListen implements Runnable {
    boolean run = true;
    int port = 8015;

    public ClientUDPListen(int port){
        this.port = port;
    }

    @Override
    public void run() {
        while (run) {
            try {
                DatagramSocket udpSocket = new DatagramSocket(port);
                byte[] message = new byte[8000];
                DatagramPacket packet = new DatagramPacket(message,message.length);
                Log.i("UDP client: ", "about to wait to receive");
                udpSocket.receive(packet);
                String text = new String(message, 0, packet.getLength());
                Log.d("Received data", text);
            }catch (IOException e) {
                Log.e("UDP client has IOException", "error: ", e);
                run = false;
            }
        }
    }
}
