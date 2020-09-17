package app.jugaadfunda.learningcompanion.businessactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.localstorage.JfStorage;

public class CanvasTemplateActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewKeyPatner, textViewKeyActivity, textViewKeyResources, textViewValueProposition,textViewCustomerRel;
    private TextView textViewChannels, textViewCustomerSegment, textViewCost, textViewRevenue;
    private File myFile;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_template);

        setUI();
    }

    void setUI(){
        textViewKeyPatner = findViewById(R.id.tvKeyPartner);
        textViewKeyActivity = findViewById(R.id.tvKeyActivity);
        textViewKeyResources = findViewById(R.id.tvKeyResource);

        textViewValueProposition =findViewById(R.id.tvValueProposition);
        textViewCustomerRel =  findViewById(R.id.tvCustomerRel);
        textViewChannels =  findViewById(R.id.tvChannel);

        textViewCustomerSegment =  findViewById(R.id.tvCustomerSeg);
        textViewCost = findViewById(R.id.tvCost);
        textViewRevenue = findViewById(R.id.tvRevenue);
        linearLayout = findViewById(R.id.bussLinear);

        setListeners();

        if (shouldAskPermissions()) {

            askPermissions();
        }
        SharedPreferences sh = getSharedPreferences("profile", Context.MODE_PRIVATE);
        long mUserId = sh.getLong("uid",0);

        JfStorage jfStorage = new JfStorage(this);
        ArrayList<BusinessActivityPojo> mList = jfStorage.getAllBusinessCanvas(mUserId);
        if(mList != null || !mList.isEmpty()){
            textViewKeyPatner.setText(mList.get(6).getCanvastext());
            textViewKeyActivity.setText(mList.get(4).getCanvastext());
            textViewKeyResources.setText(mList.get(5).getCanvastext());
            textViewValueProposition.setText(mList.get(1).getCanvastext());
            textViewCustomerRel.setText(mList.get(2).getCanvastext());
            textViewChannels.setText(mList.get(3).getCanvastext());
            textViewCustomerSegment.setText(mList.get(0).getCanvastext());
            textViewRevenue.setText(mList.get(8).getCanvastext());
            textViewCost.setText(mList.get(7).getCanvastext());
        }else {
            Toast.makeText(this,"No Data Available for Business Canvas Activity", Toast.LENGTH_LONG).show();
        }

    }

    void setListeners(){
        findViewById(R.id.iv_download).setOnClickListener(this);
    }
    protected boolean shouldAskPermissions() {

        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }


    @TargetApi(23)
    protected void askPermissions() {

        String[] permissions = {

                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };

        int requestCode = 200;
        requestPermissions(permissions, requestCode);

    }

    public Bitmap viewToBitmap(View view) {

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;

    }

    void download(){
        Toast.makeText(CanvasTemplateActivity.this, "Download Starts",Toast.LENGTH_SHORT).show();

        Bitmap bitmap = viewToBitmap(linearLayout);

        File pngFolder = new File(Environment.getExternalStorageDirectory(),"Learning Companion");

        if (!pngFolder.exists()) {

            pngFolder.mkdir();
        }

        myFile = new File(pngFolder+"/"+"Business-Model-Canvas.png");

        try {

            FileOutputStream output = new FileOutputStream(myFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.close();

            Toast.makeText(CanvasTemplateActivity.this, "Business Model Canvas Downloaded in Learning Companion folder in your device",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_download:
                download();
                break;
        }
    }
}