package br.calculafrete;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cServico")
public class CServico {

	@XStreamAlias("Codigo")
	private String codigo;
	
	@XStreamAlias("Valor")
	private String valor;
	
	@XStreamAlias("PrazoEntrega")
	private String prazoEntrega;
	
	@XStreamAlias("ValorSemAdicionais")
	private String valorSemAdicionais;
	
	@XStreamAlias("ValorMaoPropria")
	private String valorMaoPropria;
	
	@XStreamAlias("ValorAvisoRecebimento")
	private String valorAvisoRecebimento;
	
	@XStreamAlias("ValorValorDeclarado")
	private String valorValorDeclarado;
	
	@XStreamAlias("EntregaDomiciliar")
	private String entregaDomiciliar;
	
	@XStreamAlias("EntregaSabado")
	private String entregaSabado;
	
	@XStreamAlias("Erro")
	private String erro;
	
	@XStreamAlias("MsgErro")
	private String msgErro;
	
	@XStreamAlias("obsFim")
	private String obsFim;

	
	public String getCodigo() {
		return codigo;
	}

	public String getValor() {
		return valor;
	}

	public String getPrazoEntrega() {
		return prazoEntrega;
	}

	public String getValorSemAdicionais() {
		return valorSemAdicionais;
	}

	public String getValorMaoPropria() {
		return valorMaoPropria;
	}

	public String getValorAvisoRecebimento() {
		return valorAvisoRecebimento;
	}

	public String getValorValorDeclarado() {
		return valorValorDeclarado;
	}

	public String getEntregaDomiciliar() {
		return entregaDomiciliar;
	}

	public String getEntregaSabado() {
		return entregaSabado;
	}

	public String getErro() {
		return erro;
	}

	public String getMsgErro() {
		return msgErro;
	}

	public String getObsFim() {
		return obsFim;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setPrazoEntrega(String prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public void setValorSemAdicionais(String valorSemAdicionais) {
		this.valorSemAdicionais = valorSemAdicionais;
	}

	public void setValorMaoPropria(String valorMaoPropria) {
		this.valorMaoPropria = valorMaoPropria;
	}

	public void setValorAvisoRecebimento(String valorAvisoRecebimento) {
		this.valorAvisoRecebimento = valorAvisoRecebimento;
	}

	public void setValorValorDeclarado(String valorValorDeclarado) {
		this.valorValorDeclarado = valorValorDeclarado;
	}

	public void setEntregaDomiciliar(String entregaDomiciliar) {
		this.entregaDomiciliar = entregaDomiciliar;
	}

	public void setEntregaSabado(String entregaSabado) {
		this.entregaSabado = entregaSabado;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}

	public void setObsFim(String obsFim) {
		this.obsFim = obsFim;
	}
	
}
