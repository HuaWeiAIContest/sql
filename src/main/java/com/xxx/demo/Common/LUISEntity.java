package com.xxx.demo.Common;

import org.json.JSONObject;

import java.util.Map;

/**
 * Describes the LUIS Entity structure
 */
public class LUISEntity {

    private String name;
    private String type;
    private int startIndex;
    private int endIndex;
    private double score;
    private Map<String, Object> resolution;

    /**
     * Constructs a LUIS Entity from a JSON object
     * @param JSONentity a JSONObject containing the entity data
     */
    public LUISEntity(JSONObject JSONentity) {
        name = JSONentity.optString("entity");
        type = JSONentity.optString("type");
        startIndex = JSONentity.optInt("startIndex");
        endIndex = JSONentity.optInt("endIndex");
        score = JSONentity.optDouble("score");
        JSONObject JSONresolution = JSONentity.optJSONObject("resolution");
        resolution = JSONresolution != null ? LUISUtility.JSONObjectToMap(JSONresolution) : null;
    }
public LUISEntity(){

}

    public LUISEntity(String name, String type, int startIndex, int endIndex, double score, Map<String, Object> resolution) {
        this.name = name;
        this.type = type;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.score = score;
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "LUISEntity{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", score=" + score +
                ", resolution=" + resolution +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public double getScore() {
        return score;
    }

    public Map<String, Object> getResolution() {
        return resolution;
    }
}