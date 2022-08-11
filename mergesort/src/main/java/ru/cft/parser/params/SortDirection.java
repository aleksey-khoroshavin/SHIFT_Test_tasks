package ru.cft.parser.params;

public enum SortDirection {
    DESC,
    ASC;

    @Override
    public String toString() {
    switch(this) {
            case DESC: return "Descending";
            case ASC: return "Ascending";
            default: throw new IllegalArgumentException();
        }
    }
}
