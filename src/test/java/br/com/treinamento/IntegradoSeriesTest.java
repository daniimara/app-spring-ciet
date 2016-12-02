package br.com.treinamento;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import br.com.treinamento.dojo.model.CollectionURI;
import br.com.treinamento.dojo.model.CollectionURIDeserializer;
import com.google.gson.Gson;

import br.com.treinamento.config.TestConfig;
import br.com.treinamento.dojo.model.Serie;
import br.com.treinamento.dojo.model.Result;
import br.com.treinamento.dojo.model.MarvelCharacter;
import br.com.treinamento.dojo.model.Comic;
import br.com.treinamento.dojo.model.Creator;
import br.com.treinamento.dojo.model.Story;
import br.com.treinamento.dojo.model.URLFactory;

import br.com.treinamento.dojo.parameter.SeriesParameters;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestConfig.class)
public class IntegradoSeriesTest {
	
	private URLFactory urlFactory;
	private String publicKey = "4f8c462b9fdb1e043d0d907ce4164374";
	private String privateKey = "52c4cd408bb0c1d27e4e98b741853f30f29d9115";
	
	private WebTarget webTarget;
	private Client client;
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp() throws NoSuchAlgorithmException{
		
		client = ClientBuilder.newClient();
		
		objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("CollectionURIDeserializerModule", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(CollectionURI.class, new CollectionURIDeserializer());
        objectMapper.registerModule(module);
	}
	
    @Test
    public void testGetSeries() throws Exception {
    	
    	urlFactory = new URLFactory(privateKey, publicKey);
    	webTarget = client.target(urlFactory.getSeriesURL());
		
		Response response = webTarget				
				.request()	
				.accept(MediaType.APPLICATION_JSON)
				.get();
		
		String json = response.readEntity(String.class);
		//System.out.println("json\n" + json + "\n");
		
		Gson gson = new Gson();		
		Result resposta = gson.fromJson(json, Result.class);
		//System.out.println("resposta\n" + resposta.toString() + "\n");
		
		assertTrue(resposta.getData().getResults().size() > 0);
    }

    @Test
    public void testGetSeriesBySeriesId() throws Exception {

    	urlFactory = new URLFactory(privateKey, publicKey);
    	webTarget = client.target(urlFactory.getSeriesURL(Constants.ID_SERIE));
		
		Response response = webTarget				
				.request()	
				.accept(MediaType.APPLICATION_JSON)
				.get();
		
		String json = response.readEntity(String.class);
		
		Gson gson = new Gson();		
		Result resposta = gson.fromJson(json, Result.class);
        
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Result.class, Serie.class);
		Result<Serie> mappedResult = objectMapper.readValue(json, javaType);
		Serie series = mappedResult.getData().getResults().get(0);
		
		assertTrue(resposta.getData().getResults().size() != 0);
		assertNotNull(series);
		assertNotNull(series.getTitle());
		assertEquals(series.getTitle(), "4 (2004 - 2006)");
    }

    @Test
    public void testGetSeriesCharacters() throws Exception {
    	
    	SeriesParameters seriesParameters = new SeriesParameters(Constants.ID_SERIE);    	
    	urlFactory = new URLFactory(privateKey, publicKey);
    	webTarget = client.target(urlFactory.getSeriesCharactersURL(seriesParameters));
		
		Response response = webTarget				
				.request()	
				.accept(MediaType.APPLICATION_JSON)
				.get();
		
		String json = response.readEntity(String.class);
		
		Gson gson = new Gson();		
		Result resposta = gson.fromJson(json, Result.class);
		
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Result.class, MarvelCharacter.class);
		Result<MarvelCharacter> mappedResult = objectMapper.readValue(json, javaType);
		MarvelCharacter character = mappedResult.getData().getResults().get(0);
		
		assertTrue(resposta.getData().getResults().size() > 0);
		assertEquals(character.getName(), "Doctor Doom");
    }

    @Test
    public void testGetSeriesComics() throws Exception {
    	
    	SeriesParameters seriesParameters = new SeriesParameters(Constants.ID_SERIE);    	
    	urlFactory = new URLFactory(privateKey, publicKey);
    	webTarget = client.target(urlFactory.getSeriesComicsURL(seriesParameters));
		
		Response response = webTarget				
				.request()	
				.accept(MediaType.APPLICATION_JSON)
				.get();
		
		String json = response.readEntity(String.class);
		
		Gson gson = new Gson();		
		Result resposta = gson.fromJson(json, Result.class);
		
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Result.class, Comic.class);
		Result<Comic> mappedResult = objectMapper.readValue(json, javaType);
		Comic Comic = mappedResult.getData().getResults().get(0);
		
		assertTrue(resposta.getData().getResults().size() > 0);
		assertEquals(Comic.getTitle(), "4 (2004) #30");
    }

    @Test
    public void testGetSeriesCreators() throws Exception {
        
    	SeriesParameters seriesParameters = new SeriesParameters(Constants.ID_SERIE);    	
    	urlFactory = new URLFactory(privateKey, publicKey);
    	webTarget = client.target(urlFactory.getSeriesCreatorsURL(seriesParameters));
		
		Response response = webTarget				
				.request()	
				.accept(MediaType.APPLICATION_JSON)
				.get();
		
		String json = response.readEntity(String.class);
		
		Gson gson = new Gson();		
		Result resposta = gson.fromJson(json, Result.class);
		
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Result.class, Creator.class);
		Result<Creator> mappedResult = objectMapper.readValue(json, javaType);
		Creator creator = mappedResult.getData().getResults().get(0);
		
		assertTrue(resposta.getData().getResults().size() > 0);
		assertEquals(creator.getFirstName(), "Roberto");
    }

    @Test
    public void testGetSeriesStories() throws Exception {
    	
    	SeriesParameters seriesParameters = new SeriesParameters(Constants.ID_SERIE);    	
    	urlFactory = new URLFactory(privateKey, publicKey);
    	webTarget = client.target(urlFactory.getSeriesStoriesURL(seriesParameters));
		
		Response response = webTarget				
				.request()	
				.accept(MediaType.APPLICATION_JSON)
				.get();
		
		String json = response.readEntity(String.class);
		
		Gson gson = new Gson();		
		Result resposta = gson.fromJson(json, Result.class);
		
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Result.class, Story.class);
		Result<Story> mappedResult = objectMapper.readValue(json, javaType);
		Story story = mappedResult.getData().getResults().get(0);
		
		assertTrue(resposta.getData().getResults().size() > 0);
		assertEquals(story.getTitle(), "3 of 3");
    }
}
