package ru.cft.limitfinders;

import java.util.Collection;

public class StringElementLimitFinder extends LimitElementFinder{

    @Override
    protected String findMinElement(Collection<String> values) {
        String tmpElement = null;

        for (String value : values) {
            if (tmpElement == null) {
                tmpElement = value;
            } else {
                if (tmpElement.compareTo(value) >= 0) {
                    tmpElement = value;
                }
            }
        }

        return tmpElement;
    }

    @Override
    protected String findMaxElement(Collection<String> values) {
        String tmpElement = null;

        for (String value : values) {
            if (tmpElement == null) {
                tmpElement = value;
            } else {
                if (tmpElement.compareTo(value) <= 0) {
                    tmpElement = value;
                }
            }
        }

        return tmpElement;
    }
}
