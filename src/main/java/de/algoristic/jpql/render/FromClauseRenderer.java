package de.algoristic.jpql.render;

import de.algoristic.jpql.sql.FromClause;
import de.algoristic.jpql.sql.Table;

public class FromClauseRenderer extends FullClauseRenderer {
    
    private FromClause fromClause;

    public FromClauseRenderer(FromClause fromClause) {
        this.fromClause = fromClause;
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder("FROM").append(" ");
        for (Table table : fromClause) {
            sb.append(table.toString())
                .append(", ");
        }
        sb = new StringBuilder(sb.substring(0, (sb.length() - 2))); //remove single trailing comma
        sb.append(" ");
        return sb.toString();
    }

}
