package itangqi.me.mygreendao.db.factory;

import android.content.Context;

import itangqi.me.mygreendao.db.util.DBOperation;

public class DataFactory {

    private DBOperation dbOperation;

    private DataFactory() {

    }

    private static class CommonFactoryHodler {
        static final DataFactory INSTANCE = new DataFactory();
    }

    public static DataFactory getInstance() {
        return CommonFactoryHodler.INSTANCE;
    }

    public DBOperation getDbOperation(Context context) {
        if (dbOperation == null) {
            dbOperation = new DBOperation(context);
        }
        return dbOperation;
    }
}
