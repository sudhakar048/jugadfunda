package app.jugaadfunda.learningcompanion.track;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.addtrack.AddTrackActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrackFragment extends Fragment {
    private FloatingActionButton fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_track, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView webView=view.findViewById(R.id.webview);
        fab = view.findViewById(R.id.floating_add);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://radiojf.com/");

        view.findViewById(R.id.floating_add).setOnClickListener(v -> add());

        checkForUser();
    }

    private void checkForUser(){
        SharedPreferences sh = getContext().getSharedPreferences("profile", MODE_PRIVATE);
        String usertype = sh.getString("ut","");
        if(usertype.equals("mobile")){
            fab.setVisibility(View.GONE);
        }
    }

    private void add() {
        Intent intents = new Intent(getContext(), AddTrackActivity.class);
        intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intents);
    }
}
