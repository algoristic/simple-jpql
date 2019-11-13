package de.algoristic.jpql.parse;

import java.util.ArrayList;
import java.util.List;

import de.algoristic.jpql.Table;
import de.algoristic.jpql.util.RandomStringProvider;

public class TablesParser implements QualifierParser<List<Table>> {

    private QualifierParser<Table> tableParser;

    public TablesParser(RandomStringProvider rsp) {
        this.tableParser = new TableParser(rsp);
    }

    @Override
    public List<Table> parse(String s) {
        s = s.trim();
        List<Table> tables = new ArrayList<>();
        String[] tableTokens = s.split(",");
        for (String tableToken : tableTokens) {
            Table table = tableParser.parse(tableToken);
            tables.add(table);
        }
        return tables;
    }

}
