package ru.cft.checkers.format;

import ru.cft.exceptions.IntegerElementFormatException;

public class NumberChecker {
    
    public void isCorrectInteger(String strNum, String srcName) throws IntegerElementFormatException{
        try{
            Integer.parseInt(strNum);
        }
        catch(NumberFormatException exception){
            String msg = String.format(
                    "Found incorrect number format in input source: %s, line:[%s], some data will be lost!",
                    srcName, strNum);
            throw new IntegerElementFormatException(msg);
        }
    }
}
