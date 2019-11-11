package de.algoristic.jpql.render;

import de.algoristic.jpql.Property;
import de.algoristic.jpql.sql.SelectClause;

public class SelectClauseRenderer extends FullClauseRenderer {
    
    protected SelectClause selectClause;

    public SelectClauseRenderer(SelectClause selectClause) {
        this.selectClause = selectClause;
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder("SELECT").append(" ");
        for(Property property: selectClause) {
            String tableAlias = property.getTableAlias();
            String name = property.getName();
            sb.append(tableAlias)
                .append(".")
                .append(name)
                .append(", ");
        }
        sb = new StringBuilder(sb.substring(0, (sb.length() - 2))); //remove single trailing comma
        sb.append(" ");
        return sb.toString();
    }

}
