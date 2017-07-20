package py.com.datapar.maestro.domain.commons.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WorkflowBean implements WorkflowInstance, Cloneable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z")
    private Date stageStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z")
    private Date stageEnd;
    private STATE state;
    private String name;
    private String correlatedId;
    private String lastNode;
    private ConcurrentHashMap<String,Object> context = new ConcurrentHashMap<>();

    @Override
    public String getCorrelatedId() {
        return correlatedId;
    }

    @Override
    public WorkflowInstance setCorrelatedId(String correlatedId) {
        this.correlatedId = correlatedId;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public WorkflowInstance setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public WorkflowInstance setParameters(Map<String, Object> parameters) {
        context.putAll(parameters);
        return this;
    }

    @Override
    public String getLastNode() {
        return null;
    }

    @Override
    public WorkflowInstance setLastNode(String lastNode) {
        this.lastNode = lastNode;
        return this;
    }

    @Override
    public Map<String, Object> getContext() {
        return context;
    }

    @Override
    public WorkflowInstance setContext(Map<String, Object> map) {
        return setParameters(map);
    }

    @Override
    public STATE getState() {
        return state;
    }

    @Override
    public WorkflowInstance setState(STATE state) {
        this.state = state;
        return this;
    }

    @Override
    public Date getStageStart() {
        return stageStart;
    }

    @Override
    public WorkflowInstance setStageStart(Date stageStart) {
        this.stageStart = stageStart;
        return this;
    }

    @Override
    public Date getStageEnd() {
        return stageEnd;
    }

    @Override
    public WorkflowInstance setStageEnd(Date stageEnd) {
        this.stageEnd = stageEnd;
        return this;
    }
}
