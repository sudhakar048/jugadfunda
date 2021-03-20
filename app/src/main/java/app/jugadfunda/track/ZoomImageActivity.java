package app.jugadfunda.track;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import app.jugadfunda.R;

public class ZoomImageActivity extends AppCompatActivity {
    private ImageView mIVAdvertisement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);

        setUI();

    }

    void setUI(){
        mIVAdvertisement = findViewById(R.id.iv_image);
        byte[] image = getIntent().getByteArrayExtra("img");
        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        mIVAdvertisement.setImageBitmap(bmp);

        findViewById(R.id.iv_zoom_out_image).setOnClickListener(view -> onBackPressed());

    }

}
