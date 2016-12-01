package br.com.treinamento.dojo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinamento.dojo.model.Serie;

@Component
public class SerieImplService implements SerieService {
	
	@Autowired
	private ArrayList<Serie> series;

	public ArrayList<Serie> getSeries() {
		return series;
	}

	public void setSeries(ArrayList<Serie> series) {
		this.series = series;
	}
	
	@Override
	public List<Serie> getAll() {
		return this.getSeries();
	}

	@Override
	public Serie get(Integer id) {
		for (Serie item : getSeries()) {
			if (item.getId() == id)
				return item;
		}
		return null;
	}
}
