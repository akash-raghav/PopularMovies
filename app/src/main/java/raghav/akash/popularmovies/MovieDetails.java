package raghav.akash.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by raghav on 1/12/16.
 */

public class MovieDetails implements Parcelable {

  public static final Parcelable.Creator<MovieDetails> CREATOR = new Parcelable.Creator<MovieDetails>() {
    @Override
    public MovieDetails createFromParcel(Parcel source) {
      return new MovieDetails(source);
    }

    @Override
    public MovieDetails[] newArray(int size) {
      return new MovieDetails[size];
    }
  };
  private String title;
  private String imageThumbnail;
  private String overview;
  private String rating;
  private String releaseDate;

  public MovieDetails() {
  }

  protected MovieDetails(Parcel in) {
    this.title = in.readString();
    this.imageThumbnail = in.readString();
    this.overview = in.readString();
    this.rating = in.readString();
    this.releaseDate = in.readString();
  }

  public static MovieDetails parseDetails(JSONObject jsonObject) throws JSONException {
    MovieDetails details = new MovieDetails();
    details.title = jsonObject.getString("original_title");
    details.imageThumbnail = jsonObject.getString("poster_path");
    details.overview = jsonObject.getString("overview");
    details.rating = jsonObject.getString("vote_average");
    details.releaseDate = jsonObject.getString("release_date");
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
    dest.writeString(this.title);
    dest.writeString(this.imageThumbnail);
    dest.writeString(this.overview);
    dest.writeString(this.rating);
    dest.writeString(this.releaseDate);
  }
}
