package ru.cft;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import ru.cft.sort.Sorter;
import ru.cft.util.SortDirection;
import ru.cft.util.SortParams;
import ru.cft.util.SortType;

public class App 
{
    public static void main( String[] args )
    {
        Options options = new Options();
        options.addOption("a", false, "sort order ascending");
        options.addOption("d", false, "sort order descending");
        options.addOption("s", false, "string values");
        options.addOption("i", false, "integer values");
        
        try{

            if(args.length < 3){
                throw new IllegalArgumentException("Неверное количество аргументов!");
            }

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            SortParams sortParams = new SortParams();
            
            if(!cmd.hasOption("s") && !cmd.hasOption("i")){
                throw new IllegalArgumentException("Не задан тип сортируемых элементов!");
            }

            if(cmd.hasOption("s") && cmd.hasOption("i")){
                throw new IllegalArgumentException("Указаны две разные опции типа сортируемых элементов! Выбрана может быть лишь одна!");
            }

            if(cmd.hasOption("s")){
                sortParams.setSortType(SortType.STR);
            }else{
                sortParams.setSortType(SortType.DIGIT);
            }

            if(cmd.hasOption("d") && cmd.hasOption("a")){
                throw new IllegalArgumentException("Указаны две разные опции направления сортировки! Выбрана может быть лишь одна!");
            }

            if(!cmd.hasOption("d") && !cmd.hasOption("a")){
                sortParams.setSortDirection(SortDirection.ASC);
            }

            if(cmd.hasOption("d")){
                sortParams.setSortDirection(SortDirection.DESC);
            }else if(cmd.hasOption("a")){
                sortParams.setSortDirection(SortDirection.ASC);
            }

            if(args[1].length() > 2 /*not an option "-o" */){
                sortParams.setOutputFileName(args[1]);
                for(int i = 2; i < args.length; i++){
                    sortParams.addINputFileName(args[i]);
                }
            }else{
                sortParams.setOutputFileName(args[2]);
                for(int i = 3; i < args.length; i++){
                    sortParams.addINputFileName(args[i]);
                }
            }

            Sorter sorter = new Sorter(sortParams);

            System.out.println(sorter.getSortParams().toString());

            sorter.sort();
        }
        catch(org.apache.commons.cli.ParseException exception){
            System.out.println(exception.getMessage());
        }
        catch(IllegalArgumentException exception){
            System.out.println(exception.getMessage());
        }
        
    }
}
