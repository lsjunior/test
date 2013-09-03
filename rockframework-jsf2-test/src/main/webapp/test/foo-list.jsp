<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	version="2.0">
	<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
	
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="ISO-8859-1" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>JSF Test :: Foo List</title>
	</head>
	<body>
	<f:view>
		<h:form>
			<table>
				<tr>
					<td>ID</td>
					<td>Name</td>
				</tr>
				<c:forEach items="#{fooMB.foos}" var="foo" varStatus="status">
					<tr>
						<!--
						<td>${foo.id}</td>
						<td>${foo.name}</td>
						 -->
						<td><h:outputText value="#{foo.id}" /></td>
						<td><h:outputText value="#{foo.name}" /></td>
						<td>
							<!--
							<h:commandLink action="#{fooMB.edit}">
								<f:setPropertyActionListener value="#{foo.id}" target="#{fooMB.foo.id}"  />
								Edit
							</h:commandLink>
							<h:commandLink action="#{fooMB.delete}">
								<f:setPropertyActionListener value="#{foo.id}" target="#{fooMB.foo.id}" />
								Delete
							</h:commandLink>
							-->
							<h:commandLink action="#{fooMB.edit}">
								<f:param name="foo.id" value="#{foo.id}" />
								Edit
							</h:commandLink>
							<h:commandLink action="#{fooMB.delete}">
								<f:param name="foo.id" value="#{foo.id}" />
								Delete
							</h:commandLink>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<h:commandLink action="index">Index</h:commandLink>
				&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;
				<h:commandLink action="save">Save</h:commandLink>
			</div>
		</h:form>
	</f:view>
	</body>
	</html>
</jsp:root>