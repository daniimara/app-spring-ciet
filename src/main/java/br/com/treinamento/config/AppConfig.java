package br.com.treinamento.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import br.com.treinamento.dojo.model.Image;
import br.com.treinamento.dojo.model.Serie;
import br.com.treinamento.dojo.service.SerieImplService;
import br.com.treinamento.dojo.service.SerieService;

@Configuration
@EnableAutoConfiguration
@ComponentScan("br.com.treinamento.dojo")
public class AppConfig {
	
	@Bean
	@Scope(value = "singleton")
	public Map<String, Object> getDb() {
		return new HashMap<String, Object>();
	}
	
	@Bean(name = "serieService")
	public SerieService serieService() {
		return new SerieImplService();
	}
	
	@Bean(name = "serie")
	public Serie serie() {
		return new Serie();
	}
	
	@Bean(name = "series")
	public ArrayList<Serie> series() {
		ArrayList<Serie> series = new ArrayList<Serie>();
		
		Serie serie1 = new Serie();
		serie1.setId(1);
		serie1.setTitle("Test Serie 1");
		serie1.setDescription("description");
		serie1.setResourceURI("resourceURI");
		serie1.setType("type");
		serie1.setUrls(null);
		serie1.setStartYear(2011);
		serie1.setEndYear(2012);
		serie1.setRating("rating");
		serie1.setModified("modified");
		serie1.setThumbnail(new Image());
		serie1.setComics(null);
		serie1.setStories(null);;
		serie1.setEvents(null);
		serie1.setCharacters(null);
		serie1.setCreators(null);
		serie1.setNext(null);
		serie1.setPrevious(null);
		series.add(serie1);
		
		Serie serie2 = new Serie();
		serie2.setId(2);
		serie2.setTitle("Test Serie 2");
		series.add(serie2);
				
		return series;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}
}
