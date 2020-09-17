package app.jugaadfunda.learningcompanion.localstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import app.jugaadfunda.learningcompanion.businessactivity.BusinessActivityPojo;
import app.jugaadfunda.learningcompanion.home.pojo.CaptureIdeaPojo;

public class JfStorage extends SQLiteOpenHelper {
    private static final String DB_NAME="jfsupport";
    private static final int DB_VERSION = 1;
    private static final String COLUMN_USERID = "userid";
    private static final String COLUMN_DAYNAME = "dayname";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_UNIQUE = "uniquedata";
    private static final String COLUMN_BETTER = "better";
    private static final String COLUMN_FUTUREPROOF = "futureproof";
    private static final String COLUMN_TRIGGERED = "triggered";
    private static final String COLUMN_FUTUREPROOFOTHER = "futureproofother";
    private static final String COLUMN_PROBLEMSOLVING = "problemsolving";
    private static final String COLUMN_FILE = "imagefile";

    private static final String TABLE_CAPTUREIDEA = "captureidea";

    private static final String CREATE_TABLE_CAPTUREIDEA = "CREATE TABLE "+TABLE_CAPTUREIDEA+"("+COLUMN_DAYNAME+" TEXT," +
            COLUMN_TITLE+" TEXT,"+COLUMN_DESCRIPTION+" TEXT,"+COLUMN_UNIQUE+" TEXT,"+COLUMN_BETTER+" TEXT,"+COLUMN_FUTUREPROOF+" TEXT,"
            +COLUMN_TRIGGERED+" TEXT,"+COLUMN_FUTUREPROOFOTHER+" TEXT,"+COLUMN_PROBLEMSOLVING+" TEXT,"
            +COLUMN_FILE+" blob,"+COLUMN_USERID+" INTEGER)";

    // Business Canvas
    private static final String TABLE_BUSINESSCANVAS = "businesscanvas";
    private static final String COLUMN_CANVASTITLE = "title";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_CANVASTEXT = "titletext";
    private static final String COLUMN_CANVASTITLEINFO = "titleinfo";
    private static final String COLUMN_CANVASINFO = "info";
    private static final String COLUMN_CANVASUTUBELINK = "utubelink";
    private static final String CREATE_TABLE_BUSINESSCANVAS = "CREATE TABLE "+TABLE_BUSINESSCANVAS+"("+COLUMN_CANVASTITLE+" TEXT," +COLUMN_IMAGE+" INTEGER,"+COLUMN_CANVASTEXT+" TEXT," +COLUMN_CANVASTITLEINFO+" INTEGER,"+COLUMN_CANVASINFO+" INTEGER,"+COLUMN_CANVASUTUBELINK+" TEXT,"+COLUMN_USERID+" INTEGER)";

    public JfStorage(Context mContext) {
        super(mContext, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CAPTUREIDEA);
        db.execSQL(CREATE_TABLE_BUSINESSCANVAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS quesoptions");
        onCreate(db);
    }

    public void insertCaptureIdeas(CaptureIdeaPojo cip){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DAYNAME,cip.getDay());
        cv.put(COLUMN_TITLE,cip.getIdeatitle());
        cv.put(COLUMN_DESCRIPTION,cip.getDescription());
        cv.put(COLUMN_UNIQUE,cip.getUnique());
        cv.put(COLUMN_BETTER,cip.getBetter());
        cv.put(COLUMN_FUTUREPROOF,cip.getFutureproof());
        cv.put(COLUMN_TRIGGERED,cip.getTriggered());
        cv.put(COLUMN_FUTUREPROOFOTHER,cip.getFutureproofotherways());
        cv.put(COLUMN_PROBLEMSOLVING,cip.getProblemsolving());
        cv.put(COLUMN_FILE,cip.getAttachfile());
        cv.put(COLUMN_USERID, cip.getUserid());

        db.insert(TABLE_CAPTUREIDEA, null, cv);
    }

