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
    public void preProcess(QueryInformation queryInfo) {
        if(tableAlias == null) {
            Table reference = queryInfo.getAffectedTables().get(0);
            tableAlias = reference.getAlias();
        }
    }

}
