package app.jugadfunda.login.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.login.pojo.NewsPojo;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{
    private Context mContext;
    private ArrayList<NewsPojo> mNewsList;

    public NewsAdapter(Context mContext, ArrayList<NewsPojo> mNewsList){
        this.mContext = mContext;
        this.mNewsList = mNewsList;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        NewsPojo mNewsPojo = mNewsList.get(position);
        holder.mTvTitle.setText(mNewsPojo.getTitle());
        holder.mTvDate.setText(mNewsPojo.getDate());
        holder.mTvDescription.setText(mNewsPojo.getDescription());

        if(mNewsPojo.getEncoded() != null){
            byte[] decodedString = Base64.decode(mNewsPojo.getEncoded(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.mImgNews.setImageBitmap(decodedByte);
        }else{
            holder.mImgNews.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder{
        private TextView mTvTitle;
        private TextView mTvDate;
        private TextView mTvDescription;
        private ImageView mImgNews;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mTvDate = itemView.findViewById(R.id.tv_date);
            mTvDescription = itemView.findViewById(R.id.tv_description);
            mImgNews = itemView.findViewById(R.id.iv_news);
        }
    }
}
