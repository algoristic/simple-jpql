package de.algoristic.jpql.render;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.algoristic.jpql.sql.OperationalClause;

public abstract class FullClauseRenderer implements Renderer {

    private String clausePrefix;
    private OperationalClause<?> operationalClause;

    protected FullClauseRenderer(String clausePrefix, OperationalClause<?> operationalClause) {
        this.clausePrefix = clausePrefix;
        this.operationalClause = operationalClause;
    }

    @Override
    public String render() {
        return render(operationalClause.stream().map(obj -> (Renderable) obj));
    }
    
    protected String render(Stream<Renderable> renderableStream) {
        StringBuilder sb = new StringBuilder(clausePrefix).append(" ");
        String props = renderableStream.map(Renderable::getRenderer)
            .map(Renderer::render)
            .collect(Collectors.joining(", "));
        sb.append(props);
        return sb.toString();
    }

}
