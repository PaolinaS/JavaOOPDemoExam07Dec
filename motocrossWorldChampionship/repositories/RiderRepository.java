package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.*;

public class RiderRepository implements Repository<Rider> {
    private List<Rider> models;

    public RiderRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Rider getByName(String name) {
        Rider rider = null;
        for (Rider model : models) {
            if (model.getName().equals(name)) {
                rider = model;
            }
        }
        return rider;
    }

    @Override
    public Collection<Rider> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Rider rider) {
        if (!this.models.contains(rider)) {
            this.models.add(rider);
        }
    }

    @Override
    public boolean remove(Rider model) {
        return this.models.remove(model);
    }
}
