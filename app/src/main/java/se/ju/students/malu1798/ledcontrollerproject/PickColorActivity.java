package se.ju.students.malu1798.ledcontrollerproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import petrov.kristiyan.colorpicker.ColorPicker;
import se.ju.students.malu1798.ledcontrollerproject.TcpPackage.TcpClient;
import se.ju.students.malu1798.ledcontrollerproject.TcpPackage.TcpEvent;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PickColorActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener, Observer {

    //tcp clients
    Clients clients = new Clients();

    ViewHolder viewHolder = new ViewHolder();
    ArrayList<ImageView> a_imageButtons = new ArrayList<>();

    private int lastBrightnessState = 0;

    SeekBar seekB_red;
    SeekBar seekB_green;
    SeekBar seekB_blue;

    private int r = 255;
    private int g = 255;
    private int b = 255;
    private int brightness = 255;

    //String ip = "192.168.1.101";
    int port = 8001;

    //Favorite Colors
    Colors colorsVar = new Colors();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_color);

        setClientRGB(brightness, r, g, b);

        //ViewHolder Pattern
        this.addToViewHolder();

        //Buttons
        Button b_mode = findViewById(R.id.b_mode);
        ImageButton b_colorPicker = findViewById(R.id.iB_color_picker);
        ImageButton b_saveColor = findViewById(R.id.iB_saveColor);
        //Toggle Button
        final ToggleButton t_b_on_off = findViewById(R.id.tB_on_off);
        t_b_on_off.setChecked(true);

        /*GET intent information*/
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            setPort(bundle.getInt("port", 0));

            ArrayList<String> deviceList = new ArrayList<>(bundle.getStringArrayList("networkDevices"));
            for (String ip : deviceList) {
                clients.tcpClients.add(new TcpClient(ip, getPort()));
            }
        }

        /*Try to connect to all clients*/
        for (TcpClient client : clients.tcpClients) {
            try {
                client.addObserver(this);
                client.connect();
            } catch (Exception e) {
                Log.e("CONNECTION", "ERROR", e);
                //e.printStackTrace();
            }
        }

        /*BUTTONS*/
        b_saveColor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("save Color Image Button clicked");

                        //int to hex conversion
                        String hexColor = intColorToHexString(
                                colorConvertWithBrightness(getR()),
                                colorConvertWithBrightness(getG()),
                                colorConvertWithBrightness(getB()
                                ));

                        colorsVar.addColor(hexColor);

                    }
                }
        );

        b_mode.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        System.out.println("Button mode clicked");
                        Intent intent = new Intent(v.getContext(), ModeActivity.class);
                        startActivity(intent);
                    }
                });

        b_colorPicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        System.out.println("Button colorPicker clicked");
                        openColorPicker();
                    }
                });


        t_b_on_off.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("Toggle Button clicked");
                        boolean onOff = t_b_on_off.isChecked();
                        if (onOff) {
                            //ON
                            if (lastBrightnessState == 0) {
                                setBrightness(255);
                                clients.setControlSetting(ControlLedEnum.BRIGHTNESS, 255);
                            } else {
                                setBrightness(lastBrightnessState);
                                clients.setControlSetting(ControlLedEnum.BRIGHTNESS, lastBrightnessState);
                            }

                        } else {
                            //OFF
                            lastBrightnessState = getBrightness();
                            setBrightness(0);
                            clients.setControlSetting(ControlLedEnum.BRIGHTNESS, 0);
                        }
                        updateSeekBars();
                        updateViewColors(getR(), getG(), getB());
                        viewHolder.seekB_brightness.setEnabled(onOff);

                        for (TcpClient client : clients.tcpClients) {
                            try {
                                client.sendMessage(clients.formatQueryString());
                            } catch (RuntimeException e) {
                                Log.e("MESSAGE", "not connected", e);
                            }
                        }
                    }
                }
        );
        /*end BUTTONS*/

        //seek bar
        viewHolder.seekB_brightness.setOnSeekBarChangeListener(this);
        seekB_red.setOnSeekBarChangeListener(this);
        seekB_green.setOnSeekBarChangeListener(this);
        seekB_blue.setOnSeekBarChangeListener(this);

        updateViewColors(getR(), getG(), getB());
    }

    /**
     * adds view to view holder and set view variables
     */
    private void addToViewHolder() {
        //Image Views
        View v_top = findViewById(R.id.layout_top);
        a_imageButtons.add((ImageView) findViewById(R.id.i_pC_led));
        a_imageButtons.add((ImageView) findViewById(R.id.i_pC_led_1));
        a_imageButtons.add((ImageView) findViewById(R.id.i_pC_led_2));
        a_imageButtons.add((ImageView) findViewById(R.id.i_pC_led_3));
        a_imageButtons.add((ImageView) findViewById(R.id.i_pC_led_4));
        a_imageButtons.add((ImageView) findViewById(R.id.i_pC_led_5));
        a_imageButtons.add((ImageView) findViewById(R.id.i_pC_led_6));
        a_imageButtons.add((ImageView) findViewById(R.id.i_pC_led_7));
        a_imageButtons.add((ImageView) findViewById(R.id.i_pC_led_8));
        a_imageButtons.add((ImageView) findViewById(R.id.i_pC_led_9));


        //Color RGB layouts
        viewHolder.v_header = findViewById(R.id.layout_top);
        viewHolder.v_r = findViewById(R.id.t_r);
        viewHolder.v_g = findViewById(R.id.t_g);
        viewHolder.v_b = findViewById(R.id.t_b);

        //Color RGB texts
        viewHolder.t_r = findViewById(R.id.t_r);
        viewHolder.t_g = findViewById(R.id.t_g);
        viewHolder.t_b = findViewById(R.id.t_b);

        //Seek bars
        viewHolder.seekB_brightness = (SeekBar) findViewById(R.id.seekBar_brightness);
        seekB_red = (SeekBar) findViewById(R.id.seekBar_r);
        seekB_green = (SeekBar) findViewById(R.id.seekBar_g);
        seekB_blue = (SeekBar) findViewById(R.id.seekBar_b);

    }

    /**
     * update colors based on seek bar position
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekBar_r:
                //System.out.println("--SeekBar onChange red " + progress + fromUser + seekBar);
                setR(progress);
                clients.setControlSetting(ControlLedEnum.RED, progress);
                break;
            case R.id.seekBar_g:
                //System.out.println("--SeekBar onChange green");
                setG(progress);
                clients.setControlSetting(ControlLedEnum.GREEN, progress);
                break;
            case R.id.seekBar_b:
                //System.out.println("--SeekBar onChange blue");
                setB(progress);
                clients.setControlSetting(ControlLedEnum.BLUE, progress);
                break;
            case R.id.seekBar_brightness:
                //System.out.println("--SeekBar onChange brightness " + getBrightness());
                setBrightness(progress);
                clients.setControlSetting(ControlLedEnum.BRIGHTNESS, progress);
                break;
            default:
                //System.out.println("--SeekBar onChange default");
                break;
        }
        updateViewColors(getR(), getG(), getB());
    }


    /**
     * display a specific color to multiple views
     */
    private void updateViewColors(int red, int green, int blue) {
        int red_minus_brightness = colorConvertWithBrightness(red);
        int green_minus_brightness = colorConvertWithBrightness(green);
        int blue_minus_brightness = colorConvertWithBrightness(blue);


        //viewHolder.v_header.setBackgroundColor(Color.rgb(red_minus_brightness, green_minus_brightness, blue_minus_brightness));
        viewHolder.v_header.setBackgroundColor(Color.WHITE);
        viewHolder.v_r.setBackgroundColor(Color.rgb(red_minus_brightness, 0, 0));
        viewHolder.v_g.setBackgroundColor(Color.rgb(0, green_minus_brightness, 0));
        viewHolder.v_b.setBackgroundColor(Color.rgb(0, 0, blue_minus_brightness));
        setTextColorVisible(red_minus_brightness, viewHolder.t_r);
        setTextColorVisible(green_minus_brightness, viewHolder.t_g);
        setTextColorVisible(blue_minus_brightness, viewHolder.t_b);
        for (ImageView imgView : a_imageButtons) {
            imgView.setColorFilter(Color.rgb(red_minus_brightness, green_minus_brightness, blue_minus_brightness));
        }

    }


    /**
     * Change background to avoid letters disappear into the background
     */
    private void setTextColorVisible(int color, TextView tV) {
        int lowerLimit = 120;
        if (color < lowerLimit)
            tV.setTextColor(Color.WHITE);
        else
            tV.setTextColor(Color.BLACK);
    }

    /**
     *
     * */
    private void updateSeekBars() {
        seekB_red.setProgress(getR());
        seekB_green.setProgress(getG());
        seekB_blue.setProgress(getB());
        viewHolder.seekB_brightness.setProgress(getBrightness());
    }

    /**
     * Handle color picker
     */
    private void openColorPicker() {
        String colorCode = "#258174";

        final ColorPicker cPicker = new ColorPicker(this);
        final ArrayList<String> colorsArr = colorsVar.getColors();

        cPicker.setColors(colorsArr)
                .setOnChooseColorListener(
                        new ColorPicker.OnChooseColorListener() {
                            @Override
                            public void onChooseColor(int position, int color) {
                                if (position != -1) {
                                    //Log.d("COLOR", "color = " + position + " - " + color);
                                    setColorWithHex(colorsArr.get(position));

                                    updateViewColors(getR(), getG(), getB());
                                    updateSeekBars();
                                    for (TcpClient client : clients.tcpClients) {
                                        try {
                                            client.sendMessage(clients.formatQueryString());
                                        } catch (RuntimeException e) {
                                            Log.e("MESSAGE", "not connected", e);
                                        }
                                    }
                                } else
                                    return;
                            }

                            @Override
                            public void onCancel() {
                                //??
                                //finish();
                            }
                        })
                .setColumns(5)
                .setRoundColorButton(true)
                .show();
    }

    private int getColorFullRange(int percentage) {
        double fullRange = percentage * 2.55;
        return (int) fullRange;
    }


    /**
     * Handle seek bar start tracking touch
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.seekBar_r:
                //System.out.println("--SeekBar onStart red");
                break;
            case R.id.seekBar_g:
                //System.out.println("--SeekBar onStart green");
                break;
            case R.id.seekBar_b:
                //System.out.println("--SeekBar onStart blue");
                break;
            case R.id.seekBar_brightness:
                //System.out.println("--SeekBar onStart brightness");
                break;
            default:
                //System.out.println("--SeekBar onStart default");
                break;
        }

    }

    /**
     * Handle seek bar stop tracking touch
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.seekBar_r:
                //System.out.println("--SeekBar onStop red");
                break;
            case R.id.seekBar_g:
                //System.out.println("--SeekBar onStop green");
                break;
            case R.id.seekBar_b:
                //System.out.println("--SeekBar onStop blue");
                break;
            case R.id.seekBar_brightness:
                //System.out.println("--SeekBar onStop brightness");
                break;
            default:
                //System.out.println("--SeekBar onStop default");
                break;
        }
        //tcp send after every stop tracking of seek bars
        for (TcpClient client : Clients.tcpClients) {
            try {
                client.sendMessage(Clients.formatQueryString());
            } catch (RuntimeException e) {
                Log.e("MESSAGE", "could not send message", e);
            }
        }
    }

    /**
     * returns string following the protocol for communication with MCU
     */
