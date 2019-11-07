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

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder("SELECT").append(" ");
        if (properties.size() == 0) {
            FromClause fromClause = parentOperation.getFromClause();
            for (Table table : fromClause) {
                String alias = table.getAlias();
                sb.append(alias).append(" ");
                sb.append(",");
            }
            sb = new StringBuilder(sb.substring(0, (sb.length() - 1))); //remove single trailing comma
        }
        return sb.toString();
    }

    @Override
    public Iterator<Property> iterator() {
        return properties.iterator();
    }

}
