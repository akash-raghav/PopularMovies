package raghav.akash.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import raghav.akash.popularmovies.util.Constants;

/**
 * @author Raghav
 * Created on 1/12/16.
 */

public class MovieDetails implements Parcelable {

  private String id;
  private String title;
  private String imageThumbnail;
  private String overview;
  private String rating;
  private String releaseDate;

  public MovieDetails() {
  }

  public static MovieDetails parseDetails(JSONObject jsonObject) throws JSONException {
    MovieDetails details = new MovieDetails();
    details.id = jsonObject.getString(Constants.ID);
    details.title = jsonObject.getString(Constants.ORIGINAL_TITLE);
    details.imageThumbnail = jsonObject.getString(Constants.POSTER_PATH);
    details.overview = jsonObject.getString(Constants.OVERVIEW);
    details.rating = jsonObject.getString(Constants.VOTE_AVERAGE);
    details.releaseDate = jsonObject.getString(Constants.RELEASE_DATE);
    return details;
  }

  public String getTitle() {
    return title;
  }

  public String getOverview() {
    return overview;
  }

  public String getRating() {
    return rating;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public String getImageThumbnail() {
    return imageThumbnail;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.id);
    dest.writeString(this.title);
    dest.writeString(this.imageThumbnail);
    dest.writeString(this.overview);
    dest.writeString(this.rating);
    dest.writeString(this.releaseDate);
  }

  protected MovieDetails(Parcel in) {
    this.id = in.readString();
    this.title = in.readString();
    this.imageThumbnail = in.readString();
    this.overview = in.readString();
    this.rating = in.readString();
    this.releaseDate = in.readString();
  }

  public static final Creator<MovieDetails> CREATOR = new Creator<MovieDetails>() {
    @Override
    public MovieDetails createFromParcel(Parcel source) {
      return new MovieDetails(source);
    }

    @Override
    public MovieDetails[] newArray(int size) {
      return new MovieDetails[size];
    }
  };
}
