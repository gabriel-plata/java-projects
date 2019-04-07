$(document).ready(function(){
	$(function () {
		$("#valdeclaradochk").click(function () {
			if ($(this).is(":checked")) {
				$("#valdeclarado").show();
			} else {
				$("#valdeclarado").hide();
			}
		});
	});
	
	//TODO get element by id and add class erro
	//TODO hide/show div on have class or not
	//TODO onload percorrer atributo get (jstl) de strings[][] e jogar as classes erro
		//nos componentes com erro
});