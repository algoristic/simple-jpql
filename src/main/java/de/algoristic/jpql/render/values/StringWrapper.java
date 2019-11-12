package de.algoristic.jpql.render.values;

public class StringWrapper implements SQLDisplayWrapper {

    private String string;

    public StringWrapper(String string) {
        this.string = string;
    }

    @Override
    public Object getValue() {
        return "\'" + string + "\'";
    }

}
