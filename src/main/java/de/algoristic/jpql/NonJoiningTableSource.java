package de.algoristic.jpql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NonJoiningTableSource implements FromClause {
    
    private List<Table> tables;
    
    public NonJoiningTableSource(Table table) {
        this(new ArrayList<>());
        tables.add(table);
    }

    public NonJoiningTableSource(List<Table> tables) {
        this.tables = tables;
    }
    
    @Override
    public String render() {
        StringBuilder sb = new StringBuilder("FROM").append(" ");
        for (Table table : this) {
            sb.append(table.getName()).append(" ");
            sb.append(table.getAlias()).append(" ");
            sb.append(",");
        }
        sb = new StringBuilder(sb.substring(0, (sb.length() - 2))); //remove single trailing comma
        return sb.toString();
    }
    
    @Override
    public Iterator<Table> iterator() {
        return tables.iterator();
    }

}
