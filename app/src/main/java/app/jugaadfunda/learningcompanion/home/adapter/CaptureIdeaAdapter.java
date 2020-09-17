package app.jugaadfunda.learningcompanion.home.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.home.captureidea.CaptureIdeaInterfaceView;
import app.jugaadfunda.learningcompanion.home.pojo.CaptureIdeaPojo;

public class CaptureIdeaAdapter extends RecyclerView.Adapter<CaptureIdeaAdapter.CaptureIdeaHolder> {

    private Context mContext;
    private ArrayList<CaptureIdeaPojo> mList;
    private CaptureIdeaInterfaceView captureIdeaInterfaceView;

    public CaptureIdeaAdapter(Context mContext, ArrayList<CaptureIdeaPojo> mList, CaptureIdeaInterfaceView captureIdeaInterfaceView){
        this.mContext = mContext;
        this.mList = mList;
        this.captureIdeaInterfaceView = captureIdeaInterfaceView;
    }

    @NonNull
    @Override
    public CaptureIdeaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CaptureIdeaAdapter.CaptureIdeaHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_captureidea,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CaptureIdeaHolder holder, int position) {
        CaptureIdeaPojo cip = mList.get(position);
        holder.et_day.setText(cip.getDay());
        holder.et_title.setText(cip.getIdeatitle());
        holder.et_desc.setText(cip.getDescription());
        holder.et_unique.setText(cip.getUnique());
        holder.et_better.setText(cip.getBetter());
        holder.et_futureproof.setText(cip.getFutureproof());
        holder.et_triggered.setText(cip.getTriggered());
        holder.et_otherfutureproof.setText(cip.getFutureproofotherways());
        holder.et_problemsolving.setText(cip.getProblemsolving());

        Log.e("cip.getAttachfile()",""+cip.getAttachfile());
        if(cip.getAttachfile() != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(cip.getAttachfile(), 0, cip.getAttachfile().length);
            holder.iv_image.setImageBitmap(bitmap);
            holder.iv_image.setVisibility(View.VISIBLE);
        }else{
            holder.iv_image.setVisibility(View.GONE);
        }

        holder.btn_savechanges.setTag(position);
        holder.btn_addfile.setTag(position);

        holder.btn_savechanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cip.getAttachfile() != null){
                    if(cip.getAttachfile().length >= 1024 * 1024){
                        Toast.makeText(mContext,"File size can't be more than 1MB",Toast.LENGTH_LONG).show();
                    }else{
                        captureIdeaInterfaceView.saveData(new CaptureIdeaPojo(holder.et_day.getText().toString(), holder.et_title.getText().toString(), holder.et_desc.getText().toString(), holder.et_unique.getText().toString(), holder.et_better.getText().toString(), holder.et_futureproof.getText().toString(), holder.et_triggered.getText().toString(), holder.et_otherfutureproof.getText().toString(), holder.et_problemsolving.getText().toString(), cip.getAttachfile(), cip.getUserid()), position);
                    }
                }else {
                    captureIdeaInterfaceView.saveData(new CaptureIdeaPojo(holder.et_day.getText().toString(), holder.et_title.getText().toString(), holder.et_desc.getText().toString(), holder.et_unique.getText().toString(), holder.et_better.getText().toString(), holder.et_futureproof.getText().toString(), holder.et_triggered.getText().toString(), holder.et_otherfutureproof.getText().toString(), holder.et_problemsolving.getText().toString(), null, cip.getUserid()), position);
                }
            }
        });

        holder.btn_addfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    captureIdeaInterfaceView.uploadFile(new CaptureIdeaPojo(holder.et_day.getText().toString(),holder.et_title.getText().toString(),holder.et_desc.getText().toString(),holder.et_unique.getText().toString(),holder.et_better.getText().toString(),holder.et_futureproof.getText().toString(),holder.et_triggered.getText().toString(),holder.et_otherfutureproof.getText().toString(),holder.et_problemsolving.getText().toString(),cip.getAttachfile(), cip.getUserid()),position);
                }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class CaptureIdeaHolder extends RecyclerView.ViewHolder{
        EditText et_day;
        TextInputEditText et_title;
        TextInputEditText et_desc;
        TextInputEditText et_unique;
        TextInputEditText et_better;
        TextInputEditText et_futureproof;
        TextInputEditText et_triggered;
        TextInputEditText et_otherfutureproof;
        TextInputEditText et_problemsolving;
        Button btn_savechanges;
        Button btn_addfile;
        ImageView iv_image;

        public CaptureIdeaHolder(@NonNull View itemView) {
            super(itemView);
            et_day = itemView.findViewById(R.id.et_day);
            et_title = itemView.findViewById(R.id.et_title);
            et_desc = itemView.findViewById(R.id.et_desc);
            et_unique = itemView.findViewById(R.id.et_unique);
            et_better = itemView.findViewById(R.id.et_better);
            et_futureproof = itemView.findViewById(R.id.et_futureproof);
            et_triggered = itemView.findViewById(R.id.et_triggered);
            et_otherfutureproof = itemView.findViewById(R.id.et_otherfutureproof);
            et_problemsolving = itemView.findViewById(R.id.et_problemsolving);
            btn_savechanges = itemView.findViewById(R.id.btn_savechanges);
            btn_addfile = itemView.findViewById(R.id.btn_uploadimage);
            iv_image = itemView.findViewById(R.id.iv_image);


        }


    }
}
