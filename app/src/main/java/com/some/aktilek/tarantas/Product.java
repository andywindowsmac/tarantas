package com.some.aktilek.tarantas;

public class Product {
    String id;
    String title;
    String description;
    String imageUrl;
    double price;
    int count;

    Product(String id, String title, String description,String imageUrl, double price,int count) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.count = count;
    }
}
