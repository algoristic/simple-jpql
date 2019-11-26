package de.algoristic.jpql;

import de.algoristic.jpql.conditions.ConditionSelector;
import de.algoristic.jpql.render.Renderable;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.render.TableRenderer;
import de.algoristic.jpql.sql.FullTableProperty;
import de.algoristic.jpql.util.NumberProvider;

public class Table implements Renderable {

    private String name;
    private String alias;

    private ConditionSelector ongoingSelector;

    public Table(Class<?> clazz) {
        this.name = determineTableName(clazz);
        this.alias = createAlias(name);
        this.ongoingSelector = new ConditionSelector(new FullTableProperty(this));
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean hasEmptyAlias() {
        return (alias == null) ? true : alias.trim().equals("");
    }

    public boolean matching(Table other) {
        return this.name.equals(other.name);
    }

    public Property property(String name) {
        return Property.of(this, name);
    }

    public Property all() {
        return new FullTableProperty(this);
    }

    public Condition isEquals(Property property) {
        return ongoingSelector.isEquals(property);
    }

    @Override
    public String toString() {
        return this.getRenderer().render();
    }

    @Override
    public Renderer getRenderer() {
        return new TableRenderer(this);
    }

    public static Table of(Class<?> clazz) {
        return new Table(clazz);
    }

    public static String createAlias(String name) {
        String alias = name.toLowerCase();
        alias += "_";
        NumberProvider numberProvider = NumberProvider.getInstance();
        alias += numberProvider.getNumber();
        return alias;
    }

    private static String determineTableName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

}
