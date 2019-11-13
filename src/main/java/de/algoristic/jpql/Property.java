package de.algoristic.jpql;

import java.util.List;

import de.algoristic.jpql.conditions.ConditionSelector;
import de.algoristic.jpql.parse.PropertyParser;
import de.algoristic.jpql.parse.QualifierParser;
import de.algoristic.jpql.render.PropertyRenderer;
import de.algoristic.jpql.render.Renderable;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.sql.BoundedProperty;
import de.algoristic.jpql.sql.QueryInformation;
import de.algoristic.jpql.sql.UnboundedProperty;

public abstract class Property implements Renderable {

    private static QualifierParser<Property> propertyParser = new PropertyParser();

    private ConditionSelector ongoingSelector;
    protected String name;

    protected Property(String name) {
        this.name = name;
        ongoingSelector = new ConditionSelector(this);
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

    public abstract void preProcess(QueryInformation queryInfo);

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

    public Condition equals(Number value) {
        return ongoingSelector.equals(value);
    }

    public Condition equals(String value) {
        return ongoingSelector.equals(value);
    }

    public Condition equals(Boolean value) {
        return ongoingSelector.equals(value);
    }

    public Condition equals(Property value) {
        return ongoingSelector.equals(value);
    }

    public Condition lessThan(Number value) {
        return ongoingSelector.lessThan(value);
    }

    public Condition lessThan(Property value) {
        return ongoingSelector.lessThan(value);
    }

    public Condition lessEquals(Number value) {
        return ongoingSelector.lessEquals(value);
    }

    public Condition lessEquals(Property value) {
        return ongoingSelector.lessEquals(value);
    }

    public Condition greaterThan(Number value) {
        return ongoingSelector.greaterThan(value);
    }

    public Condition greaterThan(Property value) {
        return ongoingSelector.greaterThan(value);
    }

    public Condition greaterEquals(Number value) {
        return ongoingSelector.greaterEquals(value);
    }

    public Condition greaterEquals(Property value) {
        return ongoingSelector.greaterEquals(value);
    }

    public Condition like(String value) {
        return ongoingSelector.like(value);
    }

    public Condition like(Property value) {
        return ongoingSelector.like(value);
    }

    public Condition between(String firstValue, String secondValue) {
        return ongoingSelector.between(firstValue, secondValue);
    }

    public Condition between(Number firstValue, Number secondValue) {
        return ongoingSelector.between(firstValue, secondValue);
    }

    public Condition between(Property firstValue, Property secondValue) {
        return ongoingSelector.between(firstValue, secondValue);
    }

    public Condition isNull() {
        return ongoingSelector.isNull();
    }

    public Condition in(Object... values) {
        return ongoingSelector.in(values);
    }

    public Condition in(List<Object> list) {
        return ongoingSelector.in(list);
    }

}
