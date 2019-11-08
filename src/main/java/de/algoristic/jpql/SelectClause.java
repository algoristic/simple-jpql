package de.algoristic.jpql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectClause implements OperationalClause<Property> {

    private Select parentOperation;
    private List<Property> properties;

    public SelectClause(Select parentOperation) {
        this.parentOperation = parentOperation;
        this.properties = new ArrayList<>();
    }

    SelectClause(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public Iterator<Property> iterator() {
        return properties.iterator();
    }

    Select getParentOperation() {
        return parentOperation;
    }
    
    boolean isSelectAll() {
        return (properties.size() == 0);
    }

    void setParentOperation(Select parentOperation) {
        this.parentOperation = parentOperation;
    }

}
