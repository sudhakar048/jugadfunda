package app.jugaadfunda.learningcompanion.home.captureidea;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.home.adapter.CaptureIdeaAdapter;
import app.jugaadfunda.learningcompanion.home.pojo.CaptureIdeaPojo;
import app.jugaadfunda.learningcompanion.localstorage.JfStorage;
import app.jugaadfunda.learningcompanion.utility.ImageFileFilter;
import app.jugaadfunda.learningcompanion.validate.Validate;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaptureFragment extends Fragment implements CaptureIdeaInterfaceView{
    private JfStorage jfStorage;
    private ArrayList<CaptureIdeaPojo> mList = null;
    private static final int PICK_IMAGE = 100;
    private ViewPager2 viewPager2;
    private  DotsIndicator indicator;
    private CaptureIdeaAdapter captureIdeaAdapter;
    private CaptureIdeaPojo cip = null;
    private int position;
    private AlertDialog.Builder builder;
    private ImplCaptureIdeaPresenter mImplCaptureIdeaPresenter;
    private Uri imageUri;
    private String filePath;
    private long mUserId;
    private MultipartBody.Part fileToUpload;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cature, container, false);
        initializeData();
        setPager(view);

        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        mUserId = sh.getLong("uid",0);
        return view;
    }

    void initializeData(){
        mImplCaptureIdeaPresenter = new ImplCaptureIdeaPresenter(getContext(), this);
        jfStorage = new JfStorage(getContext());

        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        mUserId = sh.getLong("uid",0);

        mList = jfStorage.getAllRecords(mUserId);
        if(mList.isEmpty()){
            resetData(1);
        }
    }


    void resetData(int check){
        CaptureIdeaPojo monday = new CaptureIdeaPojo("Monday","","","","","","","","",null, mUserId);
        CaptureIdeaPojo tuesday = new CaptureIdeaPojo("Tuesday","","","","","","","","",null, mUserId);
        CaptureIdeaPojo wednessday = new CaptureIdeaPojo("Wednessday","","","","","","","","",null, mUserId);
        CaptureIdeaPojo thursday = new CaptureIdeaPojo("Thursday","","","","","","","","",null, mUserId);
        CaptureIdeaPojo friday = new CaptureIdeaPojo("Friday","","","","","","","","",null, mUserId);
        CaptureIdeaPojo saturday = new CaptureIdeaPojo("Saturday","","","","","","","","",null, mUserId);
        CaptureIdeaPojo sunday = new CaptureIdeaPojo("Sunday","","","","","","","","",null, mUserId);
        if(check == 1){
            jfStorage.insertCaptureIdeas(monday);
            jfStorage.insertCaptureIdeas(tuesday);
            jfStorage.insertCaptureIdeas(wednessday);
            jfStorage.insertCaptureIdeas(thursday);
            jfStorage.insertCaptureIdeas(friday);
            jfStorage.insertCaptureIdeas(saturday);
            jfStorage.insertCaptureIdeas(sunday);

            mList.add(monday);
            mList.add(tuesday);
            mList.add(wednessday);
            mList.add(thursday);
            mList.add(friday);
            mList.add(saturday);
            mList.add(sunday);
        }else{
            jfStorage.updateCaptureIdeas(monday);
            jfStorage.updateCaptureIdeas(tuesday);
            jfStorage.updateCaptureIdeas(wednessday);
            jfStorage.updateCaptureIdeas(thursday);
            jfStorage.updateCaptureIdeas(friday);
            jfStorage.updateCaptureIdeas(saturday);
            jfStorage.updateCaptureIdeas(sunday);

            mList.set(0, monday);
            mList.set(1, tuesday);
            mList.set(2, wednessday);
            mList.set(3, thursday);
            mList.set(4, friday);
            mList.set(5, saturday);
            mList.set(6, sunday);
        }

    }

    private void setPager(View view) {
        captureIdeaAdapter = new CaptureIdeaAdapter(getContext(),mList,this);
        viewPager2 = view.findViewById(R.id.pager);
        viewPager2.setAdapter(captureIdeaAdapter);
        indicator = view.findViewById(R.id.dots_indicator);
        indicator.setViewPager2(viewPager2);

        builder = new AlertDialog.Builder(getContext());

    }

    @Override
    public void saveData(CaptureIdeaPojo captureIdeaPojo,int pos) {
        String checkvalidate = validateCaptureIdeaForm(captureIdeaPojo);

        if(!checkvalidate.equals("ok")){
            Toast.makeText(getContext(),""+checkvalidate,Toast.LENGTH_LONG).show();
        }else{
            mList.set(pos,captureIdeaPojo);
            jfStorage.updateCaptureIdeas(captureIdeaPojo);
            captureIdeaAdapter.notifyDataSetChanged();

            initiateAlertDailog(captureIdeaPojo, pos);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void uploadFile(CaptureIdeaPojo captureIdeaPojo,int pos) {
        cip = captureIdeaPojo;
        position = pos;
        if (getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            checkRunTimePermission();
        }else{
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_IMAGE);
        }
    }
    @Override
    public void clearWeekIdea() {
        resetData(2);
        captureIdeaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            try {
                InputStream is = getContext().getContentResolver().openInputStream(imageUri);
                byte[] bytes = getBytes(is);
                cip.setAttachfile(bytes);
                mList.set(position,cip);
                jfStorage.updateCaptureIdeas(cip);
                captureIdeaAdapter.notifyDataSetChanged();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024 * 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    private void initiateAlertDailog(CaptureIdeaPojo cp,int pos) {
        builder.setMessage("Do you want to submit this Idea as a Final Idea of the Week ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    public void onClick(DialogInterface dialog, int id) {
                        if(cp.getAttachfile() != null){
                            File mFile = new File(getRealPathFromURI(imageUri));
                            RequestBody requestBody = RequestBody.create(MediaType.parse(ImageFileFilter.getMimeType(mFile.getName())), mFile);
                            fileToUpload = MultipartBody.Part.createFormData("_img", mFile.getName(), requestBody);
                        }
                        mImplCaptureIdeaPresenter.wsCaptureIdea(cp.getDay(), cp.getIdeatitle(), cp.getDescription(), cp.getUnique(), cp.getBetter(), cp.getFutureproof(), cp.getTriggered(), cp.getFutureproofotherways(), cp.getProblemsolving(), mUserId, fileToUpload, pos);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(getContext(),"you didn't make this Idea as Final Idea.Please do Improvement, finalize your Idea and Submit to Jugaadfunda Team for Further Action.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Idea of the "+cp.getDay());
        alert.show();
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        String result = "";
        if (contentUri != null) {
            CursorLoader loader = new CursorLoader(getContext(), contentUri, proj, null, null, null);
            Cursor cursor = loader.loadInBackground();

            filePath = contentUri.getPath(); // for file_name display purpose
            filePath = filePath.trim();

            if (cursor != null) {
                int column_index = 0; //cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                result = cursor.getString(column_index);
                cursor.close();
            }
        } else {
            Log.e("contentUri", "" + contentUri);
        }
        return result;
    }

    String validateCaptureIdeaForm(CaptureIdeaPojo captureIdeaPojo){
       if(!Pattern.matches(Validate.IDEA_DAY,captureIdeaPojo.getDay()) || captureIdeaPojo.getDay().isEmpty()){
            return "Invalid Day, only uppercase, lowercase,numbers,-,.,_,' and Max 15 characters are allowed";
        }else if(!Pattern.matches(Validate.TITLE_IDEA,captureIdeaPojo.getIdeatitle()) || captureIdeaPojo.getIdeatitle().isEmpty()){
            return "Invalid Idea Title, only uppercase, lowercase,numbers,-,.,_,' and Max 200 characters are allowed";
        }else if(!Pattern.matches(Validate.IDEA_DESCRIPTION,captureIdeaPojo.getDescription()) || captureIdeaPojo.getDescription().isEmpty()){
            return "Invalid Idea Description, only uppercase, lowercase,numbers,-,.,_,' and Max 500 characters are allowed";
        }else if(!Pattern.matches(Validate.IDEA_UNIQUE,captureIdeaPojo.getUnique()) || captureIdeaPojo.getUnique().isEmpty()){
            return "Invalid 'How it is different than what is existing', only uppercase, lowercase,numbers,-,.,_,' and Max 500 characters are allowed";
        }else if(!Pattern.matches(Validate.IDEA_BETTER,captureIdeaPojo.getBetter()) || captureIdeaPojo.getBetter().isEmpty()){
            return "Invalid 'How it is better than what is existing', only uppercase, lowercase,numbers,-,.,_,' and Max 500 characters are allowed";
        }else if(!Pattern.matches(Validate.IDEA_FUTUREPROOF,captureIdeaPojo.getFutureproof()) || captureIdeaPojo.getFutureproof().isEmpty()){
            return "Invalid 'How future proof it is? Are any other ways to improve', only uppercase, lowercase,numbers,-,.,_,' and Max 500 characters are allowed";
        }else if(!Pattern.matches(Validate.IDEA_TRIGGERED,captureIdeaPojo.getTriggered()) || captureIdeaPojo.getTriggered().isEmpty()){
            return "Invalid 'What triggered this idea in your mind?', only uppercase, lowercase,numbers,-,.,_,' and Max 500 characters are allowed";
        }else if(!Pattern.matches(Validate.IDEA_FUTUREPROOFOTHER,captureIdeaPojo.getFutureproofotherways()) || captureIdeaPojo.getFutureproofotherways().isEmpty()){
            return "Invalid 'How future proof it is? Are any other ways to improve', only uppercase, lowercase,numbers,-,.,_,' and Max 500 characters are allowed";
        }else if(!Pattern.matches(Validate.IDEA_PROBLEMSOLVING,captureIdeaPojo.getProblemsolving()) || captureIdeaPojo.getProblemsolving().isEmpty()){
            return "Invalid 'Is it solving a problem? If yes, what problem it is', only uppercase, lowercase,numbers,-,.,_,' and Max 500 characters are allowed";
        }
       return "ok";
    }



    private void checkRunTimePermission() {
        String[] permissionArrays = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionArrays, 11111);
        }
    }
}