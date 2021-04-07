package rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.concurrent.TimeoutException;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;

public class PrihodAdapter extends ListAdapter<Prihod, PrihodAdapter.ViewHolder> {

    public PrihodAdapter(@NonNull DiffUtil.ItemCallback<Prihod> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prihod_rashod_list_item, parent, false);
        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Prihod prihod = getItem(position);
        holder.bind(prihod);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
        }

        public void bind(Prihod prihod) {
            ImageView imageView = itemView.findViewById(R.id.dollarPictureIv);
            imageView.setColorFilter(Color.GREEN);
            ((TextView) itemView.findViewById(R.id.naslovTvListItem)).setText(prihod.getNaslov());
            ((TextView) itemView.findViewById(R.id.kolicinaTvListItem)).setText(Integer.toString(prihod.getKolicina()));
        }
    }
}
