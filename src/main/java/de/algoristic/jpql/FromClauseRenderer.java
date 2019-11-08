package de.algoristic.jpql;


public class FromClauseRenderer {
    
    private FromClause fromClause;

    public FromClauseRenderer(FromClause fromClause) {
        this.fromClause = fromClause;
    }

    public String render() {
        StringBuilder sb = new StringBuilder("FROM").append(" ");
        for (Table table : fromClause) {
            sb.append(table.toString())
                .append(", ");
        }
        sb = new StringBuilder(sb.substring(0, (sb.length() - 2))); //remove single trailing comma
        return sb.toString();
    }

}
