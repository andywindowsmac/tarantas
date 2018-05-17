package com.some.aktilek.tarantas;

public interface MainTabbarActivity {
    enum SCREENS {
        Home, PostProduct, Account
    }
    void moveTo(SCREENS screen);
}
