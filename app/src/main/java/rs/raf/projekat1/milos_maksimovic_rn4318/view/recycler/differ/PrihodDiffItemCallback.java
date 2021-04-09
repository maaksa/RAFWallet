package rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;

public class PrihodDiffItemCallback extends DiffUtil.ItemCallback<Prihod> {

    @Override
    public boolean areItemsTheSame(@NonNull Prihod oldItem, @NonNull Prihod newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Prihod oldItem, @NonNull Prihod newItem) {
        return oldItem.getNaslov().equals(newItem.getNaslov())
                && oldItem.getKolicina() == (newItem.getKolicina());
    }

}
