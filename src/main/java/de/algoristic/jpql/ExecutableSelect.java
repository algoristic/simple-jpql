package de.algoristic.jpql;

public class ExecutableSelect extends Select {

    public ExecutableSelect(SelectClause selectClause, FromClause fromClause) {
        super(selectClause);
        this.fromClause = fromClause;
    }

    public String query() {
        StatementPreparator.prepareQuery(this);
        StringBuilder sb = new StringBuilder();
        sb.append(selectClause.render());
        sb.append(fromClause.render());
        String qlString = sb.toString();
        return qlString;
    }

}
