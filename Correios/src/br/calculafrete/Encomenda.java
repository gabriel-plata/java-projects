package br.calculafrete;

public class Encomenda {
	private String cepDestino;
	private String cepOrigem;
	private float peso;
	private int tipoServico;
	private int formato;
	private int comprimento;
	private int altura;
	private int largura;
	private int diametro;
	private char maoPropria;
	private float valorDeclarado;
	private char avisoRecebimento;
	private final String retorno;
	private final int retornoCalculo;
	
	public Encomenda() {
		this.retorno = "XML"; 		//Retorno em XML da consulta
		this.retornoCalculo = 3; 	//Preço e Prazo
	}
	
	public String getRetorno() {
		return this.retorno;
	}
	
	public int getRetornoCalculo() {
		return this.retornoCalculo;
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

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public int getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(int tipoServico) {
		this.tipoServico = tipoServico;
	}

	public int getFormato() {
		return formato;
	}

	public void setFormato(int formato) {
		this.formato = formato;
	}

	public int getComprimento() {
		return comprimento;
	}

	public void setComprimento(int comprimento) {
		this.comprimento = comprimento;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getDiametro() {
		return diametro;
	}

	public void setDiametro(int diametro) {
		this.diametro = diametro;
	}

	public char getMaoPropria() {
		return maoPropria;
	}

	public void setMaoPropria(char maoPropria) {
		this.maoPropria = maoPropria;
	}

	public float getValorDeclarado() {
		return valorDeclarado;
	}

	public void setValorDeclarado(float valorDeclarado) {
		this.valorDeclarado = valorDeclarado;
	}

	public char getAvisoRecebimento() {
		return avisoRecebimento;
	}

	public void setAvisoRecebimento(char avisoRecebimento) {
		this.avisoRecebimento = avisoRecebimento;
	}
}
