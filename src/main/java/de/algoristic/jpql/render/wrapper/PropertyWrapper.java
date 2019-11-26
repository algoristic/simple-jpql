package de.algoristic.jpql.render.wrapper;

import de.algoristic.jpql.Property;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.sql.SharedQueryInformation;

public class PropertyWrapper implements SQLDisplayWrapper {

    private Property property;

    public PropertyWrapper(Property property) {
        this.property = property;
    }

    @Override
    public Object getValue() {
        Renderer renderer = property.getRenderer();
        return renderer.render();
    }

    @Override
    public void preProcess(SharedQueryInformation queryInfo) {
        property.preProcess(queryInfo);
    }

}
