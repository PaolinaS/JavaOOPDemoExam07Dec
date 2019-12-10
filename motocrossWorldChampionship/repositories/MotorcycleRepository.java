package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.*;

public class MotorcycleRepository implements Repository<Motorcycle> {
    private List<Motorcycle> models;

    public MotorcycleRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Motorcycle getByName(String name) {
        Motorcycle motorcycle = null;
        for (Motorcycle model : models) {
            if (model.getModel().equals(name)) {
                motorcycle = model;
            }
        }
        return motorcycle;
    }

    @Override
    public Collection<Motorcycle> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Motorcycle model) {
        if (!this.models.contains(model)) {
            this.models.add(model);
        }
    }

    @Override
    public boolean remove(Motorcycle motorcycle) {
        return this.models.remove(motorcycle);
    }
}
