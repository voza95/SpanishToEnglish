package com.wondrousapps.spanishtoenglish.model;

public class QuizModel {
String opta,optb,optc,ans,que;

    public QuizModel(String opta, String optb, String optc, String ans, String que) {
        this.opta = opta;
        this.optb = optb;
        this.optc = optc;
        this.ans = ans;
        this.que = que;
    }

    public String getOpta() {
        return opta;
    }

    public void setOpta(String opta) {
        this.opta = opta;
    }

    public String getOptb() {
        return optb;
    }

    public void setOptb(String optb) {
        this.optb = optb;
    }

    public String getOptc() {
        return optc;
    }

    public void setOptc(String optc) {
        this.optc = optc;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }
}
