package br.calculafrete;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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
			if(erros.get(i)[1] != null) {
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
			
			if(avisoRecebimento != null) {
				encomenda.setAvisoRecebimento('S');
			}else {
				encomenda.setAvisoRecebimento('N');
			}
			
			if(maoPropria != null) {
				encomenda.setMaoPropria('S');
			}else {
				encomenda.setMaoPropria('N');
			}
			
			if(valorCheckbox != null) {
				encomenda.setValorDeclarado(Float.parseFloat(valorDeclarado));
			}else {
				encomenda.setValorDeclarado(0f);
			}
			
			
			Servicos retorno = getRetornoApi(encomenda);
			
			request.setAttribute("retorno", retorno);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/show.jsp");
			rd.forward(request, response);
		}
	}
	
	private Servicos getRetornoApi(Encomenda encomenda) throws IOException {
		
//		Client client = ClientBuilder.newClient();
//		String pesquisa = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?" + 
//				"nCdEmpresa=" + 
//				"&sDsSenha=" +
//				"&nCdServico=" + encomenda.getTipoServico() +
//				"&sCepOrigem=" + encomenda.getCepOrigem() +
//				"&sCepDestino=" + encomenda.getCepDestino() + 
//				"&nVlPeso=" + encomenda.getPeso() + 
//				"&nCdFormato=" + encomenda.getFormato() + 
//				"&nVlComprimento=" + encomenda.getComprimento() + 
//				"&nVlAltura=" + encomenda.getAltura() + 
//				"&nVlLargura=" + encomenda.getLargura() + 
//				"&nVlDiametro=" + encomenda.getDiametro() + 
//				"&sCdMaoPropria=" + encomenda.getMaoPropria() + 
//				"&nVlValorDeclarado=" + encomenda.getValorDeclarado() + 
//				"&sCdAvisoRecebimento=" + encomenda.getAvisoRecebimento() + 
//				"&StrRetorno=" + encomenda.getRetorno() + 
//				"&nIndicaCalculo=" + encomenda.getRetornoCalculo();
//		WebTarget target = client.target(pesquisa);
//		String conteudo = target.request().get(String.class);
		
		
		
		URL url;
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
		
		
		urlConnection = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String inputLine;
		File fileXML = new File("retornoXML.xml");
		FileWriter f = new FileWriter(fileXML);
		while ((inputLine = reader.readLine()) != null) {
			f.write(inputLine);
		}
		f.close();
		reader.close();
		
		FileReader fileReader = null;
		try {
			//carrega o arquivo XML para um objeto reader
			fileReader = new FileReader("retornoXML.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		XStream xStream = new XStream(new DomDriver());
		xStream.alias("Servicos", Servicos.class);
		xStream.aliasField("Codigo", CServico.class, "codigo");
		xStream.aliasField("Valor", CServico.class, "valor");
		xStream.aliasField("PrazoEntrega", CServico.class, "prazoEntrega");
		xStream.aliasField("ValorSemAdicionais", CServico.class, "valorSemAdicionais");
		xStream.aliasField("ValorMaoPropria", CServico.class, "valorMaoPropria");
		xStream.aliasField("ValorAvisoRecebimento", CServico.class, "valorAvisoRecebimento");
		xStream.aliasField("ValorValorDeclarado", CServico.class, "valorValorDeclarado");
		xStream.aliasField("EntregaDomiciliar", CServico.class, "entregaDomiciliar");
		xStream.aliasField("EntregaSabado", CServico.class, "entregaSabado");
		xStream.aliasField("Erro", CServico.class, "erro");
		xStream.aliasField("MsgErro", CServico.class, "msgErro");
		xStream.aliasField("obsFim", CServico.class, "obsFim");
		xStream.processAnnotations(Servicos.class);
		Servicos servicos = (Servicos) xStream.fromXML(fileReader);
		
		return servicos;
		
//		String retornoXML = reader.readLine();
//		json = retornoXML;
//        while( retornoXML != null){
//            json += retornoXML;
//            retornoXML = reader.readLine();
//        }
//        
        
		
//		DataInputStream is;
//		is = new DataInputStream(urlConnection.getInputStream());
//		
		

//		
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//        StringBuilder jsonSb = new StringBuilder();
//
//        br.lines().forEach(l -> jsonSb.append(l.trim()));
//
//        json = jsonSb.toString();
//		
        
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
