package de.algoristic.jpql.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.algoristic.jpql.Property;
import de.algoristic.jpql.Select;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.render.SelectAllClauseRenderer;
import de.algoristic.jpql.render.SelectClauseRenderer;

public class SelectClause implements OperationalClause<Property> {

    private Select parentOperation;
    private List<Property> properties;

    public SelectClause(Select parentOperation) {
        this.parentOperation = parentOperation;
        this.properties = new ArrayList<>();
    }

    public SelectClause(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public Iterator<Property> iterator() {
        return properties.iterator();
    }

    public Select getParentOperation() {
        return parentOperation;
    }

    public void setParentOperation(Select parentOperation) {
        this.parentOperation = parentOperation;
    }

    @Override
    public Renderer getRenderer() {
        if (properties.size() < 1) {
            return new SelectAllClauseRenderer(this);
        } else {
            return new SelectClauseRenderer(this);
        }
    }

}
