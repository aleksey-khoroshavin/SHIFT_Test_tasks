package ru.cft.readers.list;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.cft.readers.element.ElementReader;

public class ElementReaderList implements Closeable{
    private static Logger logger = Logger.getLogger(ElementReaderList.class.getName());

    private List<ElementReader> iFileElementReaders;

    public ElementReaderList(List<ElementReader> iFileElementReaders){
        this.iFileElementReaders = iFileElementReaders;
    }

    @Override
    public void close() throws IOException {
        try {
            for (ElementReader fReader : iFileElementReaders) {
                if(fReader!= null){
                    fReader.close();
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public void add(ElementReader iReader){
        this.iFileElementReaders.add(iReader);
    }

    public List<ElementReader> getFileReaders(){
        return this.iFileElementReaders;
    }

    public boolean isListEmpty(){
        return this.iFileElementReaders.isEmpty();
    }
    
}
