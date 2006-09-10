package org.codehaus.jet.web.forms.struts;

import junit.framework.TestCase;

/**
 * @author Mauro Talevi
 */
public class StrutsJetFormTest extends TestCase {


    public void testAction(){
        StrutsJetForm form = new StrutsJetForm();
        form.setAction(StrutsJetForm.CANCEL);
        assertTrue(form.cancelAction());
        form.setAction(StrutsJetForm.CREATE);
        assertTrue(form.createAction());
        form.setAction(StrutsJetForm.DELETE);
        assertTrue(form.deleteAction());
        form.setAction(StrutsJetForm.EXPORT);
        assertTrue(form.exportAction());
        form.setAction(StrutsJetForm.IMPORT);
        assertTrue(form.importAction());
        form.setAction(StrutsJetForm.QUERY);
        assertTrue(form.queryAction());
        form.setAction(StrutsJetForm.SCHEDULE);
        assertTrue(form.scheduleAction());
        form.setAction(StrutsJetForm.SAVE);
        assertTrue(form.saveAction());
        form.setAction(StrutsJetForm.START);
        assertTrue(form.startAction());
        form.setAction(StrutsJetForm.STOP);
        assertTrue(form.stopAction());
        form.setAction(StrutsJetForm.UPDATE);
        assertTrue(form.updateAction());
        assertFalse(form.isAction("no-action"));
        form.resetAction();
        assertTrue(form.isAction(StrutsJetForm.NONE));
    }

    public void testActionTarget(){
        StrutsJetForm form = new StrutsJetForm();
        String target = "actionTarget1";
        form.setActionTarget(target);
        assertTrue(form.isActionTarget(target));
        assertFalse(form.isActionTarget("no-action-target"));
    }   
    
}
