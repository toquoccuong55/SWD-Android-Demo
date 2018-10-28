package com.shoesshop.groupassignment.model;

public class Size {
    private String name;
    private boolean isChecked;

    public Size() {
    }

    public Size(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
