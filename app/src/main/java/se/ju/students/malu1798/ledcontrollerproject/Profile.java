package se.ju.students.malu1798.ledcontrollerproject;

import java.util.ArrayList;

public class Profile{
    public static ArrayList<Profile> profiles = new ArrayList<>();
    static {
        for (int i = 0; i < 15; i++) {
            profiles.add(new Profile(
                    "Profile"
            ));
        }
    }

    private String p_name;
    private ArrayList<Mode> p_modes;
    private Data.Colors p_favorite_colors;

    public Profile(String name, ArrayList<Mode> modes, Data.Colors fav_colors){
        this.p_name = name;
        this.p_modes = modes;
        this.p_favorite_colors = fav_colors;
    }

    public Profile(String name){
        this.p_name = name;
        this.p_modes = new ArrayList<>();
        this.p_favorite_colors = new Data.Colors();
    }

    /*GETTERS AND SETTERS*/

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public ArrayList<Mode> getP_modes() {
        return p_modes;
    }

    public void setP_modes(ArrayList<Mode> p_modes) {
        this.p_modes = p_modes;
    }


    public Data.Colors getP_favorite_colors() {
        return p_favorite_colors;
    }

    public void setP_favorite_colors(Data.Colors p_favorite_colors) {
        this.p_favorite_colors = p_favorite_colors;
    }

}