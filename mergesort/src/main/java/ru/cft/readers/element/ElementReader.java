package ru.cft.readers.element;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ElementReader extends BufferedReader{
    private String sourceName;
    private String prevReadElement;

    private boolean needToReadNext = true;

    public ElementReader(InputStream inputStream, String sourceName) {
        super(new InputStreamReader(inputStream));
        this.sourceName = sourceName;
    }
    
    public void setNeedToReadNext(boolean value){
        this.needToReadNext = value;
    }

    public String getSourceName(){
        return this.sourceName;
    }

    public boolean isNeedToReadNext(){
        return needToReadNext;
    }

    public String getPrevReadElement(){
        return this.prevReadElement;
    }

    public void setPrevReadElement(String str){
        this.prevReadElement = str;
    }
}
