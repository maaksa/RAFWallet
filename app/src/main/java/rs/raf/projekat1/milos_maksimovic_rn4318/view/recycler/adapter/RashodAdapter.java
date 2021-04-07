package rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Function;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;

public class RashodAdapter extends ListAdapter<Rashod, RashodAdapter.ViewHolder> {

    private final Function<Rashod, Void> onRashodClicked;

    public RashodAdapter(@NonNull DiffUtil.ItemCallback<Rashod> diffCallback, Function<Rashod, Void> onRashodClicked) {
        super(diffCallback);
        this.onRashodClicked = onRashodClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prihod_rashod_list_item, parent, false);
        return new RashodAdapter.ViewHolder(view, parent.getContext(), position -> {
            Rashod rashod = getItem(position);
            onRashodClicked.apply(rashod);
            return null;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rashod rashod = getItem(position);
        holder.bind(rashod);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        public ViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onItemClicked) {
            super(itemView);
            this.context = context;

            ImageView deleteIv = itemView.findViewById(R.id.deleteIvListItem);
            deleteIv.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.apply(getAdapterPosition());
                }
            });
        }

        public void bind(Rashod rashod) {
            ImageView imageView = itemView.findViewById(R.id.dollarPictureIv);
            imageView.setColorFilter(Color.RED);
            ((TextView) itemView.findViewById(R.id.naslovTvListItem)).setText(rashod.getNaslov());
            ((TextView) itemView.findViewById(R.id.kolicinaTvListItem)).setText(Integer.toString(rashod.getKolicina()));
        }
    }
}
