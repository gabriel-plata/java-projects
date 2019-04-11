<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:import url="/WEB-INF/jsp/cabecalho.jsp" />
	<h3>Valor do frete: ${retorno.cServico.valor}</h3>
	<h3>Prazo de entrega: ${retorno.cServico.prazoEntrega} dias</h3>
	<c:import url="/WEB-INF/jsp/rodape.jsp" />
</body>
</html>