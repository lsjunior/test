<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html:form action="people.do" method="post" onsubmit="return validatePeople(this);">
	<table>
		<tr>
			<td><bean:message key="people.id" /></td>
			<td><html:text property="people.id" /></td>
		</tr>
		<tr>
			<td><bean:message key="people.name" /></td>
			<td><html:text property="people.name" /></td>
		</tr>
		<tr>
			<td><bean:message key="people.status" /></td>
			<td>
				<bean:message key="people.status.active" />
				<html:radio property="people.status" value="true" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<bean:message key="people.status.inactive" />
				<html:radio property="people.status" value="false" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<html:submit>
					<bean:message key="form.send" />
				</html:submit>						
			</td>
		</tr>
	</table>
	<html:javascript formName="people"/>
</html:form>