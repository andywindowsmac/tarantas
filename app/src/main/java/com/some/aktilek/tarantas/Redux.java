package com.some.aktilek.tarantas;

import java.util.HashMap;

public class Redux {
    private static Redux reduxStore = new Redux();

    private HashMap<String, Product> products;

    private Redux() {}

    public static Redux getStore() {
        return reduxStore;
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }
}