    public void updateCaptureIdeas(CaptureIdeaPojo cip){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE,cip.getIdeatitle());
        cv.put(COLUMN_DESCRIPTION,cip.getDescription());
        cv.put(COLUMN_UNIQUE,cip.getUnique());
        cv.put(COLUMN_BETTER,cip.getBetter());
        cv.put(COLUMN_FUTUREPROOF,cip.getFutureproof());
        cv.put(COLUMN_TRIGGERED,cip.getTriggered());
        cv.put(COLUMN_FUTUREPROOFOTHER,cip.getFutureproofotherways());
        cv.put(COLUMN_PROBLEMSOLVING,cip.getProblemsolving());
        cv.put(COLUMN_FILE,cip.getAttachfile());

        db.update(TABLE_CAPTUREIDEA, cv, COLUMN_DAYNAME+"= ? and "+COLUMN_USERID+"= ?", new String[]{cip.getDay(), String.valueOf(cip.getUserid())});
    }

    public void insertBusinessCanvas(BusinessActivityPojo bas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CANVASTITLE,bas.getCanvastitle());
        cv.put(COLUMN_IMAGE ,bas.getCanvasimage());
        cv.put(COLUMN_CANVASTEXT ,bas.getCanvastext());
        cv.put(COLUMN_CANVASTITLEINFO ,bas.getTitle_info());
        cv.put(COLUMN_CANVASINFO ,bas.getInfo());
        cv.put(COLUMN_CANVASUTUBELINK ,bas.getUtubelink());
        cv.put(COLUMN_USERID ,bas.getUserid());

        db.insert(TABLE_BUSINESSCANVAS, null, cv);
    }

    public void updateBusinessCanvas(BusinessActivityPojo bas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CANVASTITLE,bas.getCanvastitle());
        cv.put(COLUMN_IMAGE ,bas.getCanvasimage());
        cv.put(COLUMN_CANVASTEXT ,bas.getCanvastext());
        cv.put(COLUMN_CANVASTITLEINFO ,bas.getTitle_info());
        cv.put(COLUMN_CANVASINFO ,bas.getInfo());
        cv.put(COLUMN_CANVASUTUBELINK ,bas.getUtubelink());

        db.update(TABLE_BUSINESSCANVAS, cv, COLUMN_CANVASTITLE+"= ? and "+COLUMN_USERID+"= ?", new String[]{bas.getCanvastitle(), String.valueOf(bas.getUserid())});
    }

    public ArrayList<BusinessActivityPojo> getAllBusinessCanvas(long userId){
        ArrayList<BusinessActivityPojo> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_BUSINESSCANVAS+" where "+COLUMN_USERID+"="+userId  , null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            list.add(new BusinessActivityPojo(res.getInt(res.getColumnIndex(COLUMN_IMAGE)), res.getString(res.getColumnIndex(COLUMN_CANVASTITLE)), res.getString(res.getColumnIndex(COLUMN_CANVASTEXT)),res.getInt(res.getColumnIndex(COLUMN_CANVASTITLEINFO)),res.getInt(res.getColumnIndex(COLUMN_CANVASINFO)),res.getString(res.getColumnIndex(COLUMN_CANVASUTUBELINK)), res.getLong(res.getColumnIndex(COLUMN_USERID))));
            res.moveToNext();
        }
        return list;
    }

    public ArrayList<CaptureIdeaPojo> getAllRecords(long userId){
        ArrayList<CaptureIdeaPojo> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_CAPTUREIDEA+" where "+COLUMN_USERID+"="+userId, null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            list.add(new CaptureIdeaPojo(res.getString(res.getColumnIndex(COLUMN_DAYNAME)),res.getString(res.getColumnIndex(COLUMN_TITLE)),res.getString(res.getColumnIndex(COLUMN_DESCRIPTION)),res.getString(res.getColumnIndex(COLUMN_UNIQUE)),res.getString(res.getColumnIndex(COLUMN_BETTER)),res.getString(res.getColumnIndex(COLUMN_FUTUREPROOF)),res.getString(res.getColumnIndex(COLUMN_TRIGGERED)),res.getString(res.getColumnIndex(COLUMN_FUTUREPROOFOTHER)),res.getString(res.getColumnIndex(COLUMN_PROBLEMSOLVING)),res.getBlob(res.getColumnIndex(COLUMN_FILE)), res.getLong(res.getColumnIndex(COLUMN_USERID))));
            res.moveToNext();
        }
        return list;
    }
}
