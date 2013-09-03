<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Teste</title>
	</head>
	<body>
		<s:form name="form" action="list">
			<table>
				<tr>
					<td>ID</td>
					<td>Name</td>
					<td>Status</td>
				</tr>
				<s:iterator value="items" id="item">
					<tr>
						<td>
							<s:textfield name="items(%{id}).id" />
							<br />
							<s:property value="%{check(#item)}"/>
						</td>
						<td><s:textfield name="items(%{id}).name" /></td>
						<td><s:checkbox name="items(%{id}).status" /></td>
					</tr>
				</s:iterator>
			</table>
			<s:submit method="update" value="Update" />			
			<c:forEach items="${items}" var="item">
				<div><s:property value="%{check(#item)}"/></div>	
			</c:forEach>
		</s:form>
	</body>
</html>