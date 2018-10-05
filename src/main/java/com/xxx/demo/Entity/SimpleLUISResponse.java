package com.xxx.demo.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SimpleLUISResponse {
    @Id
    @Column
    private String query;

    @Column
    private String topScoringIntent;

    @Column
    private String entity;

    public SimpleLUISResponse(){

    }
    public SimpleLUISResponse(String query, String topScoringIntent, String entity) {
        this.query = query;
        this.topScoringIntent = topScoringIntent;
        this.entity = entity;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTopScoringIntent() {
        return topScoringIntent;
    }

    public void setTopScoringIntent(String topScoringIntent) {
        this.topScoringIntent = topScoringIntent;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "SimpleLUISResponse{" +
                "query='" + query + '\'' +
                ", topScoringIntent='" + topScoringIntent + '\'' +
                ", entity='" + entity + '\'' +
                '}';
    }
}
