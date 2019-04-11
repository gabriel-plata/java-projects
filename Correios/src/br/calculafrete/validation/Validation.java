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
		int formatoInt = 0; //op��o inexistente, s� fica zero se n�o for selecionado o formato
		
		//Valida��o cepOrigem
		String[] retornoCepOrigem = new String[2];
		retornoCepOrigem[0] = "cepOrigem";
		retornoCepOrigem[1] = null;
		if(!validaCep(this.cepOrigem)) {
			retornoCepOrigem[1] = "CEP de origem inv�lido";
		}
		retorno.add(retornoCepOrigem);
		
		//Valida��o do cepDestino
		String[] retornoCepDestino = new String[2];
		retornoCepDestino[0] = "cepDestino";
		retornoCepDestino[1] = null;
		if(!validaCep(this.cepDestino)) {
			retornoCepDestino[1] = "CEP de destino inv�lido";
		}
		retorno.add(retornoCepDestino);
		
		//Valida��o do peso
		String[] retornoPeso = new String[2];
		retornoPeso[0] = "peso";
		retornoPeso[1] = null;
		if(!validaFloat(this.peso)) {
			retornoPeso[1] = "Peso inv�lido";
		}
		retorno.add(retornoPeso);
		
		//Valida��o do valorDeclarado
		String[] retornoValorDeclarado = new String[2];
		retornoValorDeclarado[0] = "valorDeclarado";
		retornoValorDeclarado[1] = null;
		if(this.valorDeclaradoCheckbox == null) {
			retornoValorDeclarado[1] = null;
		}else if(!validaFloat(this.valorDeclarado)) {
			retornoValorDeclarado[1] = "Valor declarado inv�lido";
		}else if(Float.parseFloat(this.valorDeclarado) > this.MAX_VALOR_DECLARADO) {
			retornoValorDeclarado[1] = "Valor declarado m�ximo � " + this.MAX_VALOR_DECLARADO + " reais";
		}
		retorno.add(retornoValorDeclarado);
		
		//Valida��o do tipoServico
		String[] retornoTipoServico = new String[2];
		retornoTipoServico[0] = "tipoServico";
		retornoTipoServico[1] = null;
		if(!validaInt(this.tipoServico)) {
			retornoTipoServico[1] = "Tipo de Servi�o obrigat�rio";
		}
		retorno.add(retornoTipoServico);
				
		//Valida��o do formato
		String[] retornoFormato = new String[2];
		retornoFormato[0] = "formato";
		retornoFormato[1] = null;
		if(!validaInt(this.formato)) {	
			retornoFormato[1] = "Formato obrigat�rio";
		}else {
			formatoInt = Integer.parseInt(this.formato);
			if(formatoInt == 3 && 
			retorno.get(findPositionRetorno(retorno,"peso"))[1] == null && 
			Integer.parseInt(this.peso) > this.MAX_PESO_ENVELOPE ) {
				retornoFormato[1] = "O peso m�ximo para envolopes �: " + this.MAX_PESO_ENVELOPE;
			}
		}
		retorno.add(retornoFormato);
		
		//Valida��o do comprimento
		String[] retornoComprimento = new String[2];
		retornoComprimento[0] = "comprimento";
		retornoComprimento[1] = null;
		if(!validaInt(this.comprimento)) {
			retornoComprimento[1] = "Comprimento obrigat�rio";
		}else {
			int comprimentoInt = Integer.parseInt(this.comprimento);
			if( comprimentoInt > this.MAX_COMPRIMENTO) {
				retornoComprimento[1] = "Comprimento m�ximo �: " + this.MAX_COMPRIMENTO;
			}else if( comprimentoInt < this.MIN_COMPRIMENTO ) {
				retornoComprimento[1] = "Comprimento m�nimo �: " + this.MIN_COMPRIMENTO;
			}
		}
		retorno.add(retornoComprimento);
		
		//Valida��o da altura
		String[] retornoAltura = new String[2];
		retornoAltura[0] = "altura";
		retornoAltura[1] = null;
		if(!validaInt(this.altura)) {
			retornoAltura[1] = "Altura obrigat�ria";
		}else {
			int alturaInt = Integer.parseInt(this.altura);
			if(formatoInt == 3 && alturaInt != this.ALTURA_ENVELOPE ){
				retornoAltura[1] = "Altura do envelope tem que ser: " + this.ALTURA_ENVELOPE;
			}else if(alturaInt > this.MAX_ALTURA) {
				retornoAltura[1] = "Altura m�xima �: " + this.MAX_ALTURA;
			}else if( alturaInt < this.MIN_ALTURA ) {
				retornoAltura[1] = "Altura m�nima �: " + this.MIN_ALTURA;
			}
		}
		retorno.add(retornoAltura);
		
		//Valida��o da largura
		String[] retornoLargura = new String[2];
		retornoLargura[0] = "largura";
		retornoLargura[1] = null;
		if(!validaInt(this.largura)) {
			retornoLargura[1] = "Largura obrigat�ria";
		}else {
			int larguraInt = Integer.parseInt(this.largura);
			if( larguraInt > this.MAX_LARGURA ) {
				retornoLargura[1] = "A largura m�xima �: " + this.MAX_LARGURA;
			}else if( larguraInt < this.MIN_LARGURA ) {
				retornoLargura[1] = "A largura m�nima �: " + this.MIN_LARGURA;
			}
		}
		retorno.add(retornoLargura);
		
		//Valida��o do diametro
		String[] retornoDiametro = new String[2];
		retornoDiametro[0] = "diametro";
		retornoDiametro[1] = null;
		if(!validaInt(this.diametro)) {
			retornoDiametro[1] = "Di�metro obrigat�rio";
		}
		retorno.add(retornoDiametro);
		
		//Valida��o da soma das dimens�es
		String[] retornoSomaDimensoes = new String[2];
		retornoSomaDimensoes[0] = "somaDimensoes";
		retornoSomaDimensoes[1] = null;
		if(retorno.get(findPositionRetorno(retorno,"largura"))[1] == null &&
		retorno.get(findPositionRetorno(retorno,"altura"))[1] == null &&
		retorno.get(findPositionRetorno(retorno,"comprimento"))[1] == null) {
			int larguraInt = Integer.parseInt(this.largura);
			int alturaInt = Integer.parseInt(this.altura);
			int comprimentoInt = Integer.parseInt(this.comprimento);
			if ( larguraInt + alturaInt + comprimentoInt > this.MAX_SOMA_DIMENSOES) {
				retornoSomaDimensoes[1] = "A soma das dimens�es n�o pode ser superior a: " + this.MAX_SOMA_DIMENSOES;
			}
		}
		retorno.add(retornoSomaDimensoes);
		
		//Valida��o soma do comprimento com 2x diametro
		String[] retornoSomaComprimento2xDiametro = new String[2];
		retornoSomaComprimento2xDiametro[0] = "somaComprimento2xDiametro";
		retornoSomaComprimento2xDiametro[1] = null;
		if(retorno.get(findPositionRetorno(retorno,"comprimento"))[1] == null &&
		retorno.get(findPositionRetorno(retorno,"diametro"))[1] == null) {
			int diametroInt = Integer.parseInt(this.diametro);
			int comprimentoInt = Integer.parseInt(this.comprimento);
			if( diametroInt * 2 + comprimentoInt > this.MAX_SOMA_COMPRIMENTO_2XDIAMETRO) {
				retornoSomaComprimento2xDiametro[1] = "A soma do comprimento com 2 vezes o diametro n�o pode ultrapassar: " + this.MAX_SOMA_COMPRIMENTO_2XDIAMETRO;
			}	
		}
		retorno.add(retornoSomaComprimento2xDiametro);
		
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
