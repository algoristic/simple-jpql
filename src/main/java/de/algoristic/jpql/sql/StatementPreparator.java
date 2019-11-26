package de.algoristic.jpql.sql;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.Property;

abstract class StatementPreparator {
    
    public static SharedQueryInformation prepareQuery(SelectCommand select) {
        FromClause fromClause = select.getFromClause();
        SharedQueryInformation queryInfo = new SharedQueryInformation();
        queryInfo.setTableData(fromClause);
        SelectClause selectClause = select.getSelectClause();
        for(Property property: selectClause) {
            property.preProcess(queryInfo);
        }
        WhereClause whereClause = select.getWhereClause();
        for(Condition condition: whereClause) {
            condition.preProcess(queryInfo);
        }
        return queryInfo;
    }

}
