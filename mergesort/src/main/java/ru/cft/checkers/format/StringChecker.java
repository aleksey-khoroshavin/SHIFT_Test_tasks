package ru.cft.checkers.format;

import ru.cft.exceptions.StringElementFormatException;

public class StringChecker {

    public void isCorrectString(String str, String srcName) throws StringElementFormatException {
        if (str.contains(" ")) {
            String msg = String.format("Found string with space in input source: %s, line:[%s], some data will be lost!", srcName, str);
            throw new StringElementFormatException(msg);
        }
    }
    
}
