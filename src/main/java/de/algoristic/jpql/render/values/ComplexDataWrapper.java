package de.algoristic.jpql.render.values;

import de.algoristic.jpql.sql.QueryInformation;

public class ComplexDataWrapper implements SQLDisplayWrapper {

    private Object obj;

    public ComplexDataWrapper(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object getValue() {
        return obj;
    }

    @Override
    public void preProcess(QueryInformation queryInfo) {
        // TODO this has no effect here
    }

}
