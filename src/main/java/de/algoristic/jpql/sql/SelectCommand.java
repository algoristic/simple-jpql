package de.algoristic.jpql.sql;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.Select;
import de.algoristic.jpql.render.SQLRenderer;

public class SelectCommand extends Select {

    private WhereClause whereClause;

    public SelectCommand(SelectClause selectClause, FromClause fromClause) {
        super(selectClause);
        this.fromClause = fromClause;
        this.whereClause = WhereClause.noConditions;
    }

    public WhereClause getWhereClause() {
        return whereClause;
    }

    public SelectCommand where(Condition condition) {
        this.whereClause = new WhereClause(condition);
        return this;
    }

    public String query() {
        StatementPreparator.prepareQuery(this);
        SQLRenderer renderer = new SQLRenderer(this);
        return renderer.render();
    }

}
