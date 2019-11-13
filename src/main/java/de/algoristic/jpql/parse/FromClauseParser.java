package de.algoristic.jpql.parse;

import java.util.List;

import de.algoristic.jpql.Table;
import de.algoristic.jpql.sql.FromClause;
import de.algoristic.jpql.util.RandomStringProvider;

public class FromClauseParser implements QualifierParser<FromClause> {

    private QualifierParser<List<Table>> tablesParser;

    public FromClauseParser(RandomStringProvider rsp) {
        this.tablesParser = new TablesParser(rsp);
    }

    @Override
    public FromClause parse(String s) {
        List<Table> tables = tablesParser.parse(s);
        return new FromClause(tables);
    }

}
