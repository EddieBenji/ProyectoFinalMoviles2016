package computomovil.fmat.lalo.integratingproject;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;

import computomovil.fmat.lalo.integratingproject.database.general.PondDataSource;
import computomovil.fmat.lalo.integratingproject.database.general.StudentDataSource;
import computomovil.fmat.lalo.integratingproject.model.Pond;
import computomovil.fmat.lalo.integratingproject.model.Student;
import computomovil.fmat.lalo.integratingproject.services.PondService;
import computomovil.fmat.lalo.integratingproject.services.StudentService;

public class StudentList extends ListActivity {
    private StudentService stdService;
    private StudentDataSource alumnoDS;

    private PondService pondService;
    private PondDataSource pondDS;

    private void setComponentsForWorking() {
        /*
        try {
            alumnoDS.open();
            stdService.setStudents(alumnoDS.getAllAlumnos());
            alumnoDS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                stdService.getAllMatricesRegistered()
        );
        */

        try {
            pondDS.open();
            pondService.setPonds(pondDS.getAllPonds());
            pondDS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                pondService.getAllNameRegistered()
        );

        this.setListAdapter(adapter);
    }

    public void addPoundsToDB(){
        try {
            pondDS.open();

            Pond pond = new Pond("a", "asd", -20, 20);
            pondDS.insertPond(pond);
            pondDS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        //stdService = new StudentService();
        //alumnoDS = new StudentDataSource(getApplicationContext());

        pondService = new PondService();
        pondDS = new PondDataSource(getApplicationContext());
        try {
            /*
            alumnoDS.open();
            alumnoDS.close();
            */
            pondDS.open();
            pondDS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //addPoundsToDB();
        setComponentsForWorking();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setComponentsForWorking();
    }

    public void destroySession(View v) {
        SharedPreferences.Editor ed = getSharedPreferences("login",Context.MODE_PRIVATE).edit();
        ed.clear();
        ed.apply();
        this.finish();
    }

    public void addStudent(View v) {
        Student std = new Student();
        Intent intent = new Intent(getApplicationContext(), FormStudent.class);
        intent.putExtra("student", std);
        intent.putExtra("adding", 1);
        startActivity(intent);
    }

    public void addPond(View v){
        Pond pond = new Pond();
        Intent intent = new Intent(getApplicationContext(), FormPond.class);
        intent.putExtra("pond", pond);
        intent.putExtra("adding", 1);
        startActivity(intent);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        /*
        String item = (String) getListAdapter().getItem(position);
        Student studentSelected = stdService.getByMatrix(item);
        if (studentSelected != null) {
            System.out.println("Estudiante:");
            System.out.println(studentSelected);
            Intent intent = new Intent(getApplicationContext(), FormStudent.class);
            intent.putExtra("student", studentSelected);
            intent.putExtra("adding", 0);
            startActivity(intent);
        }
        */

        String name = (String) getListAdapter().getItem(position);
        Pond pondSelected = pondService.getByName(name);
        if ( pondSelected != null ) {
            Intent intent = new Intent(getApplicationContext(), FormPond.class);
            intent.putExtra("pond", pondSelected);
            intent.putExtra("adding", 0);
            startActivity(intent);
        }

    }

    public void verMapa(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
