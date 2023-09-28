package com.example.dynamic_recyclerview.model;

public class RecyclerViewItem {

    private String name;
    private String offer;
    private String imageUrl;
    private String category;

    public RecyclerViewItem(String name, String offer, String imageUrl, String category) {
        this.name = name;
        this.offer = offer;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
