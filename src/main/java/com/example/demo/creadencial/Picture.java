package com.example.demo.creadencial;

public class Picture {
    private String pic1;
    private String pic2;
    private String pic3;

    public Picture() {
        super();
    }

    public Picture(String pic1, String pic2, String pic3) {
        this.pic1 = pic1;
        this.pic2 = pic2;
        this.pic3 = pic3;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pic1='" + pic1 + '\'' +
                ", pic2='" + pic2 + '\'' +
                ", pic3='" + pic3 + '\'' +
                '}';
    }
}
