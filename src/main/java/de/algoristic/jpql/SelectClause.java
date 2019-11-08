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
    public String render() {
        StringBuilder sb = new StringBuilder("SELECT").append(" ");
        if (properties.size() == 0) {
            FromClause fromClause = parentOperation.getFromClause();
            for (Table table : fromClause) {
                String alias = table.getAlias();
                sb.append(alias)
                    .append(" ")
                    .append(", ");
            }
        } else {
            for (Property property : properties) {
                String tableAlias = property.getTableAlias();
                String name = property.getName();
                sb.append(tableAlias)
                    .append(".")
                    .append(name)
                    .append(" ")
                    .append(", ");
            }
        }
        sb = new StringBuilder(sb.substring(0, (sb.length() - 2))); //remove single trailing comma
        return sb.toString();
    }

    @Override
    public Iterator<Property> iterator() {
        return properties.iterator();
    }

    @Override
    public String toString() {
        return render();
    }

    void setParentOperation(Select parentOperation) {
        this.parentOperation = parentOperation;
    }

}
