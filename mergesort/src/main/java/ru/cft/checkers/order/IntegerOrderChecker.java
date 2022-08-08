package ru.cft.checkers.order;

public class IntegerOrderChecker extends ElementOrderChecker{

    @Override
    protected boolean isCurrElementBigger(String currElement, String prevElement) {
        try{
            return Integer.parseInt(currElement) >= Integer.parseInt(prevElement);
        }
        catch(NumberFormatException nException){
            return false;
        }
    }

    @Override
    protected boolean isCurrElementSmaller(String currElement, String prevElement) {
        try{
            return Integer.parseInt(currElement) <= Integer.parseInt(prevElement);
        }
        catch(NumberFormatException nException){
            return false;
        }
    }
}
