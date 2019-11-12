package de.algoristic.jpql.conditions;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.Property;
import de.algoristic.jpql.render.PropertyConditionRenderer;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.render.values.SQLDisplayWrapper;
import de.algoristic.jpql.sql.FromClause;

public class PropertyCondition implements Condition {

    private String operator;
    private Property property;
    private SQLDisplayWrapper wrapper;

    public PropertyCondition(String operator, Property property, SQLDisplayWrapper wrapper) {
        this.operator = operator;
        this.property = property;
        this.wrapper = wrapper;
    }

    @Override
    public Renderer getRenderer() {
        return new PropertyConditionRenderer(this);
    }

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public void completeReferences(FromClause fromClause) {
        property.completeReferences(fromClause);
    }

    public Property getProperty() {
        return property;
    }

    public Object getValue() {
        return wrapper.getValue();
    }

}
