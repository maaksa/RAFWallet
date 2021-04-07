package rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;

//za animacije dodavanja izmene itd
public class RashodDiffItemCallback extends DiffUtil.ItemCallback<Rashod> {

    //proveravace da li je ceo item u VH isti koji se rekreira
    @Override
    public boolean areItemsTheSame(@NonNull Rashod oldItem, @NonNull Rashod newItem) {
        return oldItem.getId() == newItem.getId();
    }

    //proveravace da li ce stvari unutar tog item isti
    @Override
    public boolean areContentsTheSame(@NonNull Rashod oldItem, @NonNull Rashod newItem) {
        return oldItem.getNaslov().equals(newItem.getNaslov())
                && oldItem.getKolicina() == (newItem.getKolicina());
    }

}
