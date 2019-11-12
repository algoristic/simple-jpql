package de.algoristic.jpql.render;

import de.algoristic.jpql.Property;
import de.algoristic.jpql.conditions.PropertyCondition;

public class PropertyConditionRenderer extends ConditionRenderer {
    
    private PropertyCondition condition;
    

    public PropertyConditionRenderer(PropertyCondition condition) {
        this.condition = condition;
    }


    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        Property property = condition.getProperty();
        Renderer propertyRenderer = property.getRenderer();
        sb.append(propertyRenderer.render());
        String operator = condition.getOperator();
        sb.append(" ").append(operator).append(" ");
        Object value = condition.getValue();
        sb.append(value);
        sb.append(")");
        return sb.toString();
    }

}
