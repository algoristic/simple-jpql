package de.algoristic.jpql.parse;

import java.util.ArrayList;
import java.util.List;

import de.algoristic.jpql.Table;
import de.algoristic.jpql.sql.FromClause;
import de.algoristic.jpql.util.RandomStringProvider;

public class FromClauseParser implements QualifierParser<FromClause> {

    private QualifierParser<Table> tableParser;

    public FromClauseParser(RandomStringProvider rsp) {
        this.tableParser = new TableParser(rsp);
    }

    @Override
    public FromClause parse(String s) {
        s = s.trim();
        List<Table> tables = new ArrayList<>();
        String[] tableTokens = s.split(",");
        for(String tableToken: tableTokens) {
            Table table = tableParser.parse(tableToken);
            tables.add(table);
        }
        return new FromClause(tables);
    }

}
