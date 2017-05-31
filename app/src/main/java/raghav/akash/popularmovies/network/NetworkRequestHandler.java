package raghav.akash.popularmovies.network;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Scanner;

import raghav.akash.popularmovies.util.CommonUtils;
import raghav.akash.popularmovies.util.Constants;
import raghav.akash.popularmovies.util.CustomLog;

/**
 * Created on 26/5/17.
 *
 * @author raghav
 */

public class NetworkRequestHandler {

  private static final int SINGLE_REQUEST = 1;
  private static final int SINGLE_RESPONSE = 2;
  public static boolean isSetup;
  private static Handler uiHandler;
  private static Handler backgroundHandler;
  private static BackgroundThread backgroundThread;

  public static void setupNetworkHandler(Context context) {
    isSetup = true;
    uiHandler = new UIHandler(context.getMainLooper());
    backgroundThread = new BackgroundThread("BACKGROUND_THREAD");
    backgroundThread.start();
    backgroundThread.prepareHandler();
  }

  public static void sendRequest(Context context, Request request) {
    if (CommonUtils.isNetworkConnected(context)) {
      Message message = backgroundHandler.obtainMessage(SINGLE_REQUEST, request);
      backgroundHandler.sendMessage(message);
    } else {
      request.getResponseCallback().onFailure(new Response(Constants.FAILURE, null, "No Internet", null));
    }
  }

  private static String addParams(String urlString, Map<String, String> params) {
    StringBuilder stringBuilder = new StringBuilder(urlString);
    boolean flag = true;
    if (!urlString.contains(Constants.URL_QUERY_SEPARATOR)) {
      stringBuilder.append(Constants.URL_QUERY_SEPARATOR);
      flag = false;
    }
    for (Map.Entry<String, String> entry : params.entrySet()) {
      if (flag) {
        stringBuilder.append(Constants.URL_PARAM_SEPARATOR);
      }
      flag = false;
      stringBuilder.append(entry.getKey())
          .append(Constants.URL_KEY_VALUE_SEPARATOR)
          .append(entry.getValue());
    }
    return stringBuilder.toString();
  }

  private static String getQuery(Map<String, String> params) throws UnsupportedEncodingException {
    StringBuilder result = new StringBuilder();
    boolean first = true;

    for (Map.Entry<String, String> pair : params.entrySet()) {
      if (first)
        first = false;
      else
        result.append("&");

      result.append(URLEncoder.encode(pair.getKey(), "UTF-8"));
      result.append("=");
      result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
    }

    return result.toString();
  }

  private static class BackgroundHandler extends Handler {

    BackgroundHandler(Looper looper) {
      super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
      if (msg.what == SINGLE_REQUEST) {
        Request request = (Request) msg.obj;
        Response response = new Response();
        HttpURLConnection conn = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
          URL url = new URL(addParams(request.getUrl(), request.getParams()));
          conn = (HttpURLConnection) url.openConnection();
          InputStream inputStream = conn.getInputStream();
          Scanner scanner = new Scanner(inputStream);
          while (scanner.hasNext()) {
            stringBuilder.append(scanner.next());
          }
          CustomLog.d("API REQUEST : " + url.toString());
          CustomLog.d("API RESPONSE : " + stringBuilder.toString());
          response = new Response(Constants.SUCCESS, stringBuilder.toString(), "", request.getResponseCallback());
        } catch (IOException e) {
          response = new Response(Constants.FAILURE, null, e.getLocalizedMessage(), request.getResponseCallback());
        } finally {
          if (conn != null) {
            conn.disconnect();
          }
        }

        Message message = uiHandler.obtainMessage(SINGLE_RESPONSE, response);
        uiHandler.sendMessage(message);
      }
    }
  }

  private static class UIHandler extends Handler {

    UIHandler(Looper looper) {
      super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
      if (msg.what == SINGLE_RESPONSE) {
        Response response = (Response) msg.obj;
        if (response.getStatusCode() == Constants.SUCCESS) {
          response.getResponseCallback().onSuccess(response);
        } else {
          response.getResponseCallback().onFailure(response);
        }
      }
    }
  }

  private static class BackgroundThread extends HandlerThread {

    BackgroundThread(String name) {
      super(name);
    }

    void prepareHandler() {
      backgroundHandler = new BackgroundHandler(backgroundThread.getLooper());
    }
  }
}
