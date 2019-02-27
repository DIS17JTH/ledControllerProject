package se.ju.students.malu1798.ledcontrollerproject;

import java.util.ArrayList;

public class Profile{
    public static ArrayList<Profile> profiles = new ArrayList<>();
    static {
        for (int i = 0; i < 15; i++) {
            profiles.add(new Profile(
                    "Profile " + i
            ));
            for(int j = 0; j < 18; j++)
                profiles.get(i).getP_modes().add(new Mode("Mode" +  j));
        }
    }

    private static int totAmountOfProfiles = 0;
    private String p_name;
    private ArrayList<Mode> p_modes;
    private Colors p_favorite_colors;

    public Profile(String name, ArrayList<Mode> modes, Colors fav_colors){
        this.p_name = name;

        if(modes == null){
            this.p_modes = new ArrayList<>();
        }else
            this.p_modes = modes;
        if(fav_colors == null){
            this.p_favorite_colors = new Colors();
        }else
            this.p_favorite_colors = fav_colors;

        if(profiles == null)
            totAmountOfProfiles = 0;
        else
            totAmountOfProfiles++;

    }

    public Profile(String name, ArrayList<Mode> modes){
        this(name, modes, null);
    }

    public Profile(String name){
        this(name, null, null);
/*        this.p_name = name;
        this.p_modes = new ArrayList<>();
        this.p_favorite_colors = new Colors();*/
    }

    public Profile(){
        this("Default");
    }



    /*GETTERS AND SETTERS*/

    public static int getTotAmountOfProfiles() {
        return totAmountOfProfiles;
    }

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


    public Colors getP_favorite_colors() {
        return p_favorite_colors;
    }

    public void setP_favorite_colors(Colors p_favorite_colors) {
        this.p_favorite_colors = p_favorite_colors;
    }

    public static ArrayList<Profile> getProfiles() {
        return profiles;
    }
}