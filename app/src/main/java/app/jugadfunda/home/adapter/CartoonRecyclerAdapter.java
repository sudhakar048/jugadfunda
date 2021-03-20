package app.jugadfunda.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.jugadfunda.R;

public class CartoonRecyclerAdapter extends RecyclerView.Adapter<CartoonRecyclerAdapter.CartoonViewHolder> {

    @NonNull
    @Override
    public CartoonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartoonViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cartoon,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartoonViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class CartoonViewHolder extends RecyclerView.ViewHolder {

        public CartoonViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
