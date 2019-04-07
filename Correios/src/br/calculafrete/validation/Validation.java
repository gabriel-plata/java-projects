package br.calculafrete.validation;

import java.util.ArrayList;

public class Validation {
	private String cepDestino;
	private String cepOrigem;
	private String peso;
	private String tipoServico;
	private String formato;
	private String comprimento;
	private String altura;
	private String largura;
	private String diametro;
	private String valorDeclarado;
	private String valorDeclaradoCheckbox;
	private final int MAX_SOMA_DIMENSOES;
	private final float MAX_VALOR_DECLARADO;
	private final int MAX_ALTURA;
	private final int MAX_COMPRIMENTO;
	private final int MAX_LARGURA;
	private final int MIN_LARGURA;
	private final int MIN_ALTURA;
	private final int MIN_COMPRIMENTO;
	private final int MAX_SOMA_COMPRIMENTO_2XDIAMETRO;
	private final float MAX_PESO_ENVELOPE;
	private final int ALTURA_ENVELOPE;
	
	public Validation( ) {
		this.MAX_SOMA_DIMENSOES = 200;
		this.MAX_VALOR_DECLARADO = 10000.0f;
		this.MAX_ALTURA = 105;
		this.MAX_LARGURA = 105;
		this.MAX_COMPRIMENTO = 105;
		this.MIN_LARGURA = 11;
		this.MIN_ALTURA = 2;
		this.MIN_COMPRIMENTO = 16;
		this.MAX_SOMA_COMPRIMENTO_2XDIAMETRO = 200;
		this.MAX_PESO_ENVELOPE = 1.0f;
		this.ALTURA_ENVELOPE = 0;
	}
	
	private boolean validaCep(String cep) {
		boolean retorno = true;
		
		return retorno;
	}
	
	private boolean validaFloat(String num) {
		boolean retorno = true;
		
		try {
			Float.parseFloat(num);
		} catch(NumberFormatException n) {
			retorno = false;
		}
		
		return retorno;
	}
	
	private boolean validaInt(String num) {
		boolean retorno = true;
		
		try {
			Integer.parseInt(num);
		} catch(NumberFormatException n) {
			retorno = false;
		}
		
		return retorno;
	}
	
	private int findPositionRetorno(ArrayList<String[]> retorno, String propriedade) {
		for(int i = 0 ; i < retorno.size() ; i++) {
			if( retorno.get(i)[0].equals(propriedade) ) {
				return i;
			}
		}
		return -1;
	}
	
