package ru.cft.limitfinders;

import java.util.Collection;

import ru.cft.parser.params.SortDirection;

public abstract class LimitElementFinder {
    
    protected abstract String findMaxElement(Collection<String> values);

    protected abstract String findMinElement(Collection<String> values);
    
    public String findNextOrderElement(SortDirection sortDirection, Collection<String> values) {
        if (sortDirection.equals(SortDirection.ASC)) {
            return findMinElement(values);
        } else {
            return findMaxElement(values);
        }
    }
}
