package raghav.akash.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @author raghav
 *         Created on 23/5/16.
 */
public class ImageAdapter extends BaseAdapter {

  ArrayList<String> imageList;
  Context context;

  public ImageAdapter(Context context, ArrayList<String> imageList) {
    this.context = context;
    this.imageList = imageList;
  }

  @Override
  public int getCount() {
    return imageList.size();
  }

  @Override
  public Object getItem(int position) {
    return position;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.adapter_image_view, parent, false);
    }
    ImageView imageView = (ImageView) convertView.findViewById(R.id.adapter_image_view_display_image);
    Picasso.with(context).load(imageList.get(position)).into(imageView);
    return convertView;
  }
}
