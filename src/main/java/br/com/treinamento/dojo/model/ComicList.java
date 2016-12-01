package br.com.treinamento.dojo.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class ComicList extends AbstractList {
    private List<ComicSummary> items;

    public List<ComicSummary> getItems() {
        return items;
    }

    public void setItems(List<ComicSummary> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
