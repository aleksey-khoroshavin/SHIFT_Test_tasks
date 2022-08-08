package ru.cft.checkers.order;

public class StringOrderChecker extends ElementOrderChecker{

    @Override
    protected boolean isCurrElementBigger(String currElement, String prevElement) {
        return currElement.compareTo(prevElement) >= 0;
    }

    @Override
    protected boolean isCurrElementSmaller(String currElement, String prevElement) {
        return currElement.compareTo(prevElement) <= 0;
    }
    
}
