package computomovil.fmat.lalo.integratingproject.services;

import java.util.List;

import computomovil.fmat.lalo.integratingproject.model.Student;

/**
 * Created by lalo
 * Date: 15/02/16
 * Project: Database sqlite
 */
public class StudentService {
    private List<Student> students;


    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String[] getAllMatricesRegistered() {
        String[] values = new String[students.size()];
        for (int i = 0; i < students.size(); i++)
            values[i] = students.get(i).getMatricula();

        return values;
    }

    public Student getByMatrix(String matrix) {
        for (Student student : students)
            if (student.getMatricula().equalsIgnoreCase(matrix))
                return student;
        return null;
    }
}
