package com.example.music.Entity.Util;

public enum LoginType {
    LOGIN_BY_EMAIL(1),
    LOGIN_BY_GITHUB(2),
    LOGIN_BY_ACCOUNT(3);

    private int type;

    LoginType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
