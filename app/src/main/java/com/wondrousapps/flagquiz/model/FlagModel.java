package com.wondrousapps.flagquiz.model;

/**
 * Created by ntf-42 on 22/2/18.
 */

public class FlagModel {
    String img,optA,optB,optC,optD,Ans,lang;

    public FlagModel() {
    }

    public FlagModel(String img, String optA, String optB, String optC, String optD, String ans, String lang) {
        this.img = img;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.optD = optD;
        Ans = ans;
        this.lang = lang;
    }

    public FlagModel(String img, String optA, String optB, String optC, String optD, String ans) {
        this.img = img;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.optD = optD;
        Ans = ans;
    }

    public FlagModel(String ans, String img) {
        this.Ans=ans;
        this.img=img;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public String getOptA() {
        return optA;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getOptD() {
        return optD;
    }

    public void setOptD(String optD) {
        this.optD = optD;
    }

    public String getAns() {
        return Ans;
    }

    public void setAns(String ans) {
        Ans = ans;
    }
}
