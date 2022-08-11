package ru.cft.checkers.order;

import ru.cft.exceptions.OrderException;
import ru.cft.parser.params.SortDirection;

public abstract class ElementOrderChecker {

    public void isCorrectOrder(String currElement, String prevElement, SortDirection sortDirection, String srcName) throws OrderException{
        if(prevElement == null || prevElement.isEmpty()){
            return;
        }
        
        String msg = String.format(
                "Wrong order in file: %s, some data will be lost! %n Problem with elements: %s and %s",
                srcName, currElement, prevElement);

        if (sortDirection.equals(SortDirection.ASC)) {
            if(!isCurrElementBigger(currElement, prevElement)){
                throw new OrderException(msg);
            }
        } else {
            if(!isCurrElementSmaller(currElement, prevElement)){
                throw new OrderException(msg);
            }
        }
    }

    protected abstract boolean isCurrElementBigger(String currElement, String prevElement);
    
    protected abstract boolean isCurrElementSmaller(String currElement, String prevElement);
}
