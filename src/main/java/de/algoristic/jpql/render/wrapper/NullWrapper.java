package de.algoristic.jpql.render.wrapper;

import de.algoristic.jpql.sql.SharedQueryInformation;

public class NullWrapper implements SQLDisplayWrapper {

    @Override
    public Object getValue() {
        return "NULL";
    }

    @Override
    public void preProcess(SharedQueryInformation queryInfo) {
        //this has no effect here
    }

}
