package com.adel.currencyexchange2;

public class MoneyDataModel {


    public String value;
    public String title;
    public String commentl;
    public int process;

    public MoneyDataModel(String value, String title, String commentl, int process) {
        this.value = value;
        this.title = title;
        this.commentl = commentl;
        this.process = process;
    }

    public String getValue() {

      return Gen.getFormatedAmount(value);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommentl() {
        return commentl;
    }

    public void setCommentl(String commentl) {
        this.commentl = commentl;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }



}
