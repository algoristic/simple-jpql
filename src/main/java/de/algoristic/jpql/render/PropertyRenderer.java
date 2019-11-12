package de.algoristic.jpql.render;

import de.algoristic.jpql.Property;

public class PropertyRenderer implements Renderer {

    private Property property;

    public PropertyRenderer(Property property) {
        this.property = property;
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append(property.getTableAlias());
        sb.append(".");
        sb.append(property.getName());
        return sb.toString();
    }

}
