package se.ju.students.malu1798.ledcontrollerproject;

import java.util.ArrayList;
import java.util.List;

public class Mode {
    private String m_modeName;
    private String m_description;
    private ArrayList<Setting> m_settings;

    public Mode(String modeName, ArrayList<Setting> settings) {
        this.m_modeName = modeName;
        if(m_settings == null)
            this.m_settings = new ArrayList<>();
        else
            this.m_settings = settings;

        this.m_description = "Empty";
    }

    public Mode(String modeName) {
        this(modeName, null);
        //this.m_modeName = m_modeName;
        //if(m_settings == null)
        //    this.m_settings = new ArrayList<>();

    }

    public Mode() {
        this("Default Mode");
    }

/*
        public Mode() {
            //this(0, "Default mode name", );
        }
*/

    /*GETTERS AND SETTERS*/

    public List<Setting> getMode_settings() {
        return m_settings;
    }

    public void setMode_settings(ArrayList<Setting> m_settings) {
        this.m_settings = m_settings;
    }

    public String get_modeName() {
        return m_modeName;
    }

    public void setModeName(String m_modeName) {
        this.m_modeName = m_modeName;
    }

}
