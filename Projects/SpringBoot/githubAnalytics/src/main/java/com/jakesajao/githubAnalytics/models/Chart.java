package com.jakesajao.githubAnalytics.models;

public class Chart {
    private String Name;

    private int Appearance;
    @Override
    public String toString() {
        return "["+"\""+this.Name +"\""+ "," + this.Appearance+"]";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAppearance() {
        return Appearance;
    }

    public void setAppearance(int appearance) {
        Appearance = appearance;
    }

    public Chart(String name, int appearance) {
        Name = name;
        Appearance = appearance;
    }
}
