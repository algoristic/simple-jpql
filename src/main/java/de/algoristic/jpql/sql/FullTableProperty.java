package de.algoristic.jpql.sql;

import de.algoristic.jpql.Table;
import de.algoristic.jpql.render.Renderer;

public class FullTableProperty extends BoundedProperty {

    public FullTableProperty(Table table) {
        super("*", table);
    }

    @Override
    public Renderer getRenderer() {
        return new Renderer() {

            @Override
            public String render() {
                return table.getAlias();
            }

        };
    }

}
