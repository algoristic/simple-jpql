package de.algoristic.jpql.render;

import de.algoristic.jpql.Function;
import de.algoristic.jpql.Property;
import de.algoristic.jpql.sql.SQLFunction;

public class FunctionRenderer implements Renderer {

    private SQLFunction function;
    private Property affectedProperty;

    public FunctionRenderer(Function function) {
        this.function = function.getFunction();
        this.affectedProperty = function.getAffectedProperty();
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append(function.getName());
        sb.append("(");
        {
            Renderer propertyRenderer = affectedProperty.getRenderer();
            String renderedProperty = propertyRenderer.render();
            sb.append(renderedProperty);
        }
        sb.append(")");
        return sb.toString();
    }

}
