package org.codehaus.jet.web.forms.struts;

import org.apache.struts.util.LabelValueBean;
import org.jmock.MockObjectTestCase;

/**
 * @author Mauro Talevi
 */
public abstract class AbstractFormTestCase extends MockObjectTestCase {

    protected LabelValueBean[] createLabelValueBeans(String[] values) {
        LabelValueBean[] beans = new LabelValueBean[values.length];
        for (int i = 0; i < values.length; i++) {
            beans[i] = new LabelValueBean(values[i], values[i]);
        }
        return beans;
    }

}
