package br.com.treinamento.dojo.dto;

import java.io.Serializable;

public class ResponseProtocol implements Serializable {

	private static final long serialVersionUID = -3858804672246744328L;
	
	private String mensagem;
	private Object data;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
