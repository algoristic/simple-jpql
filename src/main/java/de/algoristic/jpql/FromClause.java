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
    public String render() {
        StringBuilder sb = new StringBuilder("FROM").append(" ");
        for (Table table : this) {
            sb.append(table.toString())
                .append(", ");
        }
        sb = new StringBuilder(sb.substring(0, (sb.length() - 2))); //remove single trailing comma
        return sb.toString();
    }

    @Override
    public Iterator<Table> iterator() {
        return tables.iterator();
    }

    @Override
    public String toString() {
        return render();
    }

}
