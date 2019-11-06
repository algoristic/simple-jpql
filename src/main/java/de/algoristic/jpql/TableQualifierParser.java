package de.algoristic.jpql;

class TableQualifierParser implements QualifierParser<Table> {

    private RandomStringProvider rsp;

    public TableQualifierParser(RandomStringProvider rsp) {
        this.rsp = rsp;
    }

    public Table parse(String s) {
        s = s.trim();
        String[] tokens = s.split(" ");
        String name = tokens[0]; //TODO: NPE? => in case of "" as tablename
        String alias;
        if(tokens.length > 1) {
            alias = tokens[1];
        } else {
            alias = rsp.getRandom();
        }
        return new Table(name, alias);
    }

}
