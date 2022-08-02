package ru.cft.utils;

public class NumberChecker {
    public static boolean isNumeric(String strNum){
        if(strNum == null){
            return false;
        }

        try{
            Integer.parseInt(strNum);
        }
        catch(NumberFormatException exception){
            return false;
        }
        return true;
    }
}
