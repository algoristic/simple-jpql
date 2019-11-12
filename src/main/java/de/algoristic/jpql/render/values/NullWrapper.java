package de.algoristic.jpql.render.values;

public class NullWrapper implements SQLDisplayWrapper {

    @Override
    public Object getValue() {
        return "NULL";
    }

}
