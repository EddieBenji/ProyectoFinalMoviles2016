package computomovil.fmat.lalo.integratingproject.database.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import computomovil.fmat.lalo.integratingproject.model.User;

/**
 * Created by lalo
 * Date: 4/02/16
 * Project: Database sqlite
 */
public class UserDataSource {
    private SQLiteDatabase database;
    private UserDBHelper userDBHelper;
    private String[] allColumns = {
            UserContract.COLUMN_NAME_USERNAME,
            UserContract.COLUMN_NAME_PASSWORD,
    };

    public UserDataSource(Context context) {
        userDBHelper = new UserDBHelper(context);
    }

    public void open() throws SQLException {
        database = userDBHelper.getWritableDatabase();
    }

    public void close() {
        userDBHelper.close();
    }


    /**
     * Function oriented to insert a new Alumno into DataBase
     *
     * @param userToInsert the student to be inserted in the db
     * @return row ID of the newly Alumno inserted row, or -1
     */
    public long insertUser(User userToInsert) {
        ContentValues values = new ContentValues();
        values.put(UserContract.COLUMN_NAME_USERNAME, userToInsert.getUsername());
        values.put(UserContract.COLUMN_NAME_PASSWORD, userToInsert.getPassword());
        long newRowId;
        newRowId = database.insert(UserContract.TABLE_NAME, null, values);
        return newRowId;
    }

    /**
     * Function oriented to recovery all Alumno's rows from Database
     *
     * @return List of Alumnos from Database
     */
    public List<User> getAllUsers() {
        List<User> usuarios = new ArrayList<>();
        User usuario;
        Cursor cursor = database.query(UserContract.TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            usuario = cursorToUser(cursor);
            usuarios.add(usuario);
            cursor.moveToNext();
        }
        cursor.close();
        return usuarios;
    }

    private User cursorToUser(Cursor cursor) {
        return new User(cursor.getString(0), cursor.getString(1));
    }

    public User getUserByUsername(String username){
        Cursor cursor = database.rawQuery(UserContract.SQL_SELECT_USER, new String[]{username});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return new User(cursor.getString(cursor.getColumnIndex(UserContract.COLUMN_NAME_USERNAME)),
                    cursor.getString(cursor.getColumnIndex(UserContract.COLUMN_NAME_PASSWORD)));
        }
        return new User("empty", "empty");
    }
}
