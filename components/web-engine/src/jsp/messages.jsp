<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<logic:present name="org.codehaus.jet.web.actions.MESSAGES">
  <div id="messages">    
  	<ul>
      <logic:iterate id="message" name="org.codehaus.jet.web.actions.MESSAGES">
        <li class="message"><bean:write name="message" filter="false"/></li>                                                                   
      </logic:iterate>    
   	</ul>
  </div>
</logic:present>
