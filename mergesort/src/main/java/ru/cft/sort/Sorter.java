package ru.cft.sort;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ru.cft.params.SortParams;
import ru.cft.params.SortType;
import ru.cft.utils.FileElementReader;
import ru.cft.utils.MapReadersUtil;
import ru.cft.utils.NumberChecker;

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
                
                for(Map.Entry<FileElementReader, String> inReader : inputFilesReaders.entrySet()){
                    if(inReader.getKey().isNeedToReadNext()){
                        String line;
                        if ((line = inReader.getKey().readLine()) != null) {

                            if (sortParams.getSortType().equals(SortType.DIGIT)) {
                                if (!NumberChecker.isNumeric(line)) {
                                    System.out.println("Обнаружен неверный формат числовых данных во входном файле: "
                                            + inReader.getKey().getFileName() + ", часть данных будет потеряна!");
                                    inReader.getKey().close();
                                    inputFilesReaders.remove(inReader.getKey());
                                }
                            }

                            if (sortParams.getSortType().equals(SortType.STR)) {
                                if (line.contains(" ")) {
                                    System.out.println("Обнаружена строка, содержащая пробел в файле:"
                                            + inReader.getKey().getFileName() + ", часть данных будет потеряна!");
                                    inReader.getKey().close();
                                    inputFilesReaders.remove(inReader.getKey());
                                }
                            }

                            inReader.setValue(line);
                            inReader.getKey().setNeedToReadNext(false);

                        } else {
                            inReader.getKey().close();
                            inputFilesReaders.remove(inReader.getKey());
                        }
                    }
                }

                if (inputFilesReaders.size() > 0) {
                    String nextOutElement = MapReadersUtil.findNextOrderElement(inputFilesReaders.values(), sortParams.getSortDirection());
                    outFileWriter.write(nextOutElement + "\n");

                    for (Map.Entry<FileElementReader, String> inReader : inputFilesReaders.entrySet()) {
                        if (inReader.getValue().equals(nextOutElement)) {
                            inReader.getKey().setNeedToReadNext(true);
                        }
                    }
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
