package de.algoristic.jpql.render;

import de.algoristic.jpql.sql.SelectClause;

public class SelectClauseRenderer extends FullClauseRenderer {

    public SelectClauseRenderer(SelectClause selectClause) {
        super("SELECT", selectClause);
    }

}
