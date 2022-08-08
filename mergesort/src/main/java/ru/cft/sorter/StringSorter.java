package ru.cft.sorter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.cft.checkers.format.StringChecker;
import ru.cft.checkers.order.ElementOrderChecker;
import ru.cft.checkers.order.StringOrderChecker;
import ru.cft.limitfinders.LimitElementFinder;
import ru.cft.limitfinders.StringElementLimitFinder;
import ru.cft.parser.params.SortDirection;
import ru.cft.readers.element.ElementReader;
import ru.cft.readers.list.ElementReaderList;

public class StringSorter extends AbstractSorter{

    private Logger logger = Logger.getLogger(StringSorter.class.getName());

    @Override
    protected List<String> getLineFromEachInputFile(ElementReaderList elementReaderList, SortDirection sortDirection) {
        List<String> iterLines = new ArrayList<>();

        ListIterator<ElementReader> iterator = elementReaderList.getFileReaders().listIterator();

        while(iterator.hasNext()){
            ElementReader reader = iterator.next();

            if(reader.isNeedToReadNext()){
                String line;

                try{
                    line = reader.readLine();
                    if (line == null || line.isEmpty()) {
                        iterator.remove();
                        continue;
                    } else {
                        StringChecker stringChecker = new StringChecker();
                        stringChecker.isCorrectString(line, reader.getSourceName());

                        ElementOrderChecker elementOrderChecker = new StringOrderChecker();
                        elementOrderChecker.isCorrectOrder(line, reader.getPrevReadElement(), sortDirection, reader.getSourceName());

                        reader.setPrevReadElement(line);
                        reader.setNeedToReadNext(false);
                    }
                }
                catch(Exception exception){
                    logger.log(Level.SEVERE, exception.getMessage());
                    iterator.remove();
                }
            }

            iterLines.add(reader.getPrevReadElement());
        }

        return iterLines;
    }

    @Override
    protected String getLimitFromIterLines(List<String> values, SortDirection sortDirection) {
        LimitElementFinder limitElementFinder = new StringElementLimitFinder();
        return limitElementFinder.findNextOrderElement(sortDirection, values);
    }
    
}
