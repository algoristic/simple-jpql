package de.algoristic.jpql;

public class NonBindingProperty extends Property {

    private String tableAlias;

    public NonBindingProperty(String name, String tableAlias) {
        super(name);
        this.tableAlias = tableAlias;
    }

}
