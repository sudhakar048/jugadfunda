package app.jugaadfunda.learningcompanion.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.jugaadfunda.R;

public class NotificationsRecyclerAdapter extends RecyclerView.Adapter<NotificationsRecyclerAdapter.NotificationsViewHolder> {

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_notifications,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class NotificationsViewHolder extends RecyclerView.ViewHolder {

        public NotificationsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
