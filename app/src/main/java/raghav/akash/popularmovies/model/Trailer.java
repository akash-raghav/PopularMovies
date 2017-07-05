package raghav.akash.popularmovies.model;

import org.json.JSONException;
import org.json.JSONObject;

import raghav.akash.popularmovies.util.CustomLog;

/**
 * Created on 22/5/17.
 *
 * @author raghav
 */

public class Trailer {

  private String id;
  private String key;
  private String name;
  private String site;
  private int size;
  private String type;

  public static Trailer parseTrailer(JSONObject jsonObject) {
    Trailer trailer = new Trailer();
    try {
      trailer.id = jsonObject.getString("id");
      trailer.key = jsonObject.getString("key");
      trailer.name = jsonObject.getString("name");
      trailer.site = jsonObject.getString("site");
      trailer.size = jsonObject.getInt("size");
      trailer.type = jsonObject.getString("type");
    } catch (JSONException e) {
      CustomLog.e(e);
    }
    return trailer;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}