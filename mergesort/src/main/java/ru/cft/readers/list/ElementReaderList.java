package ru.cft.readers.list;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.cft.readers.element.ElementReader;

public class ElementReaderList implements Closeable{
    private static Logger logger = Logger.getLogger(ElementReaderList.class.getName());

    private List<ElementReader> elementReaders;

    public ElementReaderList(List<ElementReader> elementReaders){
        this.elementReaders = elementReaders;
    }

    @Override
    public void close() throws IOException {
        try {
            for (ElementReader reader : elementReaders) {
                if(reader!= null){
                    reader.close();
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public void add(ElementReader reader){
        this.elementReaders.add(reader);
    }

    public List<ElementReader> getElementReaders(){
        return this.elementReaders;
    }

    public boolean isListEmpty(){
        return this.elementReaders.isEmpty();
    }
    
}
