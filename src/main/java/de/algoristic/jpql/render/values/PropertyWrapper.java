package de.algoristic.jpql.render.values;

import de.algoristic.jpql.Property;
import de.algoristic.jpql.render.Renderer;

public class PropertyWrapper implements SQLDisplayWrapper {
    
    private Property property;

    @Override
    public Object getValue() {
        Renderer renderer = property.getRenderer();
        return renderer.render();
    }

}
