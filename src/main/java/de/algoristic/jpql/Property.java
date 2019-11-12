package de.algoristic.jpql;

import de.algoristic.jpql.parse.PropertyParser;
import de.algoristic.jpql.parse.QualifierParser;
import de.algoristic.jpql.render.PropertyRenderer;
import de.algoristic.jpql.render.Renderable;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.sql.BoundedProperty;
import de.algoristic.jpql.sql.FromClause;
import de.algoristic.jpql.sql.Table;
import de.algoristic.jpql.sql.UnboundedProperty;

public abstract class Property implements Renderable {

    private static QualifierParser<Property> propertyParser = new PropertyParser();

    protected String name;

    protected Property(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getRenderer().render();
    }

    @Override
    public Renderer getRenderer() {
        return new PropertyRenderer(this);
    }

    abstract public String getTableAlias();

    public abstract void completeReferences(FromClause fromClause);

    public static Property of(String qualifier) {
        return propertyParser.parse(qualifier);
    }

    public static Property of(String tableAlias, String name) {
        return new UnboundedProperty(name, tableAlias);
    }

    public static Property of(Class<?> clazz, String name) {
        return new BoundedProperty(name, clazz);
    }

    public static Property of(Table table, String name) {
        return new BoundedProperty(name, table);
    }

}
