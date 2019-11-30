package com.example.jannataragh;

public class Comments {

    private String id;
    private String title;
    private String desc;
    private String like;
    private String dislike;

    public Comments(String id,String title,String desc, String like,String dislike)
    {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.like = like;
        this.dislike = dislike;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }
}
