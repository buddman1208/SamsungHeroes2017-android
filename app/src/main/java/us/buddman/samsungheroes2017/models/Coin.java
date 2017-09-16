package us.buddman.samsungheroes2017.models;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Junseok Oh on 2017-06-06.
 */

public class Coin implements Comparable<Coin> {
    private int position = 0;
    private boolean isLike = false;
    private String company;
    private String name;
    private String price = "", volume = "", dailyLow = "", dailyHigh = "", change = "";
    //    private int like = 0, dislike = 0;
    private ArrayList<Text> commments = new ArrayList<>();


    public Coin() {
    }

    public Coin(String company, String price, String volume, String dailyLow, String dailyHigh, String change) {
        this.company = company;
        this.price = price;
        this.volume = volume;
        this.dailyLow = dailyLow;
        this.dailyHigh = dailyHigh;
        this.change = change;
    }

    public boolean reverseLikeStatus() {
        this.isLike = !isLike;
        return isLike;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public Coin setPosition(int position) {
        this.position = position;
        return this;
    }

    public boolean isLike() {
        return isLike;
    }


//    public int getLike() {
//        return like;
//    }
//
//    public void setLike(int like) {
//        this.like = like;
//    }
//
//    public int getDislike() {
//        return dislike;
//    }
//
//    public void setDislike(int dislike) {
//        this.dislike = dislike;
//    }

    public ArrayList<Text> getCommments() {
        return commments;
    }

    public void setCommments(ArrayList<Text> commments) {
        this.commments = commments;
    }

    public Double getDailyLow() {

        if (dailyLow.equals("Not Supported")) dailyLow = "-1.0";
        return Double.parseDouble(dailyLow);
    }

    public Double getDailyHigh() {
        if (dailyHigh.equals("Not Supported")) dailyHigh = "-1.0";
        return Double.parseDouble(dailyHigh);
    }

    public int getPosition() {
        return position;
    }

    public double getVolume() {
        if (volume.equals("Not Supported") || volume.trim().equals("")) volume = "-1.0";
        return Double.parseDouble(volume);
    }

    public double getChange() {
        if (change.equals("Not Supported")) change = "-1.0";
        return Double.parseDouble(change);
    }

    public String getPrice() {
//        return String.format("%.4f", price);
        return price + "";
    }

    @Override
    public int compareTo(@NonNull Coin o) {
        if (volume.equals("Not Supported") || volume.trim().equals("")) volume = "-1.0";
        if (Double.parseDouble(volume) > o.getVolume()) return 1;
        else if (Double.parseDouble(volume) < o.getVolume()) return -1;
        return 0;
    }
}
