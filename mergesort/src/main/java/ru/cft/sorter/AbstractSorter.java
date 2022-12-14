package ru.cft.sorter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.cft.checkers.sources.FileChecker;
import ru.cft.parser.params.SortDirection;
import ru.cft.parser.params.SortParams;
import ru.cft.readers.element.ElementReader;
import ru.cft.readers.list.ElementReaderList;
import ru.cft.writer.FileElementWriter;

public abstract class AbstractSorter {
    
    private Logger logger = Logger.getLogger(AbstractSorter.class.getName());

    public void sort(SortParams sortParams){
        try(FileElementWriter outFileWriter = FileChecker.initOutputSource(sortParams);
            ElementReaderList inputFilesReaders = new ElementReaderList(FileChecker.initInputSources(sortParams));
        ){
            while(true){
                List<String> iterationLines = getLineFromEachInputSrc(inputFilesReaders, sortParams.getSortDirection());

                if (!inputFilesReaders.isListEmpty()) {

                    String limit = getLimitFromIterLines(iterationLines, sortParams.getSortDirection());

                    allowSrcWithLimitToReadNext(inputFilesReaders, limit);

                    outFileWriter.write(limit + System.lineSeparator());

                } else {
                    break;
                }
            }
        }
        catch(IOException exception){
            logger.log(Level.SEVERE, exception.getMessage());
        }
    }

    protected abstract List<String> getLineFromEachInputSrc(ElementReaderList elementReaderList, SortDirection sortDirection);

    protected abstract String getLimitFromIterLines(List<String> values, SortDirection sortDirection);

    protected void allowSrcWithLimitToReadNext(ElementReaderList elementReaderList, String limit){
        for (ElementReader inReader : elementReaderList.getElementReaders()) {
            if (inReader.getPrevReadElement().compareTo(limit) == 0) {
                inReader.setNeedToReadNext(true);
                break;
            }
        }
    }
    
}
