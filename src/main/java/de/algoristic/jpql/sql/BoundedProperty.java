package de.algoristic.jpql.sql;

import de.algoristic.jpql.Property;

public class BoundedProperty extends Property {
    
    private Table table;
    
    public BoundedProperty(String name, Class<?> binding) {
        this(name, Table.of(binding));
    }

    public BoundedProperty(String name, Table table) {
        super(name);
        this.table = table;
    }

    @Override
    public String getTableAlias() {
        return table.getAlias();
    }

    @Override
    public void completeReferences(FromClause fromClause) {
        for(Table other: fromClause) {
            if(table.matching(other)) {
                String alias = other.getAlias();
                table.setAlias(alias);
                break;
            }
        }
    }

}
