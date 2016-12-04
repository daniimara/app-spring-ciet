package br.com.treinamento.dojo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinamento.dojo.model.Comic;
import br.com.treinamento.dojo.parameter.AbstractParameters;
import br.com.treinamento.dojo.parameter.ComicParameters;
import br.com.treinamento.dojo.parameter.CreatorParameters;
import br.com.treinamento.dojo.parameter.EventParameters;
import br.com.treinamento.dojo.parameter.StoryParameters;
import gumi.builders.UrlBuilder;

//@Component
public class ComicImplService implements ComicService {
	/*
	private static final String BASE_URL = "http://gateway.marvel.com/v1/public/";
	private static final String COMICS_URL = BASE_URL + "comics";
    private static final String COMICS_CHARACTERS_URL = BASE_URL + "comics/%d/characters";
    private static final String COMICS_CREATORS_URL = BASE_URL + "comics/%d/creators";
    private static final String COMICS_EVENTS_URL = BASE_URL + "comics/%d/events";
    private static final String COMICS_STORIES_URL = BASE_URL + "comics/%d/stories";
    private static final String COMICS_BY_ID_URL = BASE_URL + "comics/%d";
    
	private String publicKey;
    private String privateKey;
	
	@Autowired
	private ArrayList<Comic> comics;
	
	@Autowired
	private Comic comic;

	public ComicImplService() {
		
	}
	
	public ComicImplService(String publicKey, String privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public ArrayList<Comic> getComics() {
		return comics;
	}

	public void setComics(ArrayList<Comic> comics) {
		this.comics = comics;
	}
	
	@Override
	public List<Comic> getAll() {
		return this.getComics();
	}

	@Override
	public Comic get() {
		return comic;
	}
	
	@Override
	public Comic get(Integer id) {
		for (Comic item : getComics()) {
			if (item.getId() == id)
				return item;
		}
		return null;
	}
	
	@Override
    public String getPublicKey() {
		return publicKey;
	}

	@Override
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	@Override
	public String getPrivateKey() {
		return privateKey;
	}

	@Override
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	@Override
	public String getComicsURL() {
        UrlBuilder urlBuilder = UrlBuilder.fromString(COMICS_URL);
        urlBuilder = addAuthorisationParameters(urlBuilder);
        return urlBuilder.toString();
    }

	@Override
    public String getComicsCharactersURL(ComicParameters comicParameters) {
        final String url = String.format(COMICS_CHARACTERS_URL, comicParameters.getId());
        return buildURL(url, comicParameters);
    }

	@Override
    public String getComicsCreatorsURL(CreatorParameters creatorParameters) {
        final String url = String.format(COMICS_CREATORS_URL, creatorParameters.getId());
        return buildURL(url, creatorParameters);
    }

	@Override
    public String getComicsEventsURL(EventParameters eventParameters) {
        final String url = String.format(COMICS_EVENTS_URL, eventParameters.getId());
        return buildURL(url, eventParameters);
    }

	@Override
    public String getComicsStoriesURL(StoryParameters storyParameters) {
        final String url = String.format(COMICS_STORIES_URL, storyParameters.getId());
        return buildURL(url, storyParameters);
    }

    @Override
    public String getComicsURL(int comicId) {
        final String url = String.format(COMICS_BY_ID_URL, comicId);
        UrlBuilder urlBuilder = UrlBuilder.fromString(url);
        urlBuilder = addAuthorisationParameters(urlBuilder);
        return urlBuilder.toString();
    }
    
    private <T extends AbstractParameters> String buildURL(String url, T parameters) {
        UrlBuilder urlBuilder = UrlBuilder.fromString(url);
        urlBuilder = parameters.addParameters(urlBuilder);
        urlBuilder = addAuthorisationParameters(urlBuilder);
        return urlBuilder.toString();
    }
    
    private UrlBuilder addAuthorisationParameters(UrlBuilder urlBuilder) {
        long timeStamp = System.currentTimeMillis();
        return urlBuilder.addParameter("ts", String.valueOf(timeStamp))
                .addParameter("apikey", publicKey)
                .addParameter("hash", createHash(timeStamp));
    }
    
    private String createHash(long timeStamp) {
        String stringToHash = timeStamp + privateKey + publicKey;
        return DigestUtils.md5Hex(stringToHash);
    }*/
}