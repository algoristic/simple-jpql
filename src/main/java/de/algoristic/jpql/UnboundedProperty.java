package de.algoristic.jpql;

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
    protected void completeReferences(FromClause fromClause) {
        if(tableAlias == null) {
            Table reference = fromClause.iterator().next(); //TODO: catch NPE, when fromClause not initialized or empty
            tableAlias = reference.getAlias();
        }
    }

}
