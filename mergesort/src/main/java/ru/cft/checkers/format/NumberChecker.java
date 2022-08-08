package ru.cft.checkers.format;

import ru.cft.exceptions.IntegerElementFormatException;

public class NumberChecker {
    
    public void isCorrectInteger(String strNum, String srcName) throws IntegerElementFormatException{
        try{
            Integer.parseInt(strNum);
        }
        catch(NumberFormatException exception){
            String msg = String.format(
                    "Обнаружен неверный формат числовых данных во входном файле: %s, строка:[%s], часть данных будет потеряна!",
                    srcName, strNum);
            throw new IntegerElementFormatException(msg);
        }
    }
}
