package com.ysq.musicplayer.entity;

public class BillBoard {
    private String billboard_type;

    private String billboard_no;

    private String update_date;

    private String billboard_songnum;

    private int havemore;

    private String name;

    private String comment;

    private String pic_s192;

    private String pic_s640;

    private String pic_s444;

    private String pic_s260;

    private String pic_s210;

    private String web_url;

    private String color;

    private String bg_color;

    private String bg_pic;

    public String getBillboard_type() {
        return billboard_type;
    }

    public void setBillboard_type(String billboard_type) {
        this.billboard_type = billboard_type;
    }

    public String getBillboard_no() {
        return billboard_no;
    }

    public void setBillboard_no(String billboard_no) {
        this.billboard_no = billboard_no;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getBillboard_songnum() {
        return billboard_songnum;
    }

    public void setBillboard_songnum(String billboard_songnum) {
        this.billboard_songnum = billboard_songnum;
    }

    public int getHavemore() {
        return havemore;
    }

    public void setHavemore(int havemore) {
        this.havemore = havemore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPic_s192() {
        return pic_s192;
    }

    public void setPic_s192(String pic_s192) {
        this.pic_s192 = pic_s192;
    }

    public String getPic_s640() {
        return pic_s640;
    }

    public void setPic_s640(String pic_s640) {
        this.pic_s640 = pic_s640;
    }

    public String getPic_s444() {
        return pic_s444;
    }

    public void setPic_s444(String pic_s444) {
        this.pic_s444 = pic_s444;
    }

    public String getPic_s260() {
        return pic_s260;
    }

    public void setPic_s260(String pic_s260) {
        this.pic_s260 = pic_s260;
    }

    public String getPic_s210() {
        return pic_s210;
    }

    public void setPic_s210(String pic_s210) {
        this.pic_s210 = pic_s210;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBg_color() {
        return bg_color;
    }

    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }

    public String getBg_pic() {
        return bg_pic;
    }

    public void setBg_pic(String bg_pic) {
        this.bg_pic = bg_pic;
    }

    @Override
    public String toString() {
        return "BillBoard{" +
                "billboard_type='" + billboard_type + '\'' +
                ", billboard_no='" + billboard_no + '\'' +
                ", update_date='" + update_date + '\'' +
                ", billboard_songnum='" + billboard_songnum + '\'' +
                ", havemore=" + havemore +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", pic_s192='" + pic_s192 + '\'' +
                ", pic_s640='" + pic_s640 + '\'' +
                ", pic_s444='" + pic_s444 + '\'' +
                ", pic_s260='" + pic_s260 + '\'' +
                ", pic_s210='" + pic_s210 + '\'' +
                ", web_url='" + web_url + '\'' +
                ", color='" + color + '\'' +
                ", bg_color='" + bg_color + '\'' +
                ", bg_pic='" + bg_pic + '\'' +
                '}';
    }
}
