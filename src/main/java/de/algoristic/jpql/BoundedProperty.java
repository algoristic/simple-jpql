package de.algoristic.jpql;


public class BoundedProperty extends Property {
    
    private Table table;
    
    protected BoundedProperty(String name, Class<?> binding) {
        this(name, Table.of(binding));
    }

    protected BoundedProperty(String name, Table table) {
        super(name);
        this.table = table;
    }

    @Override
    public String getTableAlias() {
        return table.getAlias();
    }

    @Override
    protected void completeReferences(FromClause fromClause) {
        for(Table other: fromClause) {
            if(table.matching(other)) {
                String alias = other.getAlias();
                table.setAlias(alias);
                break;
            }
        }
    }

}
