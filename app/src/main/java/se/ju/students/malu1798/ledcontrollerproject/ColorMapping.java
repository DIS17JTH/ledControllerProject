package se.ju.students.malu1798.ledcontrollerproject;

import androidx.appcompat.app.AppCompatActivity;

public class ColorMapping extends AppCompatActivity {
    private int red;
    private int green;
    private int blue;

    public ColorMapping(int r,int g,int b){
        this.setRed(r);
        this.setGreen(g);
        this.setBlue(b);
    }

    public ColorMapping() {
        this.setRed(0);
        this.setGreen(0);
        this.setBlue(0);
    }

    public int procentageToFullRange(int procentage){
        return (int) (procentage*2.55);
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


}
