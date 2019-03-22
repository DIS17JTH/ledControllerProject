package se.ju.students.malu1798.ledcontrollerproject;

import androidx.appcompat.app.AppCompatActivity;
/*Written by Lucas M*/
public class ColorMapping extends AppCompatActivity {
    private int red;
    private int green;
    private int blue;
    private int brightness;

    public ColorMapping(int r, int g, int b, int brightness){
        this.setRed(r);
        this.setGreen(g);
        this.setBlue(b);
        this.setBrightness(brightness);
    }

    public ColorMapping(int r,int g,int b){
        this(r,g,b,100);
    }

    public ColorMapping() {
        this(0,0,0);
    }


    public String intColorToHexString(int r, int g, int b){
        //int to hex conversion
        String s_r = Integer.toHexString(r);
        String s_g = Integer.toHexString(g);
        String s_b = Integer.toHexString(b);
        String hexColor = "#" + s_r + s_g + s_b;

        System.out.print("RED" + s_r);
        System.out.print(" GREEN" + s_g);
        System.out.print(" BLUE" + s_b);

        System.out.println("---------RESULT----------- hexColor" + hexColor);

        return hexColor;
    }


    public int procentageToFullRange(int procentage){
        return (int) (procentage*2.55);
    }

    private void setColorWithHex(String hex) {
        String colorStr = hex;
        setRed(Integer.valueOf( colorStr.substring( 1, 3 ), 16 ));
        setGreen(Integer.valueOf( colorStr.substring( 3, 5 ), 16 ));
        setBlue(Integer.valueOf( colorStr.substring( 5, 7 ), 16 ));
    }

    /*GETTERS AND SETTERS*/
    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

}
