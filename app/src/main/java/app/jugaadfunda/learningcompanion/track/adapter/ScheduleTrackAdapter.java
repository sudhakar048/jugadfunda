package app.jugaadfunda.learningcompanion.track.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.jugaadfunda.R;

public class ScheduleTrackAdapter extends RecyclerView.Adapter<ScheduleTrackAdapter.ScheduleTrackHolder> {


    @NonNull
    @Override
    public ScheduleTrackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScheduleTrackHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_schedule, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleTrackHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class ScheduleTrackHolder extends RecyclerView.ViewHolder{

        ScheduleTrackHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
