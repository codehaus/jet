package org.codehaus.jet.web.actions;

import com.opensymphony.xwork.Action;

/**
 * <p>
 * JetAction is the base action interface for all Jet actions.
 * </p>
 * <p>
 * JetAction extends {@link Action Action}, adding any application specific constants, forward, etc.
 * </p>
 * @author Mauro Talevi
 */
public interface JetAction extends Action {

    public static final String NULL = "null";

}
