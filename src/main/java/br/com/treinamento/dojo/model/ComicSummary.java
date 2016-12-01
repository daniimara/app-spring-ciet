package br.com.treinamento.dojo.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class ComicSummary {
    private String resourceURI;
    private String name;

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
