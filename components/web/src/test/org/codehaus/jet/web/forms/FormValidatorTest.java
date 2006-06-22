package org.codehaus.jet.web.forms;

import junit.framework.TestCase;

/**
 * @author Mauro Talevi
 */
public class FormValidatorTest extends TestCase {

    private FormValidator validator;
    
    public void setUp(){
        validator = new FormValidator();
    }
    
    public void testIsSet() {
        assertFalse(validator.isSet(null));
        assertFalse(validator.isSet(""));
        assertTrue(validator.isSet("value"));
    }

    public void testAreSet() {
        assertFalse(validator.areSet(null));
        assertFalse(validator.areSet(new String[]{}));
        assertTrue(validator.areSet(new String[]{"value"}));
    }
    
    public void testIsInt() {
        assertFalse(validator.isInt(null));
        assertFalse(validator.isInt(""));
        assertFalse(validator.isInt("a"));
        assertTrue(validator.isInt("1"));
    }

    public void testAreInts() {
        assertFalse(validator.areInts(null));
        assertFalse(validator.areInts(""));
        assertFalse(validator.areInts("a,b"));
        assertTrue(validator.areInts("1,2,3"));
    }
    
    public void testIsDouble() {
        assertFalse(validator.isDouble(null));
        assertFalse(validator.isDouble(""));
        assertFalse(validator.isDouble("a"));
        assertTrue(validator.isDouble("1"));
        assertTrue(validator.isDouble("1.0"));
        assertTrue(validator.isDouble("1.0E2"));
    }
    
    public void testAreDoubles() {
        assertFalse(validator.areDoubles(null));
        assertFalse(validator.areDoubles(""));
        assertFalse(validator.areDoubles("a,b"));
        assertTrue(validator.areDoubles("1,2,3"));
        assertTrue(validator.areDoubles(".01,.05,.1"));
        assertTrue(validator.areDoubles("0.01,0.05,0.1"));
    }

    public void testIsValidDate() {
        assertFalse(validator.isValidDate("05-05-2005"));
        assertTrue(validator.isValidDate("05/05/2005"));
    }
    
    public void testFormatDate() throws Exception {
        assertEquals("05/05/2005",validator.formatDate(validator.parseDate("05/05/2005")));
        assertEquals("dd/MM/yyyy",validator.getDatePattern());
    }
    
}
