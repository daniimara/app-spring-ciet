package br.com.treinamento.dojo.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class Image {
    private String path;
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
