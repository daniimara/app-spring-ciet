package br.com.treinamento.dojo.service;

import java.util.List;

import br.com.treinamento.dojo.model.Serie;

public interface SerieService {
	public List<Serie> getAll();
	public Serie get(Integer id);
	public Serie get();
}
