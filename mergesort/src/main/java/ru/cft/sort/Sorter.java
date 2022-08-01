package ru.cft.sort;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.cft.util.SortParams;

public class Sorter {
    private SortParams sortParams;
    private FileWriter outFileReader;
    private List<FileReader> inputFilesReaders = new ArrayList<>();

    public Sorter(SortParams sortParams){
        this.sortParams = sortParams;
    }

    public SortParams getSortParams(){
        return this.sortParams;
    }

    public void sort(){

        try{
            for(String inFileName : sortParams.getInputFilesNames()){

                try {
                    inputFilesReaders.add(new FileReader(inFileName));
                } catch (FileNotFoundException e) {
                    System.out.println("Файл с именем: " + inFileName + " не найден! Имя файла будет пропущено.");
                }
            }

            if(inputFilesReaders.size() == 0){
                throw new IllegalArgumentException("Не найдено ни одного входного файла! Проверьте праивльность аргументов");
            }

            outFileReader = new FileWriter(sortParams.getOutputFileName());

        }
        catch(IOException exception){
            System.out.println(exception.getMessage());
        }
        finally{
            try{
                outFileReader.close();
            }
            catch(IOException exception2){
                exception2.printStackTrace();
            }
            
        }
        
    }
}
