package com.xxx.demo.Common;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes the LUIS Intent structure
 */
public class LUISIntent {

    private String name;
    private double score;

    /**
     * Constructs a LUIS Intention from a JSON object
     * @param JSONintent a JSONObject containing the intent data
     */
    public LUISIntent(JSONObject JSONintent) {
        name = JSONintent.optString("intent");
        score = JSONintent.optDouble("score");

        JSONArray JSONactions = JSONintent.optJSONArray("actions");

        for (int i = 0; JSONactions != null && i < JSONactions.length(); i++) {
            JSONObject JSONaction = JSONactions.optJSONObject(i);
        }
    }
    public LUISIntent(){

    }

    public LUISIntent(String name, double score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "LUISIntent{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

}
