package ru.cft.limitfinders;

import java.util.Collection;

public class IntegerElementLimitFinder extends LimitElementFinder{

    @Override
    protected String findMinElement(Collection<String> values) {
        Integer tmpElement = null;

        try {
            for (String value : values) {
                if (tmpElement == null) {
                    tmpElement = Integer.parseInt(value);
                } else {
                    if (tmpElement.compareTo(Integer.parseInt(value)) >= 0) {
                        tmpElement = Integer.parseInt(value);
                    }
                }
            }
        } catch (NumberFormatException exception) {
            tmpElement = null;
        }

        if (tmpElement != null) {
            return tmpElement.toString();
        } else {
            return null;
        }
    }

    @Override
    protected String findMaxElement(Collection<String> values) {
        Integer tmpElement = null;

        try {
            for (String value : values) {
                if (tmpElement == null) {
                    tmpElement = Integer.parseInt(value);
                } else {
                    if (tmpElement.compareTo(Integer.parseInt(value)) <= 0) {
                        tmpElement = Integer.parseInt(value);
                    }
                }
            }
        } catch (NumberFormatException exception) {
            tmpElement = null;
        }

        if (tmpElement != null) {
            return tmpElement.toString();
        } else {
            return null;
        }
    }
}
