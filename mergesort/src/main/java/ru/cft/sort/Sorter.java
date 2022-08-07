package ru.cft.sort;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import ru.cft.params.SortParams;
import ru.cft.params.SortType;
import ru.cft.utils.FileElementReader;
import ru.cft.utils.MapReadersUtil;
import ru.cft.utils.NumberChecker;
import ru.cft.utils.OrderChecker;

public class Sorter {
    private SortParams sortParams;
    private BufferedWriter outFileWriter;
    private List<FileElementReader> inputFilesReaders = new ArrayList<>();
    

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
                    inputFilesReaders.add(new FileElementReader(new FileReader(inFileName), inFileName));
                } catch (FileNotFoundException e) {
                    System.out.println("Файл с именем: " + inFileName + " не найден! Имя файла будет пропущено.");
                }
            }

            if(inputFilesReaders.size() == 0){
                throw new IllegalArgumentException("Не найдено ни одного входного файла! Проверьте правильность аргументов");
            }

            outFileWriter = new BufferedWriter(new FileWriter(sortParams.getOutputFileName()));

            while(true){
                
                ListIterator<FileElementReader> iter = inputFilesReaders.listIterator();
                while(iter.hasNext()){

                    FileElementReader inReader = iter.next();

                    if(inReader.isNeedToReadNext()){
                        String line;
                        if ((line = inReader.readLine()) != null) {

                            if (sortParams.getSortType().equals(SortType.DIGIT)) {
                                if (!NumberChecker.isNumeric(line)) {
                                    System.out.println("Обнаружен неверный формат числовых данных во входном файле: "
                                            + inReader.getFileName() + ", строка:[" + line + "], часть данных будет потеряна!");
                                    inReader.close();
                                    iter.remove();
                                    continue;
                                }
                            }

                            if (sortParams.getSortType().equals(SortType.STR)) {
                                if (line.contains(" ")) {
                                    System.out.println("Обнаружена строка, содержащая пробел во входном файле:"
                                            + inReader.getFileName() + ", строка:[" + line + "], часть данных будет потеряна!");
                                    inReader.close();
                                    iter.remove();
                                    continue;
                                }
                            }

                            if (inReader.getPrevReadElement() != null && !inReader.getPrevReadElement().isEmpty()) {
                                if (!OrderChecker.isCorrectOrder(line, inReader.getPrevReadElement(),
                                        sortParams.getSortDirection(), sortParams.getSortType())) {
                                    System.out.println("Обнаружен неверный порядок эелементов в файле: "
                                            + inReader.getFileName() + ", часть данных будет потеряна!");
                                    System.out.println("Проблема в идущих подряд элементах: " + line + " и " + inReader.getPrevReadElement());
                                    inReader.close();
                                    iter.remove();
                                    continue;
                                }
                            }
                            
                            inReader.setPrevReadElement(line);
                            inReader.setNeedToReadNext(false);

                        } else {
                            inReader.close();
                            iter.remove();
                        }
                    }
                }

                if (inputFilesReaders.size() > 0) {
                    List<String> iterationLines = new ArrayList<>();

                    for(FileElementReader inReader : inputFilesReaders){
                        iterationLines.add(inReader.getPrevReadElement());
                    }

                    String nextOutElement = MapReadersUtil.findNextOrderElement(iterationLines,
                            sortParams.getSortDirection(), sortParams.getSortType());
                    outFileWriter.write(nextOutElement + "\n");

                    for (FileElementReader inReader : inputFilesReaders) {
                        if (inReader.getPrevReadElement().compareTo(nextOutElement) == 0) {
                            inReader.setNeedToReadNext(true);
                            break;
                        }
                    }
                }

                if(inputFilesReaders.size() == 0){
                    break;
                }
                
            }

        }
        catch(IOException exception){
            System.out.println(exception.getMessage());
        }
        finally{
            try{
                if(outFileWriter != null){
                    outFileWriter.flush();
                    outFileWriter.close();
                }
                
                for(FileElementReader inReader : inputFilesReaders){
                    if(inReader!= null){
                        inReader.close();
                    }
                }
            }
            catch(IOException exception2){
                System.out.println(exception2.getMessage());
            }
        }
    }
}
