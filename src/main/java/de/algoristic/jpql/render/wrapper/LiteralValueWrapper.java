package de.algoristic.jpql.render.wrapper;

import de.algoristic.jpql.sql.QueryInformation;

public abstract class LiteralValueWrapper implements SQLDisplayWrapper {

    @Override
    public void preProcess(QueryInformation queryInfo) {
        // TODO Auto-generated method stub
    }

    @Override
    public Object getValue() {
        return getLiteralValue(); // temp implementation
    }
    
    protected abstract Object getLiteralValue();

}
