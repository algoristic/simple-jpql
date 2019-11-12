package de.algoristic.jpql.sql;

import de.algoristic.jpql.Property;
import de.algoristic.jpql.Table;

public class UnboundedProperty extends Property {

    private String tableAlias;
    
    public UnboundedProperty(String name) {
        super(name);
    }

    public UnboundedProperty(String name, String tableAlias) {
        this(name);
        this.tableAlias = tableAlias;
    }

    @Override
    public String getTableAlias() {
        return tableAlias;
    }

    @Override
    public void completeReferences(FromClause fromClause) {
        if(tableAlias == null) {
            Table reference = fromClause.iterator().next(); //TODO: catch NPE, when fromClause not initialized or empty
            tableAlias = reference.getAlias();
        }
    }

}
