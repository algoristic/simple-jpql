package de.algoristic.jpql.sql;

import de.algoristic.jpql.Property;
import de.algoristic.jpql.Table;

public class BoundedProperty extends Property {
    
    protected Table table;
    
    public BoundedProperty(String name, Table table) {
        super(name);
        this.table = table;
    }

    @Override
    public String getTableAlias() {
        return table.getAlias();
    }

    @Override
    public void preProcess(SharedQueryInformation queryInfo) {
        if(table.hasEmptyAlias()) {
            for(Table other: queryInfo.getAffectedTables()) {
                if(table.matching(other)) {
                    String alias = other.getAlias();
                    table.setAlias(alias);
                    break;
                }
            }
        }
    }

}
