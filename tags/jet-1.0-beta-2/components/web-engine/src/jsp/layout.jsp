<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>  
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles' %>

<html:html xhtml='true'>
    <head>
        	<title>
            <bean:message key="app.title"/>
        	</title>
		<tiles:insert attribute='head'/>
    </head>
	<body>
    		<div id='body'>               
	        <table id="layoutTable" border="0" cellpadding="0" cellspacing="0" height="100%">
	        		<tr>
		            	<td colspan="2" valign="top" height="48">    
	                    <tiles:insert attribute='header'/>
		            	</td>
	          	</tr>
	        		<tr>
		            	<td width="136" valign="top">    
						<table id="layoutTable" border="0" cellpadding="0" cellspacing="0">            	
				        		<tr>
					            	<td>    
				                    <tiles:insert attribute='navigation'/>
					            	</td>
				          	</tr>
						</table>
	            	  </td>
		            	<td width="642" valign="top">
						<table border="0" cellpadding="0" cellspacing="0">            	
				        		<tr>
					            	<td>    
				                    <tiles:insert attribute='messages'/>
					            	</td>
				          	</tr>
				        		<tr>
				            		<td id='content'>    
					             	<tiles:insert attribute='content'/>
				            		</td>
				            </tr>		           
						</table>
	            	  </td>
	          	</tr>
		    </table>
		</div>    
    </body>
</html:html>
