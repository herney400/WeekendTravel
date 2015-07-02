package Geolocalizacion;


import com.dd.StrokeGradientDrawable;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by N550J on 14/06/2015.
 */
public class Result {

    private JSONObject geometry;
    private int icon;
    private String id;
    private String name;
    private JSONObject opening_hours;
    private JSONArray photos;
    private String place_id;
    private int price_level;
    private String reference;
    private String scope;
    private JSONArray types;
    private String vecinity;
    public Result(  int icon, String id, String name ,
                    String place_id,
                    int price_level, String reference,
                    String scope,
                    String vecinity) {

        this.icon = icon;
        this.id = id;
        this.name = name;
        this.place_id = place_id;
        this.price_level = price_level;
        this.reference = reference;
        this.scope = scope;

        this.vecinity = vecinity;
    }

    public int getPrice_level() {
        return price_level;
    }

    public void setPrice_level(int price_level) {
        this.price_level = price_level;
    }

    public int getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public JSONObject getOpening_hours() {
        return opening_hours;
    }

    public JSONArray getPhotos() {
        return photos;
    }

    public String getPlace_id() {
        return place_id;
    }

    public String getReference() {
        return reference;
    }

    public String getScope() {
        return scope;
    }

    public JSONArray getTypes() {
        return types;
    }

    public String getVecinity() {
        return vecinity;
    }

    public JSONObject getGeometry() {

        return geometry;
    }

    public void setGeometry(JSONObject geometry) {
        this.geometry = geometry;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpening_hours(JSONObject opening_hours) {
        this.opening_hours = opening_hours;
    }

    public void setPhotos(JSONArray photos) {
        this.photos = photos;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setTypes(JSONArray types) {
        this.types = types;
    }

    public void setVecinity(String vecinity) {
        this.vecinity = vecinity;
    }
}
