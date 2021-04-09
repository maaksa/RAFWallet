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
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Action;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;

public class RashodAdapter extends ListAdapter<Rashod, RashodAdapter.ViewHolder> {

    private final Function<Rashod, Void> onRashodClicked;
    private final Function<Action, Void> onRashodClickedAction;

    public RashodAdapter(@NonNull DiffUtil.ItemCallback<Rashod> diffCallback, Function<Rashod, Void> onRashodClicked, Function<Action, Void> onRashodClickedAction) {
        super(diffCallback);
        this.onRashodClicked = onRashodClicked;
        this.onRashodClickedAction = onRashodClickedAction;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prihod_rashod_list_item, parent, false);
        ViewHolder vh = new ViewHolder(view, parent.getContext(), position -> {
            Rashod rashod = getItem(position);
            onRashodClicked.apply(rashod);
            return null;
        }, action -> {
            onRashodClickedAction.apply(action);
            return null;
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rashod rashod = getItem(position);
        holder.bind(rashod);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private final Function<Integer, Void> onItemClicked;
        private final Function<Action, Void> onItemClickedAction;

        public ViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onItemClicked, Function<Action, Void> onItemClickedAction) {
            super(itemView);
            this.context = context;
            this.onItemClicked = onItemClicked;
            this.onItemClickedAction = onItemClickedAction;

            ImageView deleteIv = itemView.findViewById(R.id.deleteIvListItem);
            deleteIv.setOnClickListener(RashodAdapter.ViewHolder.this);
            ImageView editIv = itemView.findViewById(R.id.editIvListItem);
            editIv.setOnClickListener(RashodAdapter.ViewHolder.this);
            View show = itemView.findViewById(R.id.viewHolderShow);
            show.setOnClickListener(RashodAdapter.ViewHolder.this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.editIvListItem:
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        onItemClicked.apply(getAdapterPosition());
                        onItemClickedAction.apply(Action.EDIT);
                    }
                    break;
                case R.id.deleteIvListItem:
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        onItemClicked.apply(getAdapterPosition());
                        onItemClickedAction.apply(Action.DELETE);
                    }
                    break;
                default:
                    onItemClicked.apply(getAdapterPosition());
                    onItemClickedAction.apply(Action.SHOW);
                    break;
            }
        }

        public void bind(Rashod rashod) {
            ImageView imageView = itemView.findViewById(R.id.dollarPictureIv);
            imageView.setColorFilter(Color.RED);
            ((TextView) itemView.findViewById(R.id.naslovTvListItem)).setText(rashod.getNaslov());
            ((TextView) itemView.findViewById(R.id.kolicinaTvListItem)).setText(Integer.toString(rashod.getKolicina()));
        }
    }

}
