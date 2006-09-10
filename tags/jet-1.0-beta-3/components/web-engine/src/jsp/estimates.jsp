<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<div id="estimatesQuery">
<html:form action="/queryEstimates.action">
    <html:hidden property="action" value="" />
    <html:hidden property="actionTarget" value="" />
    <table border="0" cellpadding="0" cellspacing="0" id="tableTitle">
        <tr>
            <td>Estimates</td>
        </tr>
    </table>
    <table id="mainTable" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <table border="0" cellpadding="0" cellspacing="0" id="tableContent">
                    <thead>
                        <tr>
                            <td colspan="4">Select input parameters:</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <table id="tableRow" cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td colspan="2">test name<br/>
                                        				[select one]<br/>
								   		<html:select property="testName">
											<html:optionsCollection property="testNames" label="label" value="value"/>        
								   		</html:select>
                                        </td>
                                        <td colspan="2">options<br/>
                                        			[comma-separated-integers (see legend below)]<br/>
                                        			<html:text property="options" size="20" />
                                        </td>
                                        <td colspan="2">sample size<br/>
                                        			 [0 (asymptotic), N]:<br/>
                                        			<html:text property="sampleSize" size="10" />
                                        </td>
                                        <td colspan="2">level<br/>
                                        			 [eg 0.01]:<br/>
                                        			<html:text property="level" size="10" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
    </table>

    <br/>
    <p>
	Options are test-dependent and expressed as comma-separated integers:
	<ol>    
		<li>URC: [integrationVariables (1-12), regressionVariables (0{nc},1{c},2{ct},3{ctt}), testType (1{tau}-2{z})]; </li>
		<li>ECM: [integrationVariables (1-12), regressionVariables (0{nc},1{c},2{ct},3{ctt}), ncoeff]; </li>
		<li>LRC: [restrictionVariables (1-12), exogenousVariables (0-8), regressionVariables (0{I(0)},1{II(1*)},2{III},3{IV(2*)},5{V}), testType (1{lambda}-2{trace})]</li>
		<li>Johansen: [restrictionVariables (1-12), exogenousVariables (0-8), regressionVariables (0{0},1{1},2{2},3{1*},4{2*}), testType (1{lambda}-2{trace})]</li>
	</ol>
	</p>
	<br/>
    <input type="image"
        src="<html:rewrite page='/images/button_query.gif'/>"
        alt="Update value estimates"
        onclick="document.estimatesQueryForm.action.value='query'" />
        <p>
        		The critical value estimate is: <bean:write name="estimatesQueryForm" property="criticalValue"/>
		</p>
        <p>
        		The p-value estimate is: <bean:write name="estimatesQueryForm" property="PValue"/>
		</p>
</div>
</html:form>
