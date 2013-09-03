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
	<title>JSF Test :: Foo Save</title>
	</head>
	<body>
	<f:view>
		<c:if test="${not empty fooMB.message}">
			${fooMB.message}
		</c:if>
		<h:form>
			<table>
				<tr>
					<td>ID: </td>
					<td><h:inputText value="#{fooMB.foo.id}" /></td>
				</tr>
				<tr>
					<td>Name: </td>
					<td><h:inputText value="#{fooMB.foo.name}" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<c:choose>
							<c:when test="${empty fooMB.foo.id}">
								<h:commandButton action="#{fooMB.save}" value="Save" />
							</c:when>
							<c:otherwise>
								<h:commandButton action="#{fooMB.update}" value="Update" />
							</c:otherwise>
						</c:choose>
						&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;
						<h:commandButton action="#{fooMB.list}" value="Back" />
					</td>
				</tr>
			</table>
		</h:form>
	</f:view>
	</body>
	</html>
</jsp:root>