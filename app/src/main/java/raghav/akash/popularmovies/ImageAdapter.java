package raghav.akash.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @author raghav
 *         Created on 23/5/16.
 */
class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

  private ArrayList<String> imageList;
  private Context context;

  ImageAdapter(Context context, ArrayList<String> imageList) {
    this.context = context;
    this.imageList = imageList;
  }

  @Override
  public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(context).inflate(R.layout.adapter_image_view, parent, false);
    return new ImageHolder(v);
  }

  @Override
  public void onBindViewHolder(ImageHolder holder, int position) {
    Picasso.with(context).load(imageList.get(position)).into(holder.imageView);
  }


  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public int getItemCount() {
    return imageList.size();
  }

  public void updateList(ArrayList<String> imageList) {
    this.imageList = imageList;
    notifyDataSetChanged();
  }

  static class ImageHolder extends RecyclerView.ViewHolder {

    View containerView;
    ImageView imageView;

    ImageHolder(View itemView) {
      super(itemView);
      containerView = itemView;
      imageView = (ImageView) itemView.findViewById(R.id.adapter_image_view_display_image);
    }
  }
}
