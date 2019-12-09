package de.algoristic.jpql;

import de.algoristic.jpql.render.FunctionRenderer;
import de.algoristic.jpql.render.Renderable;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.sql.AggregateFunctions;
import de.algoristic.jpql.sql.SQLFunction;
import de.algoristic.jpql.sql.SharedQueryInformation;

public class Function extends Property implements Renderable {

    private Property affectedProperty;
    private SQLFunction function;

    protected Function(Property property, SQLFunction function) {
        super(property.getName());
        this.affectedProperty = property;
        this.function = function;
    }

    public Function(Table table, SQLFunction function) {
        this(table.all(), function);
    }

    public Property getAffectedProperty() {
        return affectedProperty;
    }

    public SQLFunction getFunction() {
        return function;
    }

    @Override
    public Renderer getRenderer() {
        return new FunctionRenderer(this);
    }

    @Override
    public String getTableAlias() {
        return affectedProperty.getTableAlias();
    }

    @Override
    public void preProcess(SharedQueryInformation queryInfo) {
        affectedProperty.preProcess(queryInfo);
    }

    public static Property min(Property property) {
        return new Function(property, AggregateFunctions.MIN);
    }

    public static Property min(Table table) {
        return new Function(table, AggregateFunctions.MIN);
    }

    public static Property max(Property property) {
        return new Function(property, AggregateFunctions.MAX);
    }

    public static Property max(Table table) {
        return new Function(table, AggregateFunctions.MAX);
    }

    public static Property avg(Property property) {
        return new Function(property, AggregateFunctions.AVG);
    }

    public static Property avg(Table table) {
        return new Function(table, AggregateFunctions.AVG);
    }

    public static Property sum(Property property) {
        return new Function(property, AggregateFunctions.SUM);
    }

    public static Property sum(Table table) {
        return new Function(table, AggregateFunctions.SUM);
    }

    public static Property count(Property property) {
        return new Function(property, AggregateFunctions.COUNT);
    }

    public static Property count(Table table) {
        return new Function(table, AggregateFunctions.COUNT);
    }

}
