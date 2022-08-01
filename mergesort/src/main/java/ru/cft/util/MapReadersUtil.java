package ru.cft.util;

import java.util.Collection;

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
                if(tmpElement.compareTo(value) == 1){
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
                if(tmpElement.compareTo(value) == -1){
                    tmpElement = value;
                }
            }
        }

        return tmpElement;
    }
}
