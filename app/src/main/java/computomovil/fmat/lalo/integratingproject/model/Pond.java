package computomovil.fmat.lalo.integratingproject.model;

import java.io.Serializable;

/**
 * Created by lalo
 * Date: 25/05/16
 * Project: Integrating Project
 */
public class Pond implements Serializable {
    private int id;
    private String name, description;
    private double latitude, longitude;

    public Pond() {
        this.id = 0;
        this.name = " ";
        this.description = " ";
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public Pond(int id, String name, String description, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Pond(String name, String description, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public String getIdInString() {
        return String.valueOf(this.id);
    }

    public void setId(int id) {
        this.id = id;
    }

    /*Se necesita para la serialización de objetos, los general el IDE*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pond pond = (Pond) o;

        return id == pond.id &&
                Double.compare(pond.latitude, latitude) == 0 &&
                Double.compare(pond.longitude, longitude) == 0 &&
                (name != null ? name.equals(pond.name) : pond.name == null &&
                        (description != null ? description.equals(pond.description) : pond.description == null));

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
