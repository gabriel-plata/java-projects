package br.calculafrete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.calculafrete.validation.Validation;

@WebServlet("/calculaFrete")
public class CalculaFrete extends HttpServlet implements Servlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4454913752459888326L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipoServico = request.getParameter("tipoServico");
		String formato = request.getParameter("formato");
		String cepOrigem = request.getParameter("cepOrigem");
		String cepDestino = request.getParameter("cepDestino");
		String peso = request.getParameter("peso");
		String altura = request.getParameter("altura");
		String comprimento = request.getParameter("comprimento");
		String largura = request.getParameter("largura");
		String diametro = request.getParameter("diametro");
		String maoPropria = request.getParameter("maoPropria");
		String avisoRecebimento = request.getParameter("avisoRecebimento");
		String valorCheckbox = request.getParameter("valorCheckbox");
		String valorDeclarado = request.getParameter("valorDeclarado");
		
		Validation valid = new Validation();
		valid.setAltura(altura);
		valid.setCepDestino(cepDestino);
		valid.setCepOrigem(cepOrigem);
		valid.setComprimento(comprimento);
		valid.setDiametro(diametro);
		valid.setFormato(formato);
		valid.setLargura(largura);
		valid.setPeso(peso);
		valid.setTipoServico(tipoServico);
		valid.setValorDeclarado(valorDeclarado);
		valid.setValorDeclaradoCheckbox(valorCheckbox);
		
		ArrayList<String[]> erros = valid.validar();
		int errosEncontrados = 0;
		for(int i = 0 ; i < erros.size() ; i++) {
			if(!erros.get(i)[1].equals(null)) {
				errosEncontrados++;
			}
		}
		
		request.setAttribute("errosEncontrados", erros);
		if(errosEncontrados > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}else {
			Encomenda encomenda = new Encomenda();
			encomenda.setTipoServico(Integer.parseInt(tipoServico));
			encomenda.setCepDestino(cepDestino);
			encomenda.setCepOrigem(cepOrigem);
			encomenda.setPeso(Float.parseFloat(peso));
			encomenda.setAltura(Integer.parseInt(altura));
			encomenda.setComprimento(Integer.parseInt(comprimento));
			encomenda.setLargura(Integer.parseInt(largura));
			encomenda.setDiametro(Integer.parseInt(diametro));
			encomenda.setFormato(Integer.parseInt(formato));
			
			if(avisoRecebimento.equals("on")) {
				encomenda.setAvisoRecebimento('S');
			}else {
				encomenda.setAvisoRecebimento('N');
			}
			
			if(maoPropria.equals("on")) {
				encomenda.setMaoPropria('S');
			}else {
				encomenda.setMaoPropria('N');
			}
			
			if(valorCheckbox.equals("on")) {
				encomenda.setValorDeclarado(Float.parseFloat(valorDeclarado));
			}else {
				encomenda.setValorDeclarado(0f);
			}
			
			String json = getRetornoApi(encomenda);
			request.setAttribute("json", json);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/show.jsp");
			rd.forward(request, response);
		}
	}
	
	private String getRetornoApi(Encomenda encomenda) {
		URL url;
		String json;
		URLConnection urlConnection;
		try {
			url = new URL("http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?" + 
					"nCdEmpresa=" + 
					"&sDsSenha=" +
					"&nCdServico=" + encomenda.getTipoServico() +
					"&sCepOrigem=" + encomenda.getCepOrigem() +
					"&sCepDestino=" + encomenda.getCepDestino() + 
					"&nVlPeso=" + encomenda.getPeso() + 
					"&nCdFormato=" + encomenda.getFormato() + 
					"&nVlComprimento=" + encomenda.getComprimento() + 
					"&nVlAltura=" + encomenda.getAltura() + 
					"&nVlLargura=" + encomenda.getLargura() + 
					"&nVlDiametro=" + encomenda.getDiametro() + 
					"&sCdMaoPropria=" + encomenda.getMaoPropria() + 
					"&nVlValorDeclarado=" + encomenda.getValorDeclarado() + 
					"&sCdAvisoRecebimento=" + encomenda.getAvisoRecebimento() + 
					"&StrRetorno=" + encomenda.getRetorno() + 
					"&nIndicaCalculo=" + encomenda.getRetornoCalculo() );
		} catch(MalformedURLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			urlConnection = url.openConnection();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		InputStream is;
		try {
			is = urlConnection.getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuilder jsonSb = new StringBuilder();

        br.lines().forEach(l -> jsonSb.append(l.trim()));

        json = jsonSb.toString();
		
		return json;
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		log("Iniciando a servlet");
	}
	
	public void destroy() {
		super.destroy();
		log("Destruindo a servlet");
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
