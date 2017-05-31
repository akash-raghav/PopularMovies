package raghav.akash.popularmovies.network;

import java.util.Map;

import raghav.akash.popularmovies.util.Constants;

/**
 * Created on 26/5/17.
 *
 * @author raghav
 */

public class Request {

  private String url;
  private String requestType;
  private Map<String, String> headers;
  private Map<String, String> params;
  private ResponseCallback responseCallback;

  public Request(String url, ResponseCallback responseCallback) {
    this(url, Constants.GET, null, null, responseCallback);
  }

  public Request(String url, Map<String, String> params, ResponseCallback responseCallback) {
    this(url, Constants.GET, null, params, responseCallback);
  }

  public Request(String url, String requestType, Map<String, String> params, ResponseCallback responseCallback) {
    this(url, requestType, null, params, responseCallback);
  }

  public Request(String url, String requestType, Map<String, String> headers, Map<String, String> params, ResponseCallback responseCallback) {
    this.url = url;
    this.requestType = requestType;
    this.headers = headers;
    this.params = params;
    this.responseCallback = responseCallback;
  }

  public String getRequestType() {
    return requestType;
  }

  public void setRequestType(String requestType) {
    this.requestType = requestType;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public void setParams(Map<String, String> params) {
    this.params = params;
  }

  public ResponseCallback getResponseCallback() {
    return responseCallback;
  }

  public void setResponseCallback(ResponseCallback responseCallback) {
    this.responseCallback = responseCallback;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
