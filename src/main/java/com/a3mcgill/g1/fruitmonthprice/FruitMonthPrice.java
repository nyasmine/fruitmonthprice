package com.a3mcgill.g1.fruitmonthprice;

public class FruitMonthPrice {
    private String fruit;
    private String month;
    private double price;

    public FruitMonthPrice(String fruit, String month, double price){
        this.fruit = fruit;
        this.month = month;
        this.price = price;
    }

    public String getFruit(){
        return fruit;
    }

    public void setFruit(String fruit){
        this.fruit = fruit;
    }

    public String getMonth(){
        return month;
    }

    public void setMonth(String month){
        this.month = month;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

}
