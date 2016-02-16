package computomovil.fmat.lalo.integratingproject.database.student;

import android.provider.BaseColumns;

/**
 * Created by lalo
 * Date: 4/02/16
 * Project: Database sqlite
 */
class StudentContract implements BaseColumns {
    public static final String TABLE_NAME = "Alumnos";
    public static final String COLUMN_NAME_MATRICULA = "MAtricula";
    public static final String COLUMN_NAME_NOMBRE = "nombre";
    public static final String COLUMN_NAME_APELLIDO = "apellido";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ALUMNOS =
            "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_MATRICULA + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_NOMBRE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_APELLIDO + TEXT_TYPE +
                    " )";

    public static final String SQL_DELETE_ALUMNOS =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
