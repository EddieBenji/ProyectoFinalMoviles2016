package computomovil.fmat.lalo.integratingproject.database.general;

import android.provider.BaseColumns;

/**
 * Created by lalo
 * Date: 25/05/16
 * Project: Integrating Project
 */
public class PondContract implements BaseColumns {
    public static final String TABLE_NAME = "ponds";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_LATITUDE = "latitude";
    public static final String COLUMN_NAME_LONGITUDE = "longitude";

    private static final String TEXT_TYPE = " TEXT";
    private static final String DOUBLE_TYPE = " DOUBLE";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_PONDS =
            "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_LATITUDE + DOUBLE_TYPE + COMMA_SEP +
                    COLUMN_NAME_LONGITUDE + DOUBLE_TYPE +
                    ")";

    public static final String SQL_DELETE_PONDS =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String SQL_SELECT_POND =
            "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_NAME + "=?";

    public static final String SQL_SELECT_POND_BY_LAT_AND_LONG =
            "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_LATITUDE + "=? and "
                    + COLUMN_NAME_LONGITUDE + "=?";


}
