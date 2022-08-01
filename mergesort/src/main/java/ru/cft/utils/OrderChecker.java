package ru.cft.utils;

import ru.cft.params.SortDirection;

public class OrderChecker {
    public static boolean isCorrectOrder(String currElement, String prevElement, SortDirection sortDirection){
        if(sortDirection.equals(SortDirection.ASC)){
            return isCurrBigger(currElement, prevElement);
        }
        else{
            return isCurrSmaller(currElement, prevElement);
        }
    }

    private static boolean isCurrSmaller(String currElement, String prevElement){
        return currElement.compareTo(prevElement) <= 0 ? true : false;
    }

    private static boolean isCurrBigger(String currElement, String prevElement){
        return currElement.compareTo(prevElement) >= 0 ? true : false;
    }

}
