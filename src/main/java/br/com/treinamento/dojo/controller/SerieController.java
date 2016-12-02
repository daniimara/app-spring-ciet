package br.com.treinamento.dojo.controller;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinamento.dojo.model.Proxy;
import br.com.treinamento.dojo.model.Serie;
import br.com.treinamento.dojo.model.URLFactory;
import br.com.treinamento.dojo.parameter.SeriesParameters;
import br.com.treinamento.dojo.service.SerieService;
import br.com.treinamento.dojo.dto.ResponseProtocol;
import br.com.treinamento.dojo.exception.MarvelRestException;

@RestController
@RequestMapping(value = "/v1")
public class SerieController {

	private URLFactory urlFactory;
    private Proxy proxy;
	private String publicKey = "4f8c462b9fdb1e043d0d907ce4164374";
	private String privateKey = "52c4cd408bb0c1d27e4e98b741853f30f29d9115";
	
	@Autowired
	private SerieService serieService;

	public SerieService getSerieService() {
		return serieService;
	}

	public void setSerieService(SerieService serieService) {
		this.serieService = serieService;
	}
	
    /**
     * Fetches lists of series.
     *
     * @return
     * @throws IOException
     */
		
	@RequestMapping(value = "/serie", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ResponseProtocol> serie() {
		
		List<Serie> series = getSerieService().getAll();
		ResponseProtocol response = new ResponseProtocol();
		if (series != null) {
			response.setData(series);
			return new ResponseEntity<ResponseProtocol>(response, HttpStatus.OK);
		} else {
			response.setMensagem("Não existem series.");
			return new ResponseEntity<ResponseProtocol>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/series", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> series() throws IOException {
		
		this.urlFactory = new URLFactory(privateKey, publicKey);
		String result = getURL(urlFactory.getSeriesURL());

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
    /**
     * Fetches a single comic series by id.
     *
     * @param seriesId
     * @return
     * @throws NumberFormatException 
     * @throws IOException
     */
    
    @RequestMapping(value = "/series/{seriesId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getSeriesById(@PathVariable String seriesId) throws NumberFormatException, IOException {
    	
    	this.urlFactory = new URLFactory(privateKey, publicKey);
    	String result = getURL(urlFactory.getSeriesURL(Integer.parseInt(seriesId)));

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
    
    /**
     * Fetches lists of characters by a series id.
     *
     * @param seriesParameters
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/series/{seriesId}/characters", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getSeriesCharactersById(@PathVariable String seriesId) throws NumberFormatException, IOException {
    	
    	SeriesParameters seriesParameters = new SeriesParameters(Integer.parseInt(seriesId));
    	
    	this.urlFactory = new URLFactory(privateKey, publicKey);
    	String result = getURL(urlFactory.getSeriesCharactersURL(seriesParameters));

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
    
    /**
     * Fetches lists of comics by a series id.
     *
     * @param seriesParameters
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/series/{seriesId}/comics", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getSeriesComicsById(@PathVariable String seriesId) throws NumberFormatException, IOException {
    	
    	SeriesParameters seriesParameters = new SeriesParameters(Integer.parseInt(seriesId));
    	
    	this.urlFactory = new URLFactory(privateKey, publicKey);
    	String result = getURL(urlFactory.getSeriesComicsURL(seriesParameters));

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
    
    /**
     * Fetches lists of creators by a series id.
     *
     * @param seriesParameters
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/series/{seriesId}/creators", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getSeriesCreatorsById(@PathVariable String seriesId) throws NumberFormatException, IOException {
    	
    	SeriesParameters seriesParameters = new SeriesParameters(Integer.parseInt(seriesId));
    	
    	this.urlFactory = new URLFactory(privateKey, publicKey);
    	String result = getURL(urlFactory.getSeriesCreatorsURL(seriesParameters));

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
    
    /**
     * Fetches lists of stories by a series id.
     *
     * @param seriesParameters
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/series/{seriesId}/stories", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getSeriesStoriesById(@PathVariable String seriesId) throws NumberFormatException, IOException {
    	
    	SeriesParameters seriesParameters = new SeriesParameters(Integer.parseInt(seriesId));
    	
    	this.urlFactory = new URLFactory(privateKey, publicKey);
    	String result = getURL(urlFactory.getSeriesStoriesURL(seriesParameters));

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