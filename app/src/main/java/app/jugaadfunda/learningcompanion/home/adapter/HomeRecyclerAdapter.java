package app.jugaadfunda.learningcompanion.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import app.jugaadfunda.R;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.HomeRecyclerViewHolder> {

    @NonNull
    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_home_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    static class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {

        public HomeRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.findViewById(R.id.iv_more).setOnClickListener(view -> {
                PopupMenu popupMenu=new PopupMenu(view.getContext(),view);
                popupMenu.inflate(R.menu.popup_languages);
                popupMenu.show();
            });
        }
    }
}
