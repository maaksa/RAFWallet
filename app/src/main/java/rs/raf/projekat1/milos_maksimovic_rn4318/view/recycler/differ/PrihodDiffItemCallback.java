package rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;

//za animacije dodavanja izmene itd
public class PrihodDiffItemCallback extends DiffUtil.ItemCallback<Prihod> {

    //proveravace da li je ceo item u VH isti koji se rekreira
    @Override
    public boolean areItemsTheSame(@NonNull Prihod oldItem, @NonNull Prihod newItem) {
        return oldItem.getId() == newItem.getId();
    }

    //proveravace da li ce stvari unutar tog item isti
    @Override
    public boolean areContentsTheSame(@NonNull Prihod oldItem, @NonNull Prihod newItem) {
        return oldItem.getNaslov().equals(newItem.getNaslov())
                && oldItem.getKolicina() == (newItem.getKolicina());
    }

}
