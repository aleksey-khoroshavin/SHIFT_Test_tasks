package ru.cft.checkers.format;

import ru.cft.exceptions.StringElementFormatException;

public class StringChecker {

    public void isCorrectString(String str, String srcName) throws StringElementFormatException {
        if (str.contains(" ")) {
            String msg = String.format("Обнаружена строка, содержащая пробел во входном файле: %s, строка:[%s], часть данных будет потеряна!", srcName, str);
            throw new StringElementFormatException(msg);
        }
    }
    
}
