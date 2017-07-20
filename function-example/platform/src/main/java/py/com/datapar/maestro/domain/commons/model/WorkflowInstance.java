package py.com.datapar.maestro.domain.commons.model;

import java.util.Date;
import java.util.Map;

public interface WorkflowInstance {
    String WF_ACTION_LOGGER = "WF_ACTION_LOGGER";
    String WF_CURRENT_ACTION = "WF_CURRENT_ACTION";
    String WF_INSTANCE = "WF_INSTANCE";

    enum STATE {
        PAUSED,
        RUNNING,
        ERROR,
        COMPLETE;
    }

    String getCorrelatedId();
    WorkflowInstance setCorrelatedId(String correlatedId);

    String getName();
    WorkflowInstance setName(String name);

    WorkflowInstance setParameters(Map<String, Object> parameters);

    String getLastNode() ;
    WorkflowInstance setLastNode(String lastNode);

    Map<String, Object> getContext();
    WorkflowInstance setContext(Map<String, Object> map);

    STATE getState();
    WorkflowInstance setState(STATE state);

    Date getStageStart();
    WorkflowInstance setStageStart(Date stageStart);

    Date getStageEnd();
    WorkflowInstance setStageEnd(Date stageEnd);
}

