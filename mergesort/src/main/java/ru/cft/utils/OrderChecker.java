package ru.cft.utils;

import ru.cft.params.SortDirection;
import ru.cft.params.SortType;

public class OrderChecker {
    public static boolean isCorrectOrder(String currElement, String prevElement, SortDirection sortDirection, SortType sortType){
        if(sortDirection.equals(SortDirection.ASC)){
            if(sortType.equals(SortType.STR)){
                return isCurrStrBigger(currElement, prevElement);
            }else{
                return isCurrDigitBigger(currElement, prevElement);
            }
        }
        else{
            if(sortType.equals(SortType.STR)){
                return isCurrStrSmaller(currElement, prevElement);
            }else{
                return isCurrDigitSmaller(currElement, prevElement);
            }
        }
    }

    private static boolean isCurrStrSmaller(String currElement, String prevElement){
        return currElement.compareTo(prevElement) <= 0 ? true : false;
    }

    private static boolean isCurrStrBigger(String currElement, String prevElement){
        return currElement.compareTo(prevElement) >= 0 ? true : false;
    }

    private static boolean isCurrDigitSmaller(String currElement, String prevElement){
        try{
            return Integer.parseInt(currElement) <= Integer.parseInt(prevElement);
        }
        catch(NumberFormatException nException){
            return false;
        }
    }

    private static boolean isCurrDigitBigger(String currElement, String prevElement){
        try{
            return Integer.parseInt(currElement) >= Integer.parseInt(prevElement);
        }
        catch(NumberFormatException nException){
            return false;
        }
    }

}
