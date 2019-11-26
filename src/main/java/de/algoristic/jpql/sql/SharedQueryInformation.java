package de.algoristic.jpql.sql;

import java.util.ArrayList;
import java.util.List;

import de.algoristic.jpql.Table;

public class SharedQueryInformation {

    private List<Table> affectedTables;
    List<ComplexParameter> complexParameters;

    public SharedQueryInformation() {
        affectedTables = new ArrayList<>();
        complexParameters = new ArrayList<>();
    }

    List<Table> getAffectedTables() {
        return affectedTables;
    }

    public void setTableData(FromClause fromClause) {
        affectedTables = fromClause.getTables();
    }

    public boolean add(ComplexParameter e) {
        return complexParameters.add(e);
    }

    public List<ComplexParameter> getComplexParameters() {
        return complexParameters;
    }

}
