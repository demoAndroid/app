package com.example.truyenol.model;

public class Story {
    private int id;
    private String nameStory;
    private String type;
    private String status;
    private String description;
    private String author;
    private float rating;
    private String linkImg;
    private String numberChapter;

    public Story() {
    }

    public Story(int id, String nameStory, String type, String status, String description, String author, float rating, String linkImg, String numberChapter) {
        this.id = id;
        this.nameStory = nameStory;
        this.type = type;
        this.status = status;
        this.description = description;
        this.author = author;
        this.rating = rating;
        this.linkImg = linkImg;
        this.numberChapter = numberChapter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStory() {
        return nameStory;
    }

    public void setNameStory(String nameStory) {
        this.nameStory = nameStory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public String getNumberChapter() {
        return numberChapter;
    }

    public void setNumberChapter(String numberChapter) {
        this.numberChapter = numberChapter;
    }
}
