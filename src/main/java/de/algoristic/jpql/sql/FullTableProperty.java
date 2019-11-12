package de.algoristic.jpql.sql;

import de.algoristic.jpql.Property;
import de.algoristic.jpql.render.Renderer;

public class FullTableProperty extends Property {

    private Table table;

    public FullTableProperty(Table table) {
        super("*");
        this.table = table;
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

    @Override
    public String getTableAlias() {
        return table.getAlias();
    }

    @Override
    public void completeReferences(FromClause fromClause) {
        //this property gets initialized, when all references are already set
    }

}
