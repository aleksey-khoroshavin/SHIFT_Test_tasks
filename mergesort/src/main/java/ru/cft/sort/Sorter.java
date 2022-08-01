package ru.cft.sort;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import ru.cft.params.SortParams;
import ru.cft.params.SortType;
import ru.cft.utils.FileElementReader;
import ru.cft.utils.MapReadersUtil;
import ru.cft.utils.NumberChecker;
import ru.cft.utils.OrderChecker;

public class Sorter {
    private SortParams sortParams;
    private BufferedWriter outFileWriter;
    private Map<FileElementReader, String> inputFilesReaders = new HashMap<>();
    

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
                    inputFilesReaders.put(new FileElementReader(new FileReader(inFileName), inFileName), null);
                } catch (FileNotFoundException e) {
                    System.out.println("Файл с именем: " + inFileName + " не найден! Имя файла будет пропущено.");
                }
            }

            if(inputFilesReaders.size() == 0){
                throw new IllegalArgumentException("Не найдено ни одного входного файла! Проверьте правильность аргументов");
            }

            outFileWriter = new BufferedWriter(new FileWriter(sortParams.getOutputFileName()));

            while(true){
                
                Iterator<Entry<FileElementReader, String>> iterator = inputFilesReaders.entrySet().iterator();
                while(iterator.hasNext()){
                    Entry<FileElementReader, String> inReader = iterator.next();

                    if(inReader.getKey().isNeedToReadNext()){
                        String line;
                        if ((line = inReader.getKey().readLine()) != null) {

                            if (sortParams.getSortType().equals(SortType.DIGIT)) {
                                if (!NumberChecker.isNumeric(line)) {
                                    System.out.println("Обнаружен неверный формат числовых данных во входном файле: "
                                            + inReader.getKey().getFileName() + ", строка:[" + line + "], часть данных будет потеряна!");
                                    inReader.getKey().close();
                                    iterator.remove();
                                    continue;
                                }
                            }

                            if (sortParams.getSortType().equals(SortType.STR)) {
                                if (line.contains(" ")) {
                                    System.out.println("Обнаружена строка, содержащая пробел во входном файле:"
                                            + inReader.getKey().getFileName() + ", строка:[" + line + "], часть данных будет потеряна!");
                                    inReader.getKey().close();
                                    iterator.remove();
                                    continue;
                                }
                            }

                            if (inReader.getValue() != null && !inReader.getValue().isEmpty()) {
                                if (!OrderChecker.isCorrectOrder(line, inReader.getValue(),
                                        sortParams.getSortDirection(), sortParams.getSortType())) {
                                    System.out.println("Обнаружен неверный порядок эелементов в файле: "
                                            + inReader.getKey().getFileName() + ", часть данных будет потеряна!");
                                    System.out.println("Проблема в идущих подряд элементах: " + line + " и " + inReader.getValue());
                                    inReader.getKey().close();
                                    iterator.remove();
                                    continue;
                                }
                            }
                            
                            inReader.setValue(line);
                            inReader.getKey().setNeedToReadNext(false);

                        } else {
                            inReader.getKey().close();
                            iterator.remove();
                        }
                    }
                }

                if (inputFilesReaders.size() > 0) {
                    String nextOutElement = MapReadersUtil.findNextOrderElement(inputFilesReaders.values(),
                            sortParams.getSortDirection(), sortParams.getSortType());
                    outFileWriter.write(nextOutElement + "\n");

                    for (Map.Entry<FileElementReader, String> inReader : inputFilesReaders.entrySet()) {
                        if (inReader.getValue().compareTo(nextOutElement) == 0) {
                            inReader.getKey().setNeedToReadNext(true);
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
                
                for(Map.Entry<FileElementReader, String> inReader : inputFilesReaders.entrySet()){
                    if(inReader.getKey()!= null){
                        inReader.getKey().close();
                    }
                }
            }
            catch(IOException exception2){
                System.out.println(exception2.getMessage());
            }
        }
    }
}
