package de.algoristic.jpql.render;

import de.algoristic.jpql.sql.WhereClause;

public class WhereClauseRenderer extends OptionalClauseRenderer {

    public WhereClauseRenderer(WhereClause whereClause) {
        super("WHERE", whereClause);
    }

}
