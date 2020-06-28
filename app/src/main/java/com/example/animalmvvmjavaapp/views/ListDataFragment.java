package com.example.animalmvvmjavaapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.animalmvvmjavaapp.R;
import com.example.animalmvvmjavaapp.adapter.AnimalAdapter;
import com.example.animalmvvmjavaapp.viewModel.ListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListDataFragment extends Fragment {

    @BindView(R.id.swipe_refresh_data)
    SwipeRefreshLayout swipeRefreshData;

    @BindView(R.id.txt_error)
    TextView txtError;

    @BindView(R.id.animal_list)
    RecyclerView animalList;

    @BindView(R.id.loading_indicator)
    ProgressBar loadingIndicator;

    private ListViewModel listViewModel;
    private AnimalAdapter animalAdapter = new AnimalAdapter();

    public ListDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_list_data, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        listViewModel.animals.observe(getViewLifecycleOwner(), animals -> {
            if (animals != null){
                animalList.setVisibility(View.VISIBLE);
                txtError.setVisibility(View.GONE);
                animalAdapter.updateAnimalList(animals);
            }
        });

        listViewModel.loading.observe(getViewLifecycleOwner(),loading ->{
            loadingIndicator.setVisibility(loading ? View.VISIBLE : View.GONE);
            if (loading){
                txtError.setVisibility(View.GONE);
                animalList.setVisibility(View.GONE);
            }
        });

        listViewModel.loadError.observe(getViewLifecycleOwner(),error ->{
            loadingIndicator.setVisibility(error ? View.VISIBLE : View.GONE);
        });

        listViewModel.setDummyData();

        if (animalList != null){
           animalList.setLayoutManager(new GridLayoutManager(getActivity(),2));
           animalList.setAdapter(animalAdapter);
        }

        swipeRefreshData.setOnRefreshListener(()->{
            animalList.setVisibility(View.GONE);
            txtError.setVisibility(View.GONE);
            loadingIndicator.setVisibility(View.VISIBLE);
            listViewModel.setDummyData();
            swipeRefreshData.setRefreshing(false);
        });


    }
}