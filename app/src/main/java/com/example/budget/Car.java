package com.example.budget;

public class Car {

    private String  user  , fuel , amount , cng ,  meter, result , date   ;
  //  private Float  amount ,pearson ,result;
   // private int amount, pearson, result;


    public Car(String user, String fuel, String amount, String cng, String meter, String result, String date) {
        this.user = user;
        this.fuel = fuel;
        this.amount = amount;
        this.cng = cng;
        this.meter = meter;
        this.result = result;
        this.date = date;
    }






    public Car (){
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCng() {
        return cng;
    }

    public void setCng(String cng) {
        this.cng = cng;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //public void setResult(String result) {
     //   this.result = result;
   // }
}
