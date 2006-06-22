package org.codehaus.jet.web.forms.struts;

import org.apache.struts.action.ActionForm;

/**
 * Base Jet Struts Action form
 * 
 * @author Mauro Talevi
 */
public class StrutsJetForm extends ActionForm {

    public static final String CANCEL = "cancel";
    public static final String CREATE = "create";
    public static final String DELETE = "delete";
    public static final String EXPORT = "export";
    public static final String IMPORT = "import";
    public static final String QUERY = "query";
    public static final String SAVE = "save";
    public static final String SCHEDULE = "schedule";
    public static final String START = "start";
    public static final String STOP = "stop";
    public static final String UPDATE = "update";
    public static final String NONE = "none";

    /** User form action */
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    /** User form action target */
    private String actionTarget;

    public String getActionTarget() {
        return actionTarget;
    }

    public void setActionTarget(String actionTarget) {
        this.actionTarget = actionTarget;
    }
    
    /**
     * Determines whether the action parameter has a given value
     * 
     * @param value the value of the action parameter
     */
    protected final boolean isAction(String value) {
        if (value.equals(getAction())) {
            return true;
        }
        return false;
    }
    
    /**
     * Determines whether the action target parameter has a given value
     * 
     * @param value the value of the action target parameter
     */
    protected final boolean isActionTarget(String value) {
        if (value.equals(getActionTarget())) {
            return true;
        }
        return false;
    }    
    
    /**
     * Convenience method to determine whether the Cancel button was pressed.
     */
    public final boolean cancelAction() {
        return isAction(CANCEL);
    }

    /**
     * Convenience method to determine whether the Create button was pressed.
     */
    public final boolean createAction() {
        return isAction(CREATE);
    }
    
    /**
     * Convenience method to determine whether the Delete button was pressed.
     */
    public final boolean deleteAction() {
        return isAction(DELETE);
    }

    /**
     * Convenience method to determine whether the Export button was pressed.
     */
    public boolean exportAction() {
        return isAction(EXPORT);
    }

    /**
     * Convenience method to determine whether the Import button was pressed.
     */
    public boolean importAction() {
        return isAction(IMPORT);
    }

    /**
     * Convenience method to determine whether the Query button was pressed.
     */
    public final boolean queryAction() {
        return isAction(QUERY);
    }
    
    /**
     * Convenience method to determine whether the Save button was pressed.
     */
    public final boolean saveAction() {
        return isAction(SAVE);
    }
    
    /**
     * Convenience method to determine whether the Schedule button was pressed.
     */
    public final boolean scheduleAction() {
        return isAction(SCHEDULE);
    }
    
    /**
     * Convenience method to determine whether the Start button was pressed.
     */
    public final boolean startAction() {
        return isAction(START);
    }

    /**
     * Convenience method to determine whether the Stop button was pressed.
     */
    public final boolean stopAction() {
        return isAction(STOP);
    }
    
    /**
     * Convenience method to determine whether the Update button was pressed.
     */
    public final boolean updateAction() {
        return isAction(UPDATE);
    }

    /**
     * Resets action
     */
    public final void resetAction() {
        this.setAction(NONE);
    }
}
