package de.algoristic.jpql.render.values;

public class ComplexDataWrapper implements SQLDisplayWrapper {

    private Object obj;

    public ComplexDataWrapper(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object getValue() {
        return obj;
    }

}
