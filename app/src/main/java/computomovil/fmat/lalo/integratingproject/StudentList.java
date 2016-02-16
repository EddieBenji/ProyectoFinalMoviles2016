package computomovil.fmat.lalo.integratingproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import computomovil.fmat.lalo.integratingproject.database.student.StudentDataSource;
import computomovil.fmat.lalo.integratingproject.services.StudentService;

public class StudentList extends AppCompatActivity {
    private StudentService stdService;
    private StudentDataSource alumnoDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
    }
}
