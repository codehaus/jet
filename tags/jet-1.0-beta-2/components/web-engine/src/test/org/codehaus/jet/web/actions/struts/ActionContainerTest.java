package org.codehaus.jet.web.actions.struts;

import java.io.InputStreamReader;
import java.io.Reader;

import junit.framework.TestCase;

import org.codehaus.jet.JetEngine;
import org.nanocontainer.script.ScriptedContainerBuilder;
import org.nanocontainer.script.xml.XMLContainerBuilder;
import org.picocontainer.PicoContainer;
import org.picocontainer.defaults.ObjectReference;
import org.picocontainer.defaults.SimpleReference;


/**
 * @author Mauro Talevi
 */
public class ActionContainerTest extends TestCase {

    public void testInstantiationOfActions() throws Exception {
            PicoContainer pico = buildContainer(getScript("org/codehaus/jet/web/actions/struts/container.xml"));
            assertNotNull(pico.getComponentInstanceOfType(JetEngine.class));
    }

    protected PicoContainer buildContainer(Reader script) {
        ObjectReference containerRef = new SimpleReference();
        ObjectReference parentContainerRef = new SimpleReference();
        ScriptedContainerBuilder builder = new XMLContainerBuilder(script, getClass().getClassLoader());
        parentContainerRef.set(null);
        builder.buildContainer(containerRef, parentContainerRef, "scope", true);
        return (PicoContainer) containerRef.get();
    }

    private Reader getScript(String resource) {
        return new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(resource));
    }

}         