package rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;

public class RashodDiffItemCallback extends DiffUtil.ItemCallback<Rashod> {

    @Override
    public boolean areItemsTheSame(@NonNull Rashod oldItem, @NonNull Rashod newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Rashod oldItem, @NonNull Rashod newItem) {
        return oldItem.getNaslov().equals(newItem.getNaslov())
                && oldItem.getKolicina() == (newItem.getKolicina());
    }

}
