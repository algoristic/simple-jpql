package de.algoristic.jpql.sql;

import java.util.stream.Stream;

import de.algoristic.jpql.render.Renderable;

public interface OperationalClause<T extends Renderable> extends Iterable<T>, Renderable {

    Stream<T> stream();

}
