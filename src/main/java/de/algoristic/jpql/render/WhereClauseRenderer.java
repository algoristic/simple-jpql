package de.algoristic.jpql.render;

import de.algoristic.jpql.sql.WhereClause;

public class WhereClauseRenderer extends FullClauseRenderer {

    private WhereClause whereClause;

    public WhereClauseRenderer(WhereClause whereClause) {
        this.whereClause = whereClause;
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        //TODO
        return sb.toString();
    }

}
