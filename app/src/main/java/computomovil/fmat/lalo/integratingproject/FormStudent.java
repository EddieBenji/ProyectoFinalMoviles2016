package computomovil.fmat.lalo.integratingproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import computomovil.fmat.lalo.integratingproject.database.general.StudentDataSource;
import computomovil.fmat.lalo.integratingproject.model.Student;

public class FormStudent extends AppCompatActivity {
    private StudentDataSource alumnoDS;
    private Student student;
    private String matrix, name, lastName;

    private boolean adding;

    private void setButton(Button btn, boolean isAvailable) {
        btn.setClickable(isAvailable);
        btn.setEnabled(isAvailable);
    }

    private void setButtons() {
        setButton((Button) findViewById(R.id.btn_delete), !adding);
        setButton((Button) findViewById(R.id.btn_update), !adding);
        setButton((Button) findViewById(R.id.btn_save), adding);
    }

    private void setTexts() {
        ((EditText) this.findViewById(R.id.text_matricula)).setText(student.getMatricula());
        this.findViewById(R.id.text_matricula).setEnabled(adding);
        ((EditText) this.findViewById(R.id.text_name)).setText(student.getNombre());
        ((EditText) this.findViewById(R.id.text_lastName)).setText(student.getApellido());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);
        alumnoDS = new StudentDataSource(getApplicationContext());

        student = (Student) getIntent().getSerializableExtra("student");
        adding = (int) getIntent().getSerializableExtra("adding") == 1;

        this.setTexts();
        this.setButtons();
    }

    private void getFields() {
        matrix = ((EditText) this.findViewById(R.id.text_matricula)).getText().toString();
        name = ((EditText) this.findViewById(R.id.text_name)).getText().toString();
        lastName = ((EditText) this.findViewById(R.id.text_lastName)).getText().toString();
    }

    private boolean emptyFields() {
        return matrix.isEmpty() || name.isEmpty() || lastName.isEmpty();
    }

    private void showMsgForEmptyFields() {
        Toast.makeText(this, "Asegúrate de llenar todos los campos", Toast.LENGTH_LONG).show();
    }

    public void updateStudent(View v) {
        getFields();
        if (!emptyFields()) {
            alumnoDS.updateAlumno(new Student(matrix, name, lastName));
            Toast.makeText(this, "Estudiante actualizado", Toast.LENGTH_LONG).show();
            this.finish();
        } else {
            showMsgForEmptyFields();
        }
    }

    public void saveStudent(View v) {
        getFields();
        if (!emptyFields()) {
            if (!alumnoDS.existsStudent(matrix)) {
                alumnoDS.insertAlumno(new Student(matrix, name, lastName));
                Toast.makeText(this, "Estudiante guardado", Toast.LENGTH_LONG).show();
                this.finish();
            } else {
                Toast.makeText(this, "Esa matrícula, ya está registrada. ¡Intenta otra!",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            showMsgForEmptyFields();
        }
    }

    public void deleteStudent(View v) {
        getFields();
        alumnoDS.deleteAlumno(new Student(matrix, name, lastName));
        Toast.makeText(this, "Estudiante eliminado", Toast.LENGTH_LONG).show();
        this.finish();
    }
}
