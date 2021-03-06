package de.algoristic.jpql.render.wrapper;

public class StringWrapper extends LiteralValueWrapper {

    private String string;

    public StringWrapper(String string) {
        this.string = string;
    }

    @Override
    protected Object getLiteralValue() {
        return "\'" + string + "\'";
    }

}
