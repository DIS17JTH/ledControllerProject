package se.ju.students.malu1798.ledcontrollerproject;

public class Setting {
    private static int s_tot_amount = 0;
    private int s_id;
    private String s_name;
    private String s_description;
    private int s_value;
    private int s_priority;

    public Setting(String s_name, String s_description, int value, int s_priority) {
        this.s_id = this.s_tot_amount;
        this.s_name = s_name;
        this.s_description = s_description;
        this.s_value = value;
        this.s_priority = s_priority;
        s_tot_amount++;
    }

    public Setting() {
        this("Default name", "Defult desciption", 0,0);
    }

    public int getS_id() {
        return s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_description() {
        return s_description;
    }

    public void setS_description(String s_description) {
        this.s_description = s_description;
    }

    public int getS_value() {
        return s_value;
    }

    public void setS_value(int s_value) {
        this.s_value = s_value;
    }

    public int getS_priority() {
        return s_priority;
    }

    public void setS_priority(int s_priority) {
        this.s_priority = s_priority;
    }
}