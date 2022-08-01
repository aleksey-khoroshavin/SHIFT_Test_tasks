package ru.cft.utils;

import java.io.BufferedReader;
import java.io.Reader;

public class FileElementReader extends BufferedReader{
    private String fileName;

    private boolean needToReadNext = true;

    public FileElementReader(Reader reader, String fileName) {
        super(reader);
        this.fileName = fileName;
    }
    
    public void setNeedToReadNext(boolean value){
        this.needToReadNext = value;
    }

    public String getFileName(){
        return this.fileName;
    }

    public boolean isNeedToReadNext(){
        return needToReadNext;
    }
}
