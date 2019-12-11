package com.example.convertor;

public class Temperature {
    public Temperature() {

    }

    public String FarenheittoCelcius(String F){
        double f = Double.parseDouble(F);
        double C = (5.0/9.0)*(f - 32);
        return C+"";
    }

    public  String CelciusToFaranheit(String C){
        double celsius = Double.parseDouble(C);
        double fahrenheit = (9.0/5.0)*celsius + 32;
        return fahrenheit+"";
    }
}
