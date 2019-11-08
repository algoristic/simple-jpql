package de.algoristic.jpql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FromClause implements OperationalClause<Table> {

    private List<Table> tables;

    public FromClause(Table table) {
        this(new ArrayList<>());
        tables.add(table);
    }

    public FromClause(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public Iterator<Table> iterator() {
        return tables.iterator();
    }

}
