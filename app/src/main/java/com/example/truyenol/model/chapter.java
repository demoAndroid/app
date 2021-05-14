package com.example.truyenol.model;

public class chapter {
    public int id;
    public String nameChapter;
    public String content;

    public chapter() {
    }

    public chapter(int id, String nameChapter, String content) {
        this.id = id;
        this.nameChapter = nameChapter;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameChapter() {
        return nameChapter;
    }

    public void setNameChapter(String nameChapter) {
        this.nameChapter = nameChapter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
