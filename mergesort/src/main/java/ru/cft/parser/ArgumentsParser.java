package ru.cft.parser;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import ru.cft.parser.params.SortDirection;
import ru.cft.parser.params.SortParams;
import ru.cft.parser.params.SortType;
import ru.cft.sorter.AbstractSorter;
import ru.cft.sorter.IntegerSorter;
import ru.cft.sorter.StringSorter;

public class ArgumentsParser {
    private Options options = new Options();
    private CommandLineParser parser = new DefaultParser();
    private SortParams params = new SortParams();

    
    public void initOptions(){
        options.addOption("a", false, "sort order ascending");
        options.addOption("d", false, "sort order descending");
        options.addOption("s", false, "string values");
        options.addOption("i", false, "integer values");
    }

    public void checkArguments(String[] args) throws ParseException {
        checkArgsLength(args);
        CommandLine cmd = parser.parse(options, args);
        parseSortType(cmd);
        parseSortDirection(cmd);
        parseOutFileName(args);
        parseInFileNames(args);
    }

    public SortParams getSortParams(){
        return this.params;
    }

    private void checkArgsLength(String[] args) throws IllegalArgumentException{
        if(args.length < 3){
            throw new IllegalArgumentException("Неверное количество аргументов!");
        }
    }

    private void parseSortType(CommandLine cmd){
        if(!cmd.hasOption("s") && !cmd.hasOption("i")){
            throw new IllegalArgumentException("Не задан тип сортируемых элементов!");
        }

        if(cmd.hasOption("s") && cmd.hasOption("i")){
            throw new IllegalArgumentException("Указаны две разные опции типа сортируемых элементов! Выбрана может быть лишь одна!");
        }

        if(cmd.hasOption("s")){
            params.setSortType(SortType.STR);
        }else{
            params.setSortType(SortType.DIGIT);
        }
    }

    private void parseSortDirection(CommandLine cmd){
        if(cmd.hasOption("d") && cmd.hasOption("a")){
            throw new IllegalArgumentException("Указаны две разные опции направления сортировки! Выбрана может быть лишь одна!");
        }

        if(!cmd.hasOption("d") && !cmd.hasOption("a")){
            params.setSortDirection(SortDirection.ASC);
        }

        if(cmd.hasOption("d")){
            params.setSortDirection(SortDirection.DESC);
        }else if(cmd.hasOption("a")){
            params.setSortDirection(SortDirection.ASC);
        }
    }

    private void parseOutFileName(String args[]){
        if(args[1].length() > 2){
            params.setOutputFileName(args[1]);
        }else{
            params.setOutputFileName(args[2]);
        }
    }

    private void parseInFileNames(String[] args){
        if(args[1].length() > 2){
            for(int i = 2; i < args.length; i++){
                params.addINputFileName(args[i]);
            }
        }else{
            for(int i = 3; i < args.length; i++){
                params.addINputFileName(args[i]);
            }
        }
    }

    public AbstractSorter createSorterByType(){
        if(params.getSortType().equals(SortType.STR)){
            return new StringSorter();
        }else{
            return new IntegerSorter();
        }
    }

}
