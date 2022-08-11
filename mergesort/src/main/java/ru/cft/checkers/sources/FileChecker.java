package ru.cft.checkers.sources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.cft.parser.params.SortParams;
import ru.cft.readers.element.ElementReader;
import ru.cft.writer.FileElementWriter;

public class FileChecker {
    private static Logger logger = Logger.getLogger(FileChecker.class.getName());

    private FileChecker(){}

    public static List<ElementReader> initInputSources(SortParams sortParams) throws IllegalArgumentException{
        List<ElementReader> inputFilesReaders = new ArrayList<>();

        for(String inFileName : sortParams.getInputFilesNames()){

            try {
                inputFilesReaders.add(new ElementReader(new FileInputStream(inFileName), inFileName));
            } catch (FileNotFoundException e) {
                logger.log(Level.WARNING, String.format("File with name:%s not found! This file name will be skipped!", inFileName));
            }
        }

        if(inputFilesReaders.isEmpty()){
            throw new IllegalArgumentException("Not found any input files! Check input files");
        }

        return inputFilesReaders;
    }

    public static FileElementWriter initOutputSource(SortParams sortParams) throws FileNotFoundException{
        return new FileElementWriter(new FileOutputStream(sortParams.getOutputFileName()), sortParams.getOutputFileName());
    }
}
