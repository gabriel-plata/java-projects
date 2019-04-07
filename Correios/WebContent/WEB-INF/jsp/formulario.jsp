<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="css/jquery-ui.css" />
	<link rel="stylesheet" href="css/jquery-ui.structure.css" />
	<link rel="stylesheet" href="css/jquery-ui.theme.css" />
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script type="text/javascript" src="js/formulario.js"></script>
	<title></title>
</head>
<body>
	<form action="calculaFrete" method="POST">
		<br>
		<fieldset>
			<legend>Tipo de serviço</legend>
			<input type="radio" name="tipoServico" id="40010" value="40010">
			<label for="40010">SEDEX Varejo</label>
			<br>
			<input type="radio" name="tipoServico" id="40045" value="40045">
			<label for="40045">SEDEX a Cobrar Varejo</label>
			<br>
			<input type="radio" name="tipoServico" id="40215" value="40215">
			<label for="40215">SEDEX 10 Varejo</label>
			<br>
			<input type="radio" name="tipoServico" id="40290" value="40290">
			<label for="40290">SEDEX Hoje Varejo</label>
			<br>
			<input type="radio" name="tipoServico" id="41106" value="41106">
			<label for="41106">PAC Varejo</label>
		</fieldset>
		<br>
		<fieldset>
			<legend>Formato da Encomenda</legend>
			<input type="radio" name="formato" id="formato1" value="1">
			<label for="formato1">Caixa/Pacote</label>
			<br>
			<input type="radio" name="formato" id="formato2" value="2">
			<label for="formato2">Rolo/Prisma</label>
			<br>
			<input type="radio" name="formato" id="formato3" value="3">
			<label for="formato3">Envelope</label>
		</fieldset>
		<br>
		<fieldset>
			<legend>CEP</legend>
			<label for="cep-origem">Origem</label>
			<input type="text" name="cepOrigem" id="cep-origem" onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
			<br>
			<label for="cep-destino">Destino</label>
			<input type="text" name="cepDestino" id="cep-destino" onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
		</fieldset>
		<br>
		<fieldset>
			<legend>Dimensões</legend>
			<label for="peso">Peso(kg)</label>
			<input type="text" name="peso" id="peso" onkeypress='return (event.charCode >= 48 && event.charCode <= 57) || event.charCode == 46'/>
			<br>
			<label for="comprimento">Comprimento(cm)</label>
			<input type="text" name="comprimento" id="comprimento" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
			<br>
			<label for="altura">Altura(cm)</label>
			<input type="text" name="altura" id="altura" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>	
			<label class="erro" id="altura" style="display: none, color: red">
				<%--TODO: fazer taglib pt:erroFrete 
				 
				<c:forEach items="${errosEncontrados}" var="erros">
				
				</c:forEach>
				--%>
			</label>
			<br>
			<label for="largura">Largura(cm)</label>
			<input type="text" name="largura" id="largura" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
			<br>
			<label for="diametro">Diâmetro(cm)</label>
			<input type="text" name="diametro" id="diametro" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
		</fieldset>
		<br>
		<fieldset>
			<legend>Opções adicionais</legend>
			<input type="checkbox" name="maoPropria" id="mao-propria"/>
			<label for="mao-propria">Mão própria</label>
			<br>
			<input type="checkbox" name="avisoRecebimento" id="aviso-recebimento"/>
			<label for="aviso-recebimento">Aviso de recebimento</label>
			<br>
			<input type="checkbox" name="valorCheckbox" id="valdeclaradochk" />
			<label for="valdeclaradochk">Valor declarado (BRL)</label>
			<div id="valdeclarado" style="display: none">
				<input id="txtvaldeclarado" type="text" name="valorDeclarado" onkeypress='return (event.charCode >= 48 && event.charCode <= 57) || event.charCode == 46' />
			</div>
		</fieldset>
		<br><br>
		<input type="submit" value="Calcular" />
	</form>
</body>
</html>