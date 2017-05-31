package raghav.akash.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import raghav.akash.popularmovies.DetailsActivity;
import raghav.akash.popularmovies.R;
import raghav.akash.popularmovies.model.MovieDetails;
import raghav.akash.popularmovies.network.UrlGenerator;

/**
 * @author raghav
 *         Created on 23/5/16.
 */
public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ImageHolder> {

  private ArrayList<MovieDetails> movieDetailsList;
  private Context context;

  public DetailsAdapter(Context context, ArrayList<MovieDetails> movieDetailsList) {
    this.context = context;
    this.movieDetailsList = movieDetailsList;
  }

  @Override
  public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(context).inflate(R.layout.adapter_detail_view, parent, false);
    return new ImageHolder(v);
  }

  @Override
  public void onBindViewHolder(final ImageHolder holder, int position) {
    holder.containerView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(context, DetailsActivity.class);
        i.putExtra(DetailsActivity.MOVIE_DATA, movieDetailsList.get(holder.getAdapterPosition()));
        context.startActivity(i);
      }
    });
    Picasso.with(context)
        .load(UrlGenerator.getMoviePosterUrl(movieDetailsList.get(position).getImageThumbnail()))
        .placeholder(R.drawable.place_holder)
        .error(R.drawable.place_holder)
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
    @InjectView(R.id.adapter_image_view_display_image) ImageView imageView;

    ImageHolder(View itemView) {
      super(itemView);
      containerView = itemView;
      ButterKnife.inject(this, itemView);
    }
  }
}
