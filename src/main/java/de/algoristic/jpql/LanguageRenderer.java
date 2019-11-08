package de.algoristic.jpql;


public class LanguageRenderer {
    
    private ExecutableSelect executableSelect;

    LanguageRenderer(ExecutableSelect executableSelect) {
        this.executableSelect = executableSelect;
    }
    
    String render() {
        SelectClause selectClause = executableSelect.getSelectClause();
        SelectClauseRenderer selectClauseRenderer = new SelectClauseRenderer(selectClause);
        FromClause fromClause = executableSelect.getFromClause();
        FromClauseRenderer fromClauseRenderer = new FromClauseRenderer(fromClause);
        StringBuilder sb = new StringBuilder();
        sb.append(selectClauseRenderer.render());
        sb.append(fromClauseRenderer.render());
        return sb.toString();
    }

}
