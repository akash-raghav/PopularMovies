package raghav.akash.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import raghav.akash.popularmovies.R;
import raghav.akash.popularmovies.model.Trailer;
import raghav.akash.popularmovies.network.UrlGenerator;

/**
 * @author raghav
 *         Created on 23/5/16.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ImageHolder> {

  private ArrayList<Trailer> trailersList;
  private Context context;

  public TrailerAdapter(Context context, ArrayList<Trailer> trailersList) {
    this.context = context;
    this.trailersList = trailersList;
  }

  @Override
  public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(context).inflate(R.layout.adapter_trailer_view, parent, false);
    return new ImageHolder(v);
  }

  @Override
  public void onBindViewHolder(final ImageHolder holder, int position) {
    holder.imageView.setImageResource(R.drawable.place_holder);
    holder.textView.setText(trailersList.get(holder.getAdapterPosition()).getName());
    holder.containerView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(UrlGenerator.getYoutubeTrailerURL(trailersList.get(holder.getAdapterPosition()).getKey())));
        context.startActivity(i);
      }
    });
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public int getItemCount() {
    return trailersList.size();
  }

  public void updateList(ArrayList<Trailer> trailersList) {
    this.trailersList = trailersList;
    notifyDataSetChanged();
  }

  static class ImageHolder extends RecyclerView.ViewHolder {

    View containerView;
    @InjectView(R.id.adapter_trailer_view_image)
    ImageView imageView;
    @InjectView(R.id.adapter_trailer_view_name)
    TextView textView;

    ImageHolder(View itemView) {
      super(itemView);
      containerView = itemView;
      ButterKnife.inject(this, itemView);
    }
  }
}
