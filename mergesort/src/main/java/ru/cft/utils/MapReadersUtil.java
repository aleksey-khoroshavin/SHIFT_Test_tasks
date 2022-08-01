package ru.cft.utils;

import java.util.Collection;

import ru.cft.params.SortDirection;

public class MapReadersUtil {
    public static String findNextOrderElement(Collection<String> values, SortDirection sortDirection){
        if(sortDirection.equals(SortDirection.ASC)){
            return finMinElement(values);
        }else{
            return findMaxElement(values);
        }
    }

    private static String finMinElement(Collection<String> values){
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

    private static String findMaxElement(Collection<String> values){
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
}
