package app.jugaadfunda.learningcompanion.home.funcorner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.apiresponse.CountResponse;
import app.jugaadfunda.learningcompanion.apiresponse.FunCornerResponse;
import app.jugaadfunda.learningcompanion.track.ZoomImageActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartoonCornerFragment extends Fragment implements CartoonCornerInterfaceView, View.OnClickListener {
    private ImageView iv_cartoon;
    private TextView tv_happy;
    private TextView tv_satisfied;
    private TextView tv_unhappy;
    private TextView tv_sad;
    private TextView tv_cartoontext;
    private ImplCartoonCornerPresenter mImplCartoonCornerPresenter;
    private FunCornerResponse countResponse;
    private long funId = 0;
    private long uid = 0;
    private LinearLayout mLinearNodata;
    private TextView mTVNodata;
    private LinearLayout mLinearCartoon;
    private ImageView mIVAdvertisement;
    private ImageView mIVzoomout;
    private CardView cv_adverstisement;
    private byte[] mAdvertisementImage = null;
    private View itemView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView= inflater.inflate(R.layout.fragment_cartoon_corner, container, false);

        //setting UI
        setUI(itemView);

        itemView.findViewById(R.id.iv_zoom_image).setOnClickListener(v ->
                zoom(itemView.findViewById(R.id.iv_advertisment)));
        return itemView;
    }

    void setUI(View itemView){
        iv_cartoon = itemView.findViewById(R.id.iv_cc);
        tv_happy = itemView.findViewById(R.id.tv_happycount);
        tv_satisfied = itemView.findViewById(R.id.tv_satisfied);
        tv_unhappy = itemView.findViewById(R.id.tv_unhappy);
        tv_sad = itemView.findViewById(R.id.tv_sad);
        tv_cartoontext = itemView.findViewById(R.id.tv_ctext);
        mLinearNodata = itemView.findViewById(R.id.linear_nodata);
        mTVNodata = itemView.findViewById(R.id.tv_nodata);
        mLinearCartoon = itemView.findViewById(R.id.linear_funcorner);
        mIVAdvertisement = itemView.findViewById(R.id.iv_advertisment);
        mIVzoomout = itemView.findViewById(R.id.iv_zoom_image);
        cv_adverstisement = itemView.findViewById(R.id.card2);

        //calling Lsiteners
        setListners(itemView);

        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        uid = sh.getLong("uid", 0);

        mImplCartoonCornerPresenter = new ImplCartoonCornerPresenter(getContext(),this);
        mImplCartoonCornerPresenter.wsFunCorner(uid);
        mImplCartoonCornerPresenter.wsAdvertisement();
    }

    void setListners(View view){
        view.findViewById(R.id.iv_happy).setOnClickListener(this);
        view.findViewById(R.id.iv_satisfied).setOnClickListener(this);
        view.findViewById(R.id.iv_unhappy).setOnClickListener(this);
        view.findViewById(R.id.iv_sad).setOnClickListener(this);
        view.findViewById(R.id.iv_zoom_image).setOnClickListener(this);
    }
    private void zoom(View view) {
        Intent intents = new Intent(getContext(), ZoomImageActivity.class);
        intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intents.putExtra("img",mAdvertisementImage);
        startActivity(intents);
    }

    @Override
    public void populateData(FunCornerResponse mFunCornerResponse) {
        countResponse = mFunCornerResponse;
        funId = mFunCornerResponse.getFunid();
        tv_happy.setText(""+countResponse.getEmojicount().getHappycount());
        tv_satisfied.setText(""+countResponse.getEmojicount().getSatisfiedcount());
        tv_unhappy.setText(""+countResponse.getEmojicount().getUnhappycount());
        tv_sad.setText(""+countResponse.getEmojicount().getSadcount());
        tv_cartoontext.setText(mFunCornerResponse.getDescription());


        byte[] image = mFunCornerResponse.getCartoon();
        if(image != null){
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            iv_cartoon.setImageBitmap(bmp);
            mLinearCartoon.setVisibility(View.VISIBLE);
        }
        checkEnableAndDisable(countResponse.getPemoji());
    }

    @Override
    public void updateCount(int check) {
        switch (countResponse.getPemoji()){
            case 1:
                countResponse.getEmojicount().setHappycount(countResponse.getEmojicount().getHappycount() - 1);
                break;
            case 2:
                countResponse.getEmojicount().setSatisfiedcount(countResponse.getEmojicount().getSatisfiedcount() - 1);
                break;
            case 3:
                countResponse.getEmojicount().setUnhappycount(countResponse.getEmojicount().getUnhappycount() - 1);
                break;
            case 4:
                countResponse.getEmojicount().setSadcount(countResponse.getEmojicount().getSadcount() - 1);
                break;
        }

            switch (check){
                case 1:
                    countResponse.getEmojicount().setHappycount(countResponse.getEmojicount().getHappycount() + 1);
                    break;
                case 2:
                    countResponse.getEmojicount().setSatisfiedcount(countResponse.getEmojicount().getSatisfiedcount() + 1);
                    break;
                case 3:
                    countResponse.getEmojicount().setUnhappycount(countResponse.getEmojicount().getUnhappycount() + 1);
                    break;
                case 4:
                    countResponse.getEmojicount().setSadcount(countResponse.getEmojicount().getSadcount() + 1);
                    break;
        }

        countResponse.setPemoji(check);
        checkEnableAndDisable(check);

        countResponse.getEmojicount().setHappycount(countResponse.getEmojicount().getHappycount());
        countResponse.getEmojicount().setSatisfiedcount(countResponse.getEmojicount().getSatisfiedcount());
        countResponse.getEmojicount().setUnhappycount(countResponse.getEmojicount().getUnhappycount());
        countResponse.getEmojicount().setSadcount(countResponse.getEmojicount().getSadcount());

        tv_happy.setText(""+countResponse.getEmojicount().getHappycount());
        tv_satisfied.setText(""+countResponse.getEmojicount().getSatisfiedcount());
        tv_unhappy.setText(""+countResponse.getEmojicount().getUnhappycount());
        tv_sad.setText(""+countResponse.getEmojicount().getSadcount());
    }

    @Override
    public void checkforNodata() {
        mLinearCartoon.setVisibility(View.GONE);
        mLinearNodata.setVisibility(View.VISIBLE);
        mTVNodata.setText("No Cartoon Found.");

    }

    @Override
    public void setAdvertisement(byte[] image) {
        mAdvertisementImage = image;
        if(mAdvertisementImage != null){
            Bitmap bmp = BitmapFactory.decodeByteArray(mAdvertisementImage, 0, mAdvertisementImage.length);
            mIVAdvertisement.setImageBitmap(bmp);
            cv_adverstisement.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_happy:
                checkEnableAndDisable(1);
                mImplCartoonCornerPresenter.wsAddEmoji(funId, 1, uid);
                break;
            case R.id.iv_satisfied:
                checkEnableAndDisable(2);
                mImplCartoonCornerPresenter.wsAddEmoji(funId, 2, uid);
                break;
            case R.id.iv_unhappy:
                checkEnableAndDisable(3);
                mImplCartoonCornerPresenter.wsAddEmoji(funId, 3, uid);
                break;
            case R.id.iv_sad:
                checkEnableAndDisable(4);
                mImplCartoonCornerPresenter.wsAddEmoji(funId, 4, uid);
                break;
            case R.id.iv_zoom_image:
               zoom(v);
                break;
        }
    }

    void checkEnableAndDisable(int check){
        switch (check){
            case 1:
                itemView.findViewById(R.id.iv_happy).setClickable(false);
                itemView.findViewById(R.id.iv_satisfied).setClickable(true);
                itemView.findViewById(R.id.iv_unhappy).setClickable(true);
                itemView.findViewById(R.id.iv_sad).setClickable(true);
                break;

            case 2:
                itemView.findViewById(R.id.iv_happy).setClickable(true);
                itemView.findViewById(R.id.iv_satisfied).setClickable(false);
                itemView.findViewById(R.id.iv_unhappy).setClickable(true);
                itemView.findViewById(R.id.iv_sad).setClickable(true);
                break;

            case 3:
                itemView.findViewById(R.id.iv_happy).setClickable(true);
                itemView.findViewById(R.id.iv_satisfied).setClickable(true);
                itemView.findViewById(R.id.iv_unhappy).setClickable(false);
                itemView.findViewById(R.id.iv_sad).setClickable(true);
                break;

            case 4:
                itemView.findViewById(R.id.iv_happy).setClickable(true);
                itemView.findViewById(R.id.iv_satisfied).setClickable(true);
                itemView.findViewById(R.id.iv_unhappy).setClickable(true);
                itemView.findViewById(R.id.iv_sad).setClickable(false);
                break;

        }
    }
}
