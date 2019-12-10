package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private Collection<Rider> riders;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.riders = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException(String.format("Name %s cannot be less than 5 symbols.", name));
        }
        this.name = name;
    }

    private void setLaps(int laps) {
        if (laps < 1) {
            throw new IllegalArgumentException("Laps cannot be less than 1.");
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Rider> getRiders() {
        return Collections.unmodifiableCollection(this.riders);
    }

    @Override
    public void addRider(Rider rider) {
        if (rider == null) {
            throw new NullPointerException("Rider cannot be null.");
        }
        if (!rider.getCanParticipate()) {
            throw new IllegalArgumentException(String.format("Rider %s could not participate in race.", rider.getName()));
        }
        if (this.riders.contains(rider)) {
            throw new IllegalArgumentException(String.format("Rider %s is already added in %s race.", rider.getName(), this.name));
        }
        this.riders.add(rider);
    }
}
