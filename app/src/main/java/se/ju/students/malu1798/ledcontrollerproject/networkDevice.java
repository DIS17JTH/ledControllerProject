package se.ju.students.malu1798.ledcontrollerproject;

public class networkDevice {
    private String ip;
    private String macAddress;
    private boolean isChecked;

    private networkDevice(String ip, String macAddress, Boolean isChecked) {
        this.ip = ip;
        this.macAddress = macAddress;
        this.isChecked = isChecked;
    }

    private networkDevice(String ip, Boolean isChecked) {
        this.ip = ip;
        this.isChecked = isChecked;
    }

    public networkDevice(String ip) {
        this.ip = ip;
        this.isChecked = false;
    }


    /*GETTERS AND SETTERS*/
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }

}
