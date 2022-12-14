package ru.cft.parser.params;

public enum SortType {
    STR,
    DIGIT;

    @Override
    public String toString() {
    switch(this) {
            case STR: return "String";
            case DIGIT: return "Digits (int)";
            default: throw new IllegalArgumentException();
        }
    }
}
