package de.algoristic.jpql.render;

import de.algoristic.jpql.sql.FromClause;

public class FromClauseRenderer extends FullClauseRenderer {
    
    public FromClauseRenderer(FromClause fromClause) {
        super("FROM", fromClause);
    }

}
