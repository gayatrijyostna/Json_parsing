package com.example.sys.json_parsing;

public class Listdata {
    private String title;
    private  String des;
    private String image_url;

    public Listdata(String title, String image_url,String des) {
        this.title = title;
        this.image_url = image_url;
        this.des=des;
    }
    public String getTitle() {
        return title;
    }
    public String getDes() {
        return des;
    }
    public String getImage_url() {
        return image_url;
    }
}
