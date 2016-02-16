package computomovil.fmat.lalo.integratingproject.database.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import computomovil.fmat.lalo.integratingproject.model.Student;

/**
 * Created by lalo
 * Date: 4/02/16
 * Project: Database sqlite
 */
public class StudentDataSource {
    private SQLiteDatabase database;
    private StudentDBHelper alumnoDBHelper;
    private String[] allColumns = {StudentContract.COLUMN_NAME_MATRICULA,
            StudentContract.COLUMN_NAME_NOMBRE,
            StudentContract.COLUMN_NAME_APELLIDO};

    public StudentDataSource(Context context) {
        alumnoDBHelper = new StudentDBHelper(context);
    }

    public void open() throws SQLException {
        database = alumnoDBHelper.getWritableDatabase();
    }

    public void close() {
        alumnoDBHelper.close();
    }


    /**
     * Function oriented to insert a new Alumno into DataBase
     *
     * @param stdToInsert the student to be inserted in the db
     * @return row ID of the newly Alumno inserted row, or -1
     */
    public long insertAlumno(Student stdToInsert) {
        ContentValues values = new ContentValues();
        values.put(StudentContract.COLUMN_NAME_MATRICULA, stdToInsert.getMatricula());
        values.put(StudentContract.COLUMN_NAME_NOMBRE, stdToInsert.getNombre());
        values.put(StudentContract.COLUMN_NAME_APELLIDO, stdToInsert.getApellido());
        long newRowId;
        newRowId = database.insert(StudentContract.TABLE_NAME, null, values);
        return newRowId;
    }

    public long updateAlumno(Student stdToInsert) {
        ContentValues values = new ContentValues();
        values.put(StudentContract.COLUMN_NAME_MATRICULA, stdToInsert.getMatricula());
        values.put(StudentContract.COLUMN_NAME_NOMBRE, stdToInsert.getNombre());
        values.put(StudentContract.COLUMN_NAME_APELLIDO, stdToInsert.getApellido());
        long newRowId;
        newRowId = database.update(StudentContract.TABLE_NAME, values,
                StudentContract.COLUMN_NAME_MATRICULA + "=?",
                new String[]{stdToInsert.getMatricula()});

        return newRowId;
    }

    public long deleteAlumno(Student stdToDelete) {
        String whereClause = StudentContract.COLUMN_NAME_MATRICULA + "=?";
        String[] whereArgs = new String[]{String.valueOf(stdToDelete.getMatricula())};
        return database.delete(StudentContract.TABLE_NAME, whereClause, whereArgs);
    }

    /**
     * Function oriented to recovery all Alumno's rows from Database
     *
     * @return List of Alumnos from Database
     */
    public List<Student> getAllAlumnos() {
        List<Student> alumnos = new ArrayList<>();
        Student alumno;
        Cursor cursor = database.query(StudentContract.TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            alumno = cursorToAlumno(cursor);
            alumnos.add(alumno);
            cursor.moveToNext();
        }
        cursor.close();
        return alumnos;
    }

    private Student cursorToAlumno(Cursor cursor) {
        return new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2));
    }

}
