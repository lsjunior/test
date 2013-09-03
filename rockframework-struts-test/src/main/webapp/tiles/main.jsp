<?xml version="1.0" encoding="iso-8859-1"?>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html 
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="content-language" content="pt-br" />
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="cache-control" content="no-cache" />
        <title>Struts Test :: <tiles:getAsString name="title" /></title>
        <tiles:insert attribute="css" />
        <tiles:insert attribute="javascript" />
	</head>
	<body>
		<div style="text-align: center;">CABECALHO</div>
		<div id="body">
			<tiles:insert attribute="body" />
		</div>
		<div style="text-align: center;">RODAPE</div>
	</body>
</html>