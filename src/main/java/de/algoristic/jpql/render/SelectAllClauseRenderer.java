package de.algoristic.jpql.render;

import de.algoristic.jpql.Select;
import de.algoristic.jpql.sql.FromClause;
import de.algoristic.jpql.sql.SelectClause;
import de.algoristic.jpql.sql.Table;

public class SelectAllClauseRenderer extends SelectClauseRenderer {

    public SelectAllClauseRenderer(SelectClause selectClause) {
        super(selectClause);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder("SELECT").append(" ");
        Select parentOperation = selectClause.getParentOperation();
        FromClause fromClause = parentOperation.getFromClause();
        for(Table table: fromClause) {
            String alias = table.getAlias();
            sb.append(alias)
                .append(", ");
        }
        sb = new StringBuilder(sb.substring(0, (sb.length() - 2))); //remove single trailing comma
        sb.append(" ");
        return sb.toString();
    }

}
