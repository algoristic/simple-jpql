package de.algoristic.jpql.sql;

import de.algoristic.jpql.render.Renderable;

public interface OptionalClause<T extends Renderable> extends OperationalClause<T> {

    boolean containsStatements();

}
