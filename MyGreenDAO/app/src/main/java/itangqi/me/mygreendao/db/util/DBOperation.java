package itangqi.me.mygreendao.db.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

import java.util.List;

import de.greenrobot.dao.query.Query;
import itangqi.me.mygreendao.BaseApplication;
import itangqi.me.mygreendao.bean.Note;
import itangqi.me.mygreendao.db.dao.DaoSession;
import itangqi.me.mygreendao.db.dao.NoteDao;

public class DBOperation {
    private static final int FLAG_COMPLETE = -1;
    private static final int FLAG_ERROR = -2;
    private static final String SUCCESS = "success";

    private DaoSession daoSession;
    private SQLiteDatabase db;
    private Context context;

    public DBOperation(Context context) {
        this.context = context;
        daoSession = ((BaseApplication) context.getApplicationContext()).getDaoSession();
        db = ((BaseApplication) context.getApplicationContext()).getDb();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
    public void saveFood(final Note note, CallBackListener listener) {
        final Handler mHandler = getHandler(listener);
        new Thread() {
            @Override
            public void run() {
                try {
                    daoSession.getNoteDao().insert(note);
                    Message msg = Message.obtain();
                    msg.what = FLAG_COMPLETE;
                    msg.obj = SUCCESS;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(FLAG_ERROR);
                }
            }
        }.start();
    }
    public void getFoods(CallBackListener listener) {
        final Handler mHandler = getHandler(listener);
        new Thread() {
            @Override
            public void run() {
                try {
                    List<Note> notes = daoSession.getNoteDao().queryBuilder().build().list();
                    Message msg = Message.obtain();
                    msg.what = FLAG_COMPLETE;
                    msg.obj = notes;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(FLAG_ERROR);
                }
            }
        }.start();
    }
    public void getFoods(final String textString,CallBackListener listener) {
        final Handler mHandler = getHandler(listener);
        new Thread() {
            @Override
            public void run() {
                try {
                    Query query = daoSession.getNoteDao().queryBuilder()
                            .where(NoteDao.Properties.Text.eq(textString))
                            .orderAsc(NoteDao.Properties.Date)
                            .build();
                    List<Note> notes = query.list();
                    Message msg = Message.obtain();
                    msg.what = FLAG_COMPLETE;
                    msg.obj = notes;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(FLAG_ERROR);
                }
            }
        }.start();
    }
    public void deleteFood(final long id,CallBackListener listener) {
        final Handler mHandler = getHandler(listener);
        new Thread() {
            @Override
            public void run() {
                try {
                    daoSession.getNoteDao().deleteByKey(id);
                    Message msg = Message.obtain();
                    msg.what = FLAG_COMPLETE;
                    msg.obj = SUCCESS;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(FLAG_ERROR);
                }
            }
        }.start();
    }
    public void deleteAll(CallBackListener listener) {
        final Handler mHandler = getHandler(listener);
        new Thread() {
            @Override
            public void run() {
                try {
                    daoSession.getNoteDao().deleteAll();
                    Message msg = Message.obtain();
                    msg.what = FLAG_COMPLETE;
                    msg.obj = SUCCESS;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(FLAG_ERROR);
                }
            }
        }.start();
    }
    private Handler getHandler(final CallBackListener listener) {
        Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case FLAG_COMPLETE:
                        listener.onComplete(msg.obj);
                        break;
                    case FLAG_ERROR:
                        listener.onError("数据库出错");
                        break;
                }
            }
        };
        return mHandler;
    }

    public interface CallBackListener {

        void onComplete(Object object);

        void onError(String error);
    }

}
