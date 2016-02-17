package computomovil.fmat.lalo.integratingproject.database.general;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lalo
 * Date: 4/02/16
 * Project: Database sqlite
 */
class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "proyecto_integrador.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Usuario", UserContract.SQL_CREATE_USUARIOS);
        Log.d("Alumnos", StudentContract.SQL_CREATE_ALUMNOS);
        db.execSQL(UserContract.SQL_CREATE_USUARIOS);
        db.execSQL(StudentContract.SQL_CREATE_ALUMNOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UserContract.SQL_DELETE_USUARIOS);
        db.execSQL(StudentContract.SQL_DELETE_ALUMNOS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, newVersion, oldVersion);
    }
}
