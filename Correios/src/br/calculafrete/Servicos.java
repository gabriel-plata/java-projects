package br.calculafrete;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Servicos")
public class Servicos {
	
	@XStreamAlias("cServico")
	private CServico cServico;

	public CServico getcServico() {
		return cServico;
	}

	public void setcServico(CServico cServico) {
		this.cServico = cServico;
	}
	
	
}
