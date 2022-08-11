package ru.cft;


import java.util.logging.Level;
import java.util.logging.Logger;

import ru.cft.parser.ArgumentsParser;
import ru.cft.sorter.AbstractSorter;

public class App 
{
    private static Logger logger = Logger.getLogger(App.class.getName());
    public static void main( String[] args )
    {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s %n");
        ArgumentsParser argumentsParser = new ArgumentsParser();
        argumentsParser.initOptions();

        try{
            argumentsParser.checkArguments(args);
            String paramsStr = argumentsParser.getSortParams().toString();
            logger.log(Level.INFO, paramsStr);
            AbstractSorter sorter = argumentsParser.createSorterByType();
            sorter.sort(argumentsParser.getSortParams());
        }
        catch(org.apache.commons.cli.ParseException | IllegalArgumentException exception){
            logger.log(Level.SEVERE, exception.getMessage());
        }
    }
}
