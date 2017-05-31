package raghav.akash.popularmovies.network;

/**
 * Created on 26/5/17.
 *
 * @author raghav
 */

public class Response {

  private int statusCode;
  private String data;
  private String message;
  private ResponseCallback responseCallback;

  public Response() {
  }

  public Response(int statusCode, String data, String message, ResponseCallback responseCallback) {
    this.statusCode = statusCode;
    this.data = data;
    this.message = message;
    this.responseCallback = responseCallback;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ResponseCallback getResponseCallback() {
    return responseCallback;
  }

  public void setResponseCallback(ResponseCallback responseCallback) {
    this.responseCallback = responseCallback;
  }
}
