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

import br.com.treinamento.dojo.model.Serie;
import br.com.treinamento.dojo.service.SerieImplService;
import br.com.treinamento.dojo.service.SerieService;

@Configuration
@EnableAutoConfiguration
@ComponentScan("br.com.treinamento.dojo")
public class TestConfig {
	
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
		serie1.setTitle("Teste Serie 1");
		series.add(serie1);
		
		Serie serie2 = new Serie();
		serie1.setTitle("Teste Serie 2");
		series.add(serie2);
				
		return series;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}
}
