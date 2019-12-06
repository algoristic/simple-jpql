package de.algoristic.jpql;

import de.algoristic.jpql.render.FunctionRenderer;
import de.algoristic.jpql.render.Renderable;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.sql.CommonFunctions;
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

    public static Property count(Property property) {
        return new Function(property, CommonFunctions.COUNT);
    }

}
