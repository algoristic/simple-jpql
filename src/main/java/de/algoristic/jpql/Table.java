package de.algoristic.jpql;

import de.algoristic.jpql.parse.TableParser;
import de.algoristic.jpql.render.Renderable;
import de.algoristic.jpql.render.Renderer;
import de.algoristic.jpql.render.TableRenderer;

public class Table implements Renderable {

    private String name;
    private String alias;

    public Table(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Table as(String alias) {
        this.alias = alias;
        return this;
    }
    
    public boolean matching(Table other) {
        return this.name.equals(other.name);
    }
    
    @Override
    public String toString() {
        return this.getRenderer().render();
    }
    
    @Override
    public Renderer getRenderer() {
        return new TableRenderer(this);
    }

    public static Table of(String name) {
        Table tmpTable = TableParser.tryParse(name);
        name = tmpTable.getName();
        String alias = tmpTable.getAlias();
        return of(name, alias);
    }

    public static Table of(String name, String alias) {
        return new Table(name, alias);
    }

    public static Table of(Class<?> clazz) {
        return of(clazz, null);
    }

    public static Table of(Class<?> clazz, String alias) {
        String name = TableParser.determineTableName(clazz);
        return of(name, alias);
    }

}
