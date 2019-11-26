package de.algoristic.jpql;

import java.util.List;

import de.algoristic.jpql.conditions.ConditionSelector;
import de.algoristic.jpql.render.PropertyRenderer;
import de.algoristic.jpql.render.Renderable;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.sql.BoundedProperty;
import de.algoristic.jpql.sql.SharedQueryInformation;
import de.algoristic.jpql.sql.UnboundedProperty;

public abstract class Property implements Renderable {

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

    public abstract void preProcess(SharedQueryInformation queryInfo);

    static Property of(Table table, String name) {
        return new BoundedProperty(name, table);
    }

    static Property of(String name) {
        return new UnboundedProperty(name);
    }

    public Condition isEquals(Object object) {
        return ongoingSelector.isEquals(object);
    }

    public Condition isEquals(Number value) {
        return ongoingSelector.isEquals(value);
    }

    public Condition isEquals(String value) {
        return ongoingSelector.isEquals(value);
    }

    public Condition isEquals(Boolean value) {
        return ongoingSelector.isEquals(value);
    }

    public Condition isEquals(Property value) {
        return ongoingSelector.isEquals(value);
    }

    public Condition isEquals(Table table) {
        return ongoingSelector.isEquals(table);
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
