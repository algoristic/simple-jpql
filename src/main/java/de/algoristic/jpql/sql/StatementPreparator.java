package de.algoristic.jpql.sql;

import de.algoristic.jpql.Property;
import de.algoristic.jpql.Select;
import de.algoristic.jpql.util.RandomStringProvider;

abstract class StatementPreparator {
    
    public static void prepareQuery(Select select) {
        FromClause fromClause = select.getFromClause();
        RandomStringProvider rsp = new RandomStringProvider();
        rsp.init(fromClause);
        for(Table table: fromClause) {
            String alias = table.getAlias();
            if(alias == null) {
                alias = rsp.getRandom();
                table.setAlias(alias);
            }
        }
        SelectClause selectClause = select.getSelectClause();
        for(Property property: selectClause) {
            property.completeReferences(fromClause);
        }
    }

}
