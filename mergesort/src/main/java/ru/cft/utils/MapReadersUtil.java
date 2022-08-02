package ru.cft.utils;

import java.util.Collection;

import ru.cft.params.SortDirection;
import ru.cft.params.SortType;

public class MapReadersUtil {
    public static String findNextOrderElement(Collection<String> values, SortDirection sortDirection, SortType sortType){
        if(sortDirection.equals(SortDirection.ASC)){
            if(sortType.equals(SortType.STR)){
                return finMinStrElement(values);
            }else{
                return findMinDigitElement(values);
            }
            
        }else{
            if(sortType.equals(SortType.STR)){
                return findMaxStrElement(values);
            }else{
                return findMaxDigitElement(values);
            }
        }
    }

    private static String finMinStrElement(Collection<String> values){
        String tmpElement = null;

        for(String value : values){
            if(tmpElement == null){
                tmpElement = value;
            }else{
                if(tmpElement.compareTo(value) >= 0){
                    tmpElement = value;
                }
            }
        }

        return tmpElement;
    }

    private static String findMinDigitElement(Collection<String> values){
        Integer tmpElement = null;

        try{
            for(String value : values){
                if(tmpElement == null){
                    tmpElement = Integer.parseInt(value);
                }else{
                    if(tmpElement.compareTo(Integer.parseInt(value)) >= 0){                       
                        tmpElement = Integer.parseInt(value);
                    }
                }
            }
        }
        catch(NumberFormatException exception){
            tmpElement = null;
        }

        return tmpElement.toString();
    }

    private static String findMaxStrElement(Collection<String> values){
        String tmpElement = null;

        for(String value : values){
            if(tmpElement == null){
                tmpElement = value;
            }else{
                if(tmpElement.compareTo(value) <= 0){
                    tmpElement = value;
                }
            }
        }

        return tmpElement;
    }

    private static String findMaxDigitElement(Collection<String> values){
        Integer tmpElement = null;

        try{
            for(String value : values){
                if(tmpElement == null){
                    tmpElement = Integer.parseInt(value);
                }else{
                    if(tmpElement.compareTo(Integer.parseInt(value)) <= 0){
                        tmpElement = Integer.parseInt(value);
                    }
                }
            }
        }
        catch(NumberFormatException exception){
            tmpElement = null;
        }

        return tmpElement.toString();
    }
}
