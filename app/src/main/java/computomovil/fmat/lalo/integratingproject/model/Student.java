package computomovil.fmat.lalo.integratingproject.model;

import java.io.Serializable;

/**
 * Created by lalo
 * Date: 4/02/16
 * Project: Database sqlite
 */
public class Student implements Serializable {

    private String matricula, nombre, apellido;

    public Student() {
        matricula = " ";
        nombre = " ";
        apellido = " ";
    }

    public Student(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (matricula != null ? !matricula.equals(student.matricula) : student.matricula != null)
            return false;
        if (nombre != null ? !nombre.equals(student.nombre) : student.nombre != null) return false;
        return !(apellido != null ? !apellido.equals(student.apellido) : student.apellido != null);

    }

    @Override
    public int hashCode() {
        int result = matricula != null ? matricula.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellido != null ? apellido.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
