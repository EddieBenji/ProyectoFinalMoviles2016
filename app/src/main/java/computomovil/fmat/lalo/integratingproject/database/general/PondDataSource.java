package computomovil.fmat.lalo.integratingproject.database.general;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import computomovil.fmat.lalo.integratingproject.model.Pond;

/**
 * Created by lalo
 * Date: 25/05/16
 * Project: Integrating Project
 */
public class PondDataSource {
    private SQLiteDatabase database;
    private DBHelper dBHelper;
    private String[] allColumns = {
            PondContract._ID,
            PondContract.COLUMN_NAME_NAME,
            PondContract.COLUMN_NAME_DESCRIPTION,
            PondContract.COLUMN_NAME_LATITUDE,
            PondContract.COLUMN_NAME_LONGITUDE
    };

    public PondDataSource(Context c) {
        dBHelper = new DBHelper(c);
    }

    public void open() throws SQLException {
        database = dBHelper.getWritableDatabase();
    }

    public void close() {
        dBHelper.close();
    }

    /**
     * Inserts the new pond object. You don't need to make
     * the open() before, it will do it for you.
     *
     * @param pond
     * @return
     * @throws SQLException
     */
    public long insertPond(Pond pond) throws SQLException {
        ContentValues values = new ContentValues();
        values.put(PondContract.COLUMN_NAME_NAME, pond.getName());
        values.put(PondContract.COLUMN_NAME_DESCRIPTION, pond.getDescription());
        values.put(PondContract.COLUMN_NAME_LATITUDE, pond.getLatitude());
        values.put(PondContract.COLUMN_NAME_LONGITUDE, pond.getLongitude());
        this.open();
        long newRowId = database.insert(PondContract.TABLE_NAME, null, values);
        this.close();
        return newRowId;
    }

    /**
     * Returns true if the name of the pond already exists in the db.
     *
     * @param name
     * @return
     * @throws SQLException
     */
    public boolean existsPond(String name) throws SQLException {
        this.open();
        boolean exists = database.rawQuery(PondContract.SQL_SELECT_POND,
                new String[]{name}).getCount() > 0;
        this.close();
        return exists;
    }

    /**
     * It updates the pond object, by setting the name, description, latitude and longitude of the pond.
     * It's necessary that the pond has the ID.
     *
     * @param pond
     * @return
     * @throws SQLException
     */
    public long updatePond(Pond pond) throws SQLException {
        ContentValues values = new ContentValues();
        values.put(PondContract.COLUMN_NAME_NAME, pond.getName());
        values.put(PondContract.COLUMN_NAME_DESCRIPTION, pond.getDescription());
        values.put(PondContract.COLUMN_NAME_LATITUDE, pond.getLatitude());
        values.put(PondContract.COLUMN_NAME_LONGITUDE, pond.getLongitude());
        this.open();
        long newRowId = database.update(
                PondContract.TABLE_NAME,
                values,
                PondContract._ID + "=?",
                new String[]{
                        pond.getIdInString()
                }
        );
        this.close();
        return newRowId;
    }

    /**
     * Deletes the record of the pond by NAME
     *
     * @param pond
     * @return
     * @throws SQLException
     */
    public long deletePond(Pond pond) throws SQLException {
        String whereClause = PondContract.COLUMN_NAME_NAME + "=?";
        String[] whereArgs = new String[]{String.valueOf(pond.getName())};
        this.open();
        long status = database.delete(PondContract.TABLE_NAME, whereClause, whereArgs);
        this.close();
        return status;
    }

    //Returns a list of all the ponds registered in the db.
    public List<Pond> getAllPonds() {
        List<Pond> ponds = new ArrayList<>();
        Cursor cursor = database.query(PondContract.TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ponds.add(cursorToPond(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return ponds;
    }

    private Pond cursorToPond(Cursor cursor) {
        return new Pond(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4));
    }


    /**
     * Returns the pond object from the db, that has the same latitude and longitude
     * that you pass to this method.
     * In case the record isn't found, then you'll receive a pond object with these parameters:
     * id = 0, name = "empty", description="empty", latitude=0.0 and longitude=0.0
     *
     * @param latitude
     * @param longitude
     * @return
     * @throws SQLException
     */
    public Pond getPondByLatitudeAndLongitude(double latitude, double longitude) throws SQLException {
        this.open();
        Pond temp;
        Cursor cursor = database.rawQuery(PondContract.SQL_SELECT_POND_BY_LAT_AND_LONG, new String[]{
                String.valueOf(latitude), String.valueOf(longitude)
        });
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            temp = new Pond(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getDouble(3), cursor.getDouble(3));
        } else {
            temp = new Pond(0, "empty", "empty", 0.0, 0.0);
        }
        this.close();
        return temp;
    }

    /**
     * Returns the pond object from the db, that has the name you pass to this method.
     * In case the record isn't found, then you'll receive a pond object with these parameters:
     * id = 0, name = "empty", description="empty", latitude=0.0 and longitude=0.0
     *
     * @param name
     * @return
     * @throws SQLException
     */
    public Pond getPondByName(String name) throws SQLException {
        this.open();
        Pond temp;
        Cursor cursor = database.rawQuery(PondContract.SQL_SELECT_POND, new String[]{name});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            temp = new Pond(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4));
        } else {
            temp = new Pond(0, "empty", "empty", 0.0, 0.0);
        }
        this.close();
        return temp;
    }
}
