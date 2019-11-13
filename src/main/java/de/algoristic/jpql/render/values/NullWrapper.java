package de.algoristic.jpql.render.values;

import de.algoristic.jpql.sql.QueryInformation;

public class NullWrapper implements SQLDisplayWrapper {

    @Override
    public Object getValue() {
        return "NULL";
    }

    @Override
    public void preProcess(QueryInformation queryInfo) {
        //this has no effect here
    }

}
