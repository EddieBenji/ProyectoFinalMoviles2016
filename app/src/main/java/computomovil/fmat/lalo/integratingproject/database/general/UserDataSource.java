package computomovil.fmat.lalo.integratingproject.database.general;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import computomovil.fmat.lalo.integratingproject.model.User;

/**
 * Created by lalo
 * Date: 4/02/16
 * Project: Database sqlite
 */
public class UserDataSource {
    private SQLiteDatabase database;
    private DBHelper DBHelper;
    private String[] allColumns = {
            UserContract.COLUMN_NAME_USERNAME,
            UserContract.COLUMN_NAME_PASSWORD,
    };

    public UserDataSource(Context context) {
        DBHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = DBHelper.getWritableDatabase();
    }

    public void close() {
        DBHelper.close();
    }


    /**
     * Function oriented to insert a new Alumno into DataBase
     *
     * @param userToInsert the student to be inserted in the db
     * @return row ID of the newly Alumno inserted row, or -1
     */
    public long insertUser(User userToInsert) {
        try {
            this.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(UserContract.COLUMN_NAME_USERNAME, userToInsert.getUsername());
        values.put(UserContract.COLUMN_NAME_PASSWORD, userToInsert.getPassword());
        long newRowId;
        newRowId = database.insert(UserContract.TABLE_NAME, null, values);
        this.close();
        return newRowId;
    }

    private User cursorToUser(Cursor cursor) {
        return new User(cursor.getString(0), cursor.getString(1));
    }

    public User getUserByUsername(String username) {
        try {
            this.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User temp;
        Cursor cursor = database.rawQuery(UserContract.SQL_SELECT_USER, new String[]{username});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            temp = new User(cursor.getString(cursor.getColumnIndex(UserContract.COLUMN_NAME_USERNAME)),
                    cursor.getString(cursor.getColumnIndex(UserContract.COLUMN_NAME_PASSWORD)));
        } else {
            temp = new User("empty", "empty");
        }
        this.close();
        return temp;
    }
}
