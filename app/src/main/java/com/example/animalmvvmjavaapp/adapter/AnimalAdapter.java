package com.example.animalmvvmjavaapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalmvvmjavaapp.R;
import com.example.animalmvvmjavaapp.model.Animal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalHolder> {

    private ArrayList<Animal>animalList = new ArrayList<>();

    public void updateAnimalList(List<Animal> newAnimalList) {
        animalList.clear();
        animalList.addAll(newAnimalList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnimalAdapter.AnimalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_animal,parent,false);
        return new AnimalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.AnimalHolder holder, int position) {
        //final Animal animal = animalList.get(position);

        //holder.txtAnimalName.setText(animalList.get(position).name);
        TextView animalName = holder.itemView.findViewById(R.id.txt_animal_name);
        animalName.setText(animalList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public class AnimalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_animal)ImageView imgAnimal;
        //@BindView(R.id.txt_animal_name) TextView txtAnimalName;

        public AnimalHolder(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
