package br.com.treinamento.dojo.exception;

import javax.ws.rs.core.Response;

import java.io.IOException;

public class MarvelRestException extends RuntimeException {

    private final Response response;

    public MarvelRestException(Response response) throws IOException {
        super("Erro na consulta da API.");
        this.response = response;
    }
}
