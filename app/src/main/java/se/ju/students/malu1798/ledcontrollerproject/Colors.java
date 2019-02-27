package se.ju.students.malu1798.ledcontrollerproject;

import java.util.ArrayList;

public class Colors {
    public static ArrayList<String> colors;

    public Colors() {
        if (colors == null){
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
        if(colors == null) {
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
        this.colors.add(color);
    }

    public String getColor(int index) {
        return this.colors.get(index);
    }
}