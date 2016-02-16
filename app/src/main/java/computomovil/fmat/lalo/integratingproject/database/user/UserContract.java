package computomovil.fmat.lalo.integratingproject.database.user;

import android.provider.BaseColumns;

/**
 * Created by lalo
 * Date: 4/02/16
 * Project: Database sqlite
 */
class UserContract implements BaseColumns {
    public static final String TABLE_NAME = "usuarios";
    public static final String COLUMN_NAME_USERNAME = "username";
    public static final String COLUMN_NAME_PASSWORD = "password";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_USUARIOS =
            "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_PASSWORD + TEXT_TYPE +
                    " )";

    public static final String SQL_DELETE_USUARIOS =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
