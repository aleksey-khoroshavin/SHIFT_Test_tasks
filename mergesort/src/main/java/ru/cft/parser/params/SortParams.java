package ru.cft.parser.params;

import java.util.ArrayList;
import java.util.List;

public class SortParams {
    private SortDirection sortDirection;
    private SortType sortType;
    private List<String> inputFilesNames = new ArrayList<>();
    private String outputFileName;

    public void setSortDirection(SortDirection sortDirection){
        this.sortDirection = sortDirection;
    }

    public void setSortType(SortType sortType){
        this.sortType = sortType;
    }

    public SortDirection getSortDirection(){
        return this.sortDirection;
    }

    public SortType getSortType(){
        return this.sortType;
    }

    public void setOutputFileName(String outputFileName){
        this.outputFileName = outputFileName;
    }

    public String getOutputFileName(){
        return this.outputFileName;
    }

    public void addINputFileName(String inputFileName){
        this.inputFilesNames.add(inputFileName);
    }

    public List<String> getInputFilesNames(){
        return this.inputFilesNames;
    }

    private String getInputFilesNamesString(){
        StringBuilder stringBuilder = new StringBuilder();

        for(String fileName : inputFilesNames){
            stringBuilder.append("\t" + fileName + ";\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString(){
        return "{\n Sort Direction: " + this.sortDirection.toString() + ";\n " +
        "Sort Data Type: " + this.getSortType().toString() + ";\n " + 
        "Output file name: " + this.getOutputFileName() + ";\n " +
        "Input files names:\n  [\n" + this.getInputFilesNamesString() + "  ]\n}";
    }
}
