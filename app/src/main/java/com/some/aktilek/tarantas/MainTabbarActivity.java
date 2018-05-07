package com.some.aktilek.tarantas;

public interface MainTabbarActivity {
    public static enum SCREENS {
        Home, PostProduct, Account
    }
    void moveTo(SCREENS screen);
}
