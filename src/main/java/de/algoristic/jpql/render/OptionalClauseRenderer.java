package de.algoristic.jpql.render;

import de.algoristic.jpql.sql.OptionalClause;

public abstract class OptionalClauseRenderer extends FullClauseRenderer {

    private OptionalClause<?> optionalClause;

    protected OptionalClauseRenderer(String clausePrefix, OptionalClause<?> optionalClause) {
        super(clausePrefix, optionalClause);
        this.optionalClause = optionalClause;
    }

    @Override
    public String render() {
        if (optionalClause.containsStatements()) {
            return super.render();
        } else {
            return "";
        }
    }

}
