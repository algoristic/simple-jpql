package de.algoristic.jpql;

public class ExecutableSelect extends Select {
    
    public ExecutableSelect(SelectClause selectClause, FromClause fromClause) {
        super(selectClause);
        this.fromClause = fromClause;
    }

    public String query() {
        StatementPreparator.prepareQuery(this);
        LanguageRenderer renderer = new LanguageRenderer(this);
        return renderer.render();
    }

}