/*
    private String sendColorMode() {
        String sendMode = String.format("w%03dr%03dg%03db%03d",
                getBrightness(),
                getValueSetByBrightness(getR()),
                getValueSetByBrightness(getG()),
                getValueSetByBrightness(getB())
        );

        return sendMode;
    }
*/

    /**
     * menu
     */
    @Override
    //if settings menu should show
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * handle back arrow in app bar
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /**
     * Observe TCP connection events
     */
    @Override
    public void update(Observable o, Object arg) {
        TcpEvent event = (TcpEvent) arg;
        updateUi(event);
    }

    /**
     * Update GUI when event was fired
     */
    private void updateUi(final TcpEvent eventPayload) {
        final TextView t_status = findViewById(R.id.t_pC_connect_status);
        switch (eventPayload.getTcpEventType()) {
            case MESSAGE_RECEIVED:
                //Do something
                //Log.i("MASSAGE", "MESSAGE RECEIVED");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String payload = eventPayload.getPayload().toString();
                            if (payload != null) {
                                t_status.setText(payload);
                            }
                        } catch (Exception e) {
                            Log.e("ERROR", "MESSAGE", e);
                            //e.printStackTrace();
                        }
                    }
                });

                break;
            case CONNECTION_ESTABLISHED:
                runOnUiThread(new Runnable() {
                    public void run() {
                        //Update ui
                        //Log.i("CONNECTION", "CONNECTION_ESTABLISHED");
                        t_status.setText("CONNECTION_ESTABLISHED");
                        //client.sendMessage("CONNECTION_ESTABLISHED");
                        for (TcpClient client : Clients.tcpClients) {
                            if (client != null) {
                                try {
                                    client.sendMessage(Clients.formatQueryString());
                                } catch (RuntimeException e) {
                                    Log.e("MESSAGE", "not connected", e);
                                }
                            }
                        }
                    }
                });
                break;

            case CONNECTION_STARTED:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Log.i("CONNECTION", "CONNECTION_STARTED");
                        t_status.setText("CONNECTION_STARTED");
                    }
                });
                break;

            case CONNECTION_FAILED:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Log.i("CONNECTION", "CONNECTION_FAILED");
                        t_status.setText("CONNECTION_FAILED");
                    }
                });
                break;

            case CONNECTION_LOST:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Log.i("CONNECTION", "CONNECTION_LOST");
                        t_status.setText("CONNECTION_LOST");
                    }
                });
                break;

            case DISCONNECTED:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Log.i("CONNECTION", "DISCONNECTED");
                        t_status.setText("DISCONNECTED");
                    }
                });
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Disconnect devices
        for (TcpClient client : Clients.tcpClients) {
            client.disconnect();
        }
        //clear clients
        Clients.tcpClients.clear();
    }


    /**
     * GETTERS AND SETTERS
     */
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int colorConvertWithBrightness(int color) {
        return getValueSetByBrightness(color);
    }


    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getValueSetByBrightness(int in) {
        int out;
        //out = (int) ((in * 100 / (getBrightness() + 1))) / 100;
        out = (getBrightness() * in) / 255;
        //System.out.println("out Value brightness converter: " + out);
        return out;
    }

    /**
     * Converts a hex string to a color.
     *
     * @param hex (i.e. #CCCCCC)
     * @return Color
     */
    private void setColorWithHex(String hex) {
        String colorStr = hex;
        setR(Integer.valueOf(colorStr.substring(1, 3), 16));
        setG(Integer.valueOf(colorStr.substring(3, 5), 16));
        setB(Integer.valueOf(colorStr.substring(5, 7), 16));
        setClientRGB(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16)
        );
    }

    private void setClientRGB(int r, int g, int b) {
        clients.setControlSetting(ControlLedEnum.RED, r);
        clients.setControlSetting(ControlLedEnum.GREEN, g);
        clients.setControlSetting(ControlLedEnum.BLUE, b);
    }

    private void setClientRGB(int brightness, int r, int g, int b) {
        clients.setControlSetting(ControlLedEnum.BRIGHTNESS, brightness);
        setClientRGB(r,g,b);
    }


    /**
     * Converts a color to a hex string.
     *
     * @param r (i.e. 0 to 255)
     * @param g (i.e. 0 to 255)
     * @param b (i.e. 0 to 255)
     * @return String (i.e #000000 to #FFFFFF)
     */
    public String intColorToHexString(int r, int g, int b) {
        //int to hex conversion
        String hexColor = String.format("#%02X%02X%02X", r, g, b); //"#" + s_r + s_g + s_b;
        //System.out.println("---------RESULT----------- hexColor" + hexColor);
        return hexColor;
    }
}
