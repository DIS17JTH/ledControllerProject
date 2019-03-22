package se.ju.students.malu1798.ledcontrollerproject;

import android.util.Log;

import java.util.ArrayList;

public class Colors {
    private static ArrayList<String> colors;




    public Colors() {
        if (colors == null) {
            colors = new ArrayList<>();
            colors.add("#258174");
            colors.add("#27AE60");
            colors.add("#3498DB");
            colors.add("#CB4335");
            colors.add("#34495E");
            colors.add("#F4D03F");
        }
    }

    public Colors(String color) {
        if (colors == null) {
            colors = new ArrayList<>();
            colors.add("#258174");
            colors.add("#27AE60");
            colors.add("#3498DB");
            colors.add("#CB4335");
            colors.add("#34495E");
            colors.add("#F4D03F");
        }
        colors.add(color);
    }

    public Colors(ArrayList<String> colors) {
        this.colors = colors;
    }

    /*GETTERS AND SETTERS*/
    public ArrayList<String> getColors() {
        return this.colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public void addColor(String color) {
        if (color != null) {
            if (color.equals("#FFFFFF") || color.equals("#000000"))
                Log.i("COLOR", "This is fully ON/OFF not a color");
            else if (!colorExist(color))
                this.colors.add(color);
        }
    }

    private boolean colorExist(String color) {
        for (String currentColor : colors) {
            if (currentColor.equals(color)) {
                Log.i("COLOR", "already exist");
                //System.out.println("COLOR already exist");
                return true;
            }
        }
        return false;
    }

    public String getColor(int index) {
        return this.colors.get(index);
    }

}