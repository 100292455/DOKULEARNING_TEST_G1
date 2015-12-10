package es.uc3m.tiw.web.rest;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class AlumnoWSBanco {

	ClientConfig config = new ClientConfig();
	Client client = ClientBuilder.newClient(config);
	WebTarget service = client.target(getBaseURI());

	public String PedidoWSBanco(String importeCobrar, String tarjeta, String codPedido){
		String codBancario = service.path("pedido").path(importeCobrar).path(tarjeta).path(codPedido).path("xml").request().accept(MediaType.TEXT_PLAIN).get(String.class);
		return codBancario;
	}
	
	public Double ConciliarWSBanco(String codPedido){
		String precioConciliadoStr = service.path("conciliar").path(codPedido).path("xml").request().accept(MediaType.TEXT_PLAIN).get(String.class);
		Double precioConciliado = Double.parseDouble(precioConciliadoStr);
		return precioConciliado;
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/banco-web/resources/pasarela").build();
	}
}

