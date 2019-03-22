package se.ju.students.malu1798.ledcontrollerproject;

import java.util.ArrayList;

import se.ju.students.malu1798.ledcontrollerproject.TcpPackage.TcpClient;

public class Clients {
    public static ArrayList<TcpClient> tcpClients;
    private static ArrayList<Integer> currentLedControlSettings;
    private ControlLedEnum ledProtocol;

    public Clients() {
        if (tcpClients == null) {
            tcpClients = new ArrayList<>();
        }
        if (currentLedControlSettings == null){
            currentLedControlSettings = new ArrayList<>();
            for (int i = 0; i < ControlLedEnum.values().length; i++) {
                currentLedControlSettings.add(0);
            }
        }
    }

    private Clients(TcpClient client){
        if (tcpClients == null) {
            tcpClients = new ArrayList<>();
        }
        if (currentLedControlSettings == null){
            currentLedControlSettings = new ArrayList<>();
            for (int i = 0; i < ControlLedEnum.values().length; i++) {
                currentLedControlSettings.add(0);
            }
        }
        tcpClients.add(client);
    }

/*    private Clients(ArrayList<TcpClient> tcpClientsVar){
        tcpClients = tcpClientsVar;
    }*/

    public static void setTcpClients(ArrayList<TcpClient> tcpClients) {
        Clients.tcpClients = tcpClients;
    }

    public static ArrayList<TcpClient> getTcpClients() {
        if (tcpClients == null) {
            tcpClients = new ArrayList<>();
        }

        return tcpClients;
    }


    private static ArrayList<Integer> getCurrentLedControlSettings() {
        return currentLedControlSettings;
    }

    private static void setCurrentLedControlSettings(ArrayList<Integer> currentLedControlSettings) {
        Clients.currentLedControlSettings = currentLedControlSettings;
    }

    private int enumToInt(ControlLedEnum enumVar) {
        switch (enumVar) {
            case BRIGHTNESS:
                return 0;
            case RED:
                return 1;
            case GREEN:
                return 2;
            case BLUE:
                return 3;
            case STROBE:
                return 4;
            case STROBE_WAVEFORM:
                return 5;
            case STROBE_HEIGHT:
                return 6;
            case STROBE_FREQUENCY:
                return 7;
            case STROBE_AMPLITUDE:
                return 8;
            case STROBE_OFFSET:
                return 9;
            case AUDIO_SYNC:
                return 10;
            case LED_CONTROL:
                return 11;
            case LEDs:
                return 12;
        }
        return 0;
    }

    public void setControlSetting(ControlLedEnum setting, int value) {
        currentLedControlSettings.set(enumToInt(setting), value);
    }

    public void setControlSetting(int setting, int value) {
        currentLedControlSettings.set(setting, value);
    }

    public static String formatQueryString() {
        String sendMode = String.format("w%03dr%03dg%03db%03ds%01dx%01dh%03df%03da%03do%03du%01dc%01dl%010d",
                currentLedControlSettings.get(0),
                currentLedControlSettings.get(1),
                currentLedControlSettings.get(2),
                currentLedControlSettings.get(3),
                currentLedControlSettings.get(4),
                currentLedControlSettings.get(5),
                currentLedControlSettings.get(6),
                currentLedControlSettings.get(7),
                currentLedControlSettings.get(8),
                currentLedControlSettings.get(9),
                currentLedControlSettings.get(10),
                currentLedControlSettings.get(11),
                currentLedControlSettings.get(12)
        );
        return sendMode;
    }
}