	public ArrayList<String[]> validar(){
		ArrayList<String[]> retorno = new ArrayList<String[]>();
		int posicao;
		String prop,erro;
		int formatoInt = 0; //opção inexistente, só fica zero se não for selecionado o formato
		String[] res = new String[2];
		
		//Inicialiazação do retorno
		res[1] = null;
		res[0] = "cepDestino";
		retorno.add(res);
		res[0] = "cepOrigem";
		retorno.add(res);
		res[0] = "peso";
		retorno.add(res);
		res[0] = "tipoServico";
		retorno.add(res);
		res[0] = "formato";
		retorno.add(res);
		res[0] = "comprimento";
		retorno.add(res);
		res[0] = "altura";
		retorno.add(res);
		res[0] = "largura";
		retorno.add(res);
		res[0] = "diametro";
		retorno.add(res);
		res[0] = "maoPropria";
		retorno.add(res);
		res[0] = "valorDeclarado";
		retorno.add(res);
		res[0] = "avisoRecebimento";
		retorno.add(res);
		res[0] = "somaDimensoes";
		retorno.add(res);
		res[0] = "somaComprimento2xDiametro";
		retorno.add(res);
		
		
		
		//Validação cepOrigem
		prop = "cepOrigem";
		erro = null;
		posicao = findPositionRetorno(retorno,prop);
		if(!validaCep(this.cepOrigem)) {
			erro = "CEP de origem inválido";
		}
		res[0] = prop;
		res[1] = erro;
		retorno.set(posicao, res);
		
		//Validação do cepDestino
		prop = "cepDestino";
		erro = null;
		posicao = findPositionRetorno(retorno,prop);
		if(!validaCep(this.cepDestino)) {
			erro = "CEP de destino inválido";
		}
		res[0] = prop;
		res[1] = erro;
		retorno.set(posicao, res);
		
		//Validação do peso
		prop = "peso";
		erro = null;
		posicao = findPositionRetorno(retorno,prop);
		if(!validaFloat(this.peso)) {
			erro = "Peso inválido";
		}
		res[0] = prop;
		res[1] = erro;
		retorno.set(posicao, res);
		
		//Validação do valorDeclarado
		prop = "valorDeclarado";
		erro = null;
		posicao = findPositionRetorno(retorno,"valorDeclarado");
		if(valorDeclaradoCheckbox.equals(null)) {
			erro = null;
		}else if(!validaFloat(this.valorDeclarado)) {
			erro = "Valor declarado inválido";
		}else if(Float.parseFloat(this.valorDeclarado) > this.MAX_VALOR_DECLARADO) {
			erro = "Valor declarado máximo é " + this.MAX_VALOR_DECLARADO + " reais";
		}
		res[0] = prop;
		res[1] = erro;
		retorno.set(posicao, res);
		
		//Validação do tipoServico
		prop = "tipoServico";
		erro = null;
		posicao = findPositionRetorno(retorno,prop);
		if(!validaInt(this.tipoServico)) {
			erro = "Tipo de Serviço obrigatório";
		}
		res[0] = prop;
		res[1] = erro;
		retorno.set(posicao, res);
				
		//Validação do formato
		prop = "formato";
		erro = null;
		posicao = findPositionRetorno(retorno,prop);
		if(!validaInt(this.formato)) {	
			erro = "Formato obrigatório";
		}else {
			formatoInt = Integer.parseInt(this.formato);
			if(formatoInt == 3 && 
			retorno.get(findPositionRetorno(retorno,"peso"))[1] == null && 
			Integer.parseInt(this.peso) > this.MAX_PESO_ENVELOPE ) {
				erro = "O peso máximo para envolopes é: " + this.MAX_PESO_ENVELOPE;
			}
		}
		res[0] = prop;
		res[1] = erro;
		retorno.set(posicao, res);
		
		//Validação do comprimento
		prop = "comprimento";
		erro = null;
		posicao = findPositionRetorno(retorno,prop);
		if(!validaInt(this.comprimento)) {
			erro = "Comprimento obrigatório";
		}else {
			int comprimentoInt = Integer.parseInt(this.comprimento);
			if( comprimentoInt > this.MAX_COMPRIMENTO) {
				erro = "Comprimento máximo é: " + this.MAX_COMPRIMENTO;
			}else if( comprimentoInt < this.MIN_COMPRIMENTO ) {
				erro = "Comprimento mínimo é: " + this.MIN_COMPRIMENTO;
			}
		}
		res[0] = prop;
		res[1] = erro;
		retorno.set(posicao, res);
		
		//Validação da altura
		prop = "altura";
		erro = null;
		posicao = findPositionRetorno(retorno,prop);
		if(!validaInt(this.altura)) {
			erro = "Altura obrigatória";
		}else {
			int alturaInt = Integer.parseInt(this.altura);
			if(formatoInt == 3 && alturaInt != this.ALTURA_ENVELOPE ){
				erro = "Altura do envelope tem que ser: " + this.ALTURA_ENVELOPE;
			}else if(alturaInt > this.MAX_ALTURA) {
				erro = "Altura máxima é: " + this.MAX_ALTURA;
			}else if( alturaInt < this.MIN_ALTURA ) {
				erro = "Altura mínima é: " + this.MIN_ALTURA;
			}
		}
		res[0] = prop;
		res[1] = erro;
		retorno.set(posicao, res);
		
		//Validação da largura
		prop = "largura";
		erro = null;
		posicao = findPositionRetorno(retorno,prop);
		if(!validaInt(this.largura)) {
			erro = "Largura obrigatória";
		}else {
			int larguraInt = Integer.parseInt(this.largura);
			if( larguraInt > this.MAX_LARGURA ) {
				erro = "A largura máxima é: " + this.MAX_LARGURA;
			}else if( larguraInt < this.MIN_LARGURA ) {
				erro = "A largura mínima é: " + this.MIN_LARGURA;
			}
		}
		res[0] = prop;
		res[1] = erro;
		retorno.set(posicao, res);
		
		//Validação do diametro
		prop = "formato";
		erro = null;
		posicao = findPositionRetorno(retorno,prop);
		if(!validaInt(this.diametro)) {
			erro = "Formato obrigatório";
		}
		res[0] = prop;
		res[1] = erro;
		retorno.set(posicao, res);
		
		//Validação da soma das dimensões
		prop = "somaDimensoes";
		erro = null;
		posicao = findPositionRetorno(retorno,prop);
		if(retorno.get(findPositionRetorno(retorno,"largura"))[1] == null &&
		retorno.get(findPositionRetorno(retorno,"altura"))[1] == null &&
		retorno.get(findPositionRetorno(retorno,"comprimento"))[1] == null) {
			int larguraInt = Integer.parseInt(this.largura);
			int alturaInt = Integer.parseInt(this.altura);
			int comprimentoInt = Integer.parseInt(this.comprimento);
			if ( larguraInt + alturaInt + comprimentoInt > this.MAX_SOMA_DIMENSOES) {
				erro = "A soma das dimensões não pode ser superior a: " + this.MAX_SOMA_DIMENSOES;
			}
		}
		res[0] = prop;
		res[1] = erro;
		retorno.set(posicao, res);
		
		//Validação soma do comprimento com 2x diametro
		prop = "somaComprimento2xDiametro";
		erro = null;
		posicao = findPositionRetorno(retorno,prop);
		if(retorno.get(findPositionRetorno(retorno,"comprimento"))[1] == null &&
		retorno.get(findPositionRetorno(retorno,"diametro"))[1] == null) {
			int diametroInt = Integer.parseInt(this.diametro);
			int comprimentoInt = Integer.parseInt(this.comprimento);
			if( diametroInt * 2 + comprimentoInt > this.MAX_SOMA_COMPRIMENTO_2XDIAMETRO) {
				erro = "A soma do comprimento com 2 vezes o diametro não pode ultrapassar: " + this.MAX_SOMA_COMPRIMENTO_2XDIAMETRO;
			}	
		}
		res[0] = prop;
		res[1] = erro;
		retorno.set(posicao, res);
		
		return retorno;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getComprimento() {
		return comprimento;
	}

	public void setComprimento(String comprimento) {
		this.comprimento = comprimento;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getLargura() {
		return largura;
	}

	public void setLargura(String largura) {
		this.largura = largura;
	}

	public String getDiametro() {
		return diametro;
	}

	public void setDiametro(String diametro) {
		this.diametro = diametro;
	}

	public String getValorDeclarado() {
		return valorDeclarado;
	}

	public void setValorDeclarado(String valorDeclarado) {
		this.valorDeclarado = valorDeclarado;
	}

	public String getValorDeclaradoCheckbox() {
		return valorDeclaradoCheckbox;
	}

	public void setValorDeclaradoCheckbox(String valorDeclaradoCheckbox) {
		this.valorDeclaradoCheckbox = valorDeclaradoCheckbox;
	}
}
