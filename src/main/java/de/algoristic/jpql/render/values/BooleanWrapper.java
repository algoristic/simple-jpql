package de.algoristic.jpql.render.values;

public class BooleanWrapper extends LiteralValueWrapper {

    private Boolean value;

    public BooleanWrapper(Boolean value) {
        super();
        this.value = value;
    }

    @Override
    protected Object getLiteralValue() {
        if (value == null) {
            return "null";
        } else {
            String value = this.value.toString();
            return value.toUpperCase();
        }
    }

}
