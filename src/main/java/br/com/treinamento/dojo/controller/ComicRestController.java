package br.com.treinamento.dojo.controller;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinamento.dojo.exception.MarvelRestException;
import br.com.treinamento.dojo.model.URLFactory;
import br.com.treinamento.dojo.parameter.CharacterParameters;
import br.com.treinamento.dojo.service.ComicImplService;

@RestController
@PropertySource("classpath:application.properties")
@RequestMapping(value = "/v1")
public class ComicRestController {

	@Autowired
	Environment env;
	
	private URLFactory urlFactory;
	
    /**
     * Fetches lists of comics.
     *
     * @return
     * @throws IOException
     */
	
	@RequestMapping(value = "/comics", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> comics() throws IOException {
		
		this.urlFactory = new URLFactory(env.getProperty("app.privateKey"), env.getProperty("app.publicKey"));
		String result = getURL(urlFactory.getComicsURL());

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	private String getURL(String url) throws IOException {
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget =  client.target(url);
		
		Response response = webTarget				
				.request()	
				.accept(MediaType.APPLICATION_JSON)
				.get();
		
        if (response.getStatus() != 200) {
            throw new MarvelRestException(response);
        }      
        
        return response.readEntity(String.class);
    }
}