package com.some.aktilek.tarantas;

public class Product {
    String id;
    String title;
    String description;
    String imageUrl;
    double price;

    Product(String id, String title, String description,String imageUrl, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
