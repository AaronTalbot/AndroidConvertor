package com.example.convertor;

import java.util.ConcurrentModificationException;

public class Length {

    /**
     * this class deals with all the length functionality
     * Does all three lengths to eachother.
     */

    public Length() {
    }

    /**
     *
     * @param Feet
     * @return
     */
    public String FeetToMeters(String Feet){
        double feet = Double.parseDouble(Feet);
        double meters = feet/3.2808;
        String re = meters + "";
        return re;
    }

    /**
     *
     * @param Meters
     * @return
     */
    public String MetersToFeet(String Meters){
        double meters =  Double.parseDouble(Meters);
        double feet = meters*3.2808;
        String re = feet+"";
        return re;
    }

    /**
     *
     * @param Meters
     * @return
     */
    public String MetersToMeters(String Meters){
        return Meters;
    }

    /**
     *
     * @param Meters
     * @return
     */
    public String MetersToCM(String Meters){
        double m = Double.parseDouble(Meters);
        double cm = m*100;
        return cm+"";
    }

    /**
     *
     * @param CM
     * @return
     */
    public String CMtoMeters(String CM){
        double cm = Double.parseDouble(CM);
        double m = cm/100;
        return m+"";
    }

    /**
     *
     * @param cm
     * @return
     */
    public String CmToFeeT(String cm){
        String res = CMtoMeters(cm);
        String outres = MetersToFeet(res);
        return outres;
    }

    /**
     *
     * @param Feet
     * @return
     */
    public String FeetToCM(String Feet){
        String res = FeetToMeters(Feet);
        String outres = MetersToCM(res);
        return  outres;
    }
}
