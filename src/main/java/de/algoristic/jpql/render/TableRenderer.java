package de.algoristic.jpql.render;

import de.algoristic.jpql.Table;

public class TableRenderer implements Renderer {

    private Table table;

    public TableRenderer(Table table) {
        this.table = table;
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append(table.getName());
        sb.append(" ");
        sb.append(table.getAlias());
        return sb.toString();
    }

}
