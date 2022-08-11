package ru.cft.sorter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.cft.checkers.format.NumberChecker;
import ru.cft.checkers.order.ElementOrderChecker;
import ru.cft.checkers.order.IntegerOrderChecker;
import ru.cft.limitfinders.IntegerElementLimitFinder;
import ru.cft.limitfinders.LimitElementFinder;
import ru.cft.parser.params.SortDirection;
import ru.cft.readers.element.ElementReader;
import ru.cft.readers.list.ElementReaderList;

public class IntegerSorter extends AbstractSorter{

    private Logger logger = Logger.getLogger(IntegerSorter.class.getName());

    @Override
    protected List<String> getLineFromEachInputSrc(ElementReaderList elementReaderList, SortDirection sortDirection) {
        List<String> iterLines = new ArrayList<>();

        ListIterator<ElementReader> iterator = elementReaderList.getElementReaders().listIterator();

        while(iterator.hasNext()){
            ElementReader reader = iterator.next();

            if(reader.isNeedToReadNext()){
                String line;

                try{
                    line = reader.readLine();
                    if (line == null || line.isEmpty()) {
                        iterator.remove();
                    } else {
                        NumberChecker numberChecker = new NumberChecker();
                        numberChecker.isCorrectInteger(line, reader.getSourceName());

                        ElementOrderChecker elementOrderChecker = new IntegerOrderChecker();
                        elementOrderChecker.isCorrectOrder(line, reader.getPrevReadElement(), sortDirection, reader.getSourceName());

                        reader.setPrevReadElement(line);
                        reader.setNeedToReadNext(false);
                    }
                    
                }
                catch(Exception exception){
                    logger.log(Level.WARNING, exception.getMessage());
                    iterator.remove();
                    continue;
                }
            }

            iterLines.add(reader.getPrevReadElement());
        }

        return iterLines;
    }

    @Override
    protected String getLimitFromIterLines(List<String> values, SortDirection sortDirection) {
        LimitElementFinder limitElementFinder = new IntegerElementLimitFinder();
        return limitElementFinder.findNextOrderElement(sortDirection, values);
    }
    
}
