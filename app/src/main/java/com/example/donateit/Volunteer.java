package com.example.donateit;
public class Volunteer {
    private String volName;
    private String volEmail;
    private int volPhone;

    public Volunteer(String volName, String volEmail, int volPhone) {
        this.volName = volName;
        this.volEmail = volEmail;
        this.volPhone = volPhone;
    }

    public Volunteer() {
    }

    public String getVolName() {
        return volName;
    }

    public void setVolName(String volName) {
        this.volName = volName;
    }

    public String getVolEmail() {
        return volEmail;
    }

    public void setVolEmail(String volEmail) {
        this.volEmail = volEmail;
    }

    public int getVolPhone() {
        return volPhone;
    }

    public void setVolPhone(int volPhone) {
        this.volPhone = volPhone;
    }
}

