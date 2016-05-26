package computomovil.fmat.lalo.integratingproject.services;

import java.util.List;

import computomovil.fmat.lalo.integratingproject.model.Pond;

/**
 * Created by lalo
 * Date: 26/05/16
 * Project: Integrating Project
 */
public class PondService {
    private List<Pond> ponds;

    public List<Pond> getPonds() {
        return ponds;
    }

    public void setPonds(List<Pond> ponds) {
        this.ponds = ponds;
    }

    public String[] getAllNameRegistered() {
        int pondsSizeArray = ponds.size();
        String[] values = new String[pondsSizeArray];
        for (int i = 0; i < pondsSizeArray; i++)
            values[i] = ponds.get(i).getName();

        return values;
    }

    public Pond getByName(String name) {
        for (Pond pond : ponds)
            if (pond.getName().equalsIgnoreCase(name))
                return pond;
        return null;
    }

    public Pond getById(int id) {
        for (Pond pond : ponds)
            if (pond.getId() == id)
                return pond;
        return null;
    }
}
