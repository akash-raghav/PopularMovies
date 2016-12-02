package raghav.akash.popularmovies;

import android.content.Context;
import android.content.Intent;
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

  private ArrayList<MovieDetails> movieDetailsList;
  private Context context;

  ImageAdapter(Context context, ArrayList<MovieDetails> movieDetailsList) {
    this.context = context;
    this.movieDetailsList = movieDetailsList;
  }

  @Override
  public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(context).inflate(R.layout.adapter_image_view, parent, false);
    return new ImageHolder(v);
  }

  @Override
  public void onBindViewHolder(final ImageHolder holder, int position) {
    holder.containerView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(context, DetailScreenActivity.class);
        i.putExtra(DetailScreenActivity.MOVIE_DATA, movieDetailsList.get(holder.getAdapterPosition()));
        context.startActivity(i);
      }
    });
    Picasso.with(context)
        .load(context.getString(R.string.base_image_url) + movieDetailsList.get(position).getImageThumbnail())
        .into(holder.imageView);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public int getItemCount() {
    return movieDetailsList.size();
  }

  public void updateList(ArrayList<MovieDetails> movieDetailsList) {
    this.movieDetailsList = movieDetailsList;
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
