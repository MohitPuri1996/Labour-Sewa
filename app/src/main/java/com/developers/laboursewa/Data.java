package com.developers.laboursewa;

/**
 * Created by Amanjeet Singh on 21-Jan-17.
 */

public class Data {
    public static String nature;
    public static String pin;
    public static String pay;
    public static String name;
    public static String number;

    public static String getRating() {
        return rating;
    }

    public static void setRating(String rating) {
        Data.rating = rating;
    }

    public static String getNumber() {
        return number;
    }

    public static void setNumber(String number) {
        Data.number = number;
    }

    public static void setName(String name) {
        Data.name = name;
    }

    public static String getPay() {
        return pay;
    }

    public static void setPay(String pay) {
        Data.pay = pay;
    }

    public static String getPin() {
        return pin;
    }

    public static void setPin(String pin) {
        Data.pin = pin;
    }

    public static void setNature(String nature) {
        Data.nature = nature;
    }

    public static String rating;

    public static String getName() {
        return name;
    }

    public static String getNature() {
        return nature;
    }
}
