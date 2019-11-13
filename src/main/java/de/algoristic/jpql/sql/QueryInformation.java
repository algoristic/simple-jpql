package de.algoristic.jpql.sql;

import java.util.ArrayList;
import java.util.List;

import de.algoristic.jpql.Table;

public class QueryInformation {

    private List<Table> affectedTables;

    public QueryInformation() {
        affectedTables = new ArrayList<>();
    }

    List<Table> getAffectedTables() {
        return affectedTables;
    }

    public void setTableData(FromClause fromClause) {
        affectedTables = fromClause.getTables();
    }

}
