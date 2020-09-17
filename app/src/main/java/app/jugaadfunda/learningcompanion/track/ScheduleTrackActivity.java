package app.jugaadfunda.learningcompanion.track;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.track.adapter.ScheduleTrackAdapter;

public class ScheduleTrackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_track);
        ((TextView)findViewById(R.id.tv_title)).setText(R.string.schedule_track);

        setRecycler();
    }

    private void setRecycler(){
        RecyclerView recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ScheduleTrackAdapter());
    }
}
