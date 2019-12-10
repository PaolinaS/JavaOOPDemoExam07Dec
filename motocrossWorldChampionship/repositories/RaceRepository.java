package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.*;

public class RaceRepository implements Repository<Race> {
    private List<Race> models;

    public RaceRepository() {
        this.models = new ArrayList<>();
    }


    @Override
    public Race getByName(String name) {
        Race race = null;
        for (Race model : models) {
            if(model.getName().equals(name)){
                race = model;
            }
        }
        return race;
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Race race) {
        if (!this.models.contains(race)){
            this.models.add(race);
        }
    }

    @Override
    public boolean remove(Race model) {
        return this.models.remove(model);
    }
}
