package ru.cft.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class FileElementReader extends BufferedReader{
    private String fileName;
    private String fileElement;

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

    @Override
    public String readLine() throws IOException{
        fileElement = super.readLine();
        return fileElement;
    }
}
