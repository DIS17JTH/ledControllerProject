package se.ju.students.malu1798.ledcontrollerproject;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Human> humans = new ArrayList<>();

    static{
        for (int i = 0; i < 1000; i++){
            humans.add(new Human(
                    i,
                    "Human #"+i,
                    (int)(Math.random() * 100)
            ));
        }
    }

        public class Mode{
            private int m_id;
            private String m_modeName;
            private List<Setting> m_settings;

            public Mode(int m_id, String m_modeName, List<Setting> m_settings){
                this.m_id = m_id;
                this.m_modeName = m_modeName;
                this.m_settings = m_settings;
            }

            public Mode(){
                //this(0, "Default mode name", );
            }
    }

    public class Setting{

        private int s_tot_amount = 0;
        private int s_id;
        private String s_name;
        private String s_description;
        private int s_priority;

        public Setting(String s_name, String s_description, int s_priority){
            this.s_id = this.s_tot_amount;
            this.s_name = s_name;
            this.s_description = s_description;
            this.s_priority = s_priority;
            s_tot_amount++;
        }

        public Setting(){
            this("Default name","Defult desciption", 100);
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

        public int getS_priority() {
            return s_priority;
        }

        public void setS_priority(int s_priority) {
            this.s_priority = s_priority;
        }
    }

    public static class Human{
        public int id;
        public String name;
        public int age;

        public Human(int id, String name, int age){
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }

}
