package ru.cft.writer;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileElementWriter extends BufferedWriter{
    private String sourceName;

    public FileElementWriter(OutputStream outputStream, String sourceName) {
        super(new OutputStreamWriter(outputStream));
        this.sourceName = sourceName;
    }
    
    public String getSourceName(){
        return this.sourceName;
    }
}
