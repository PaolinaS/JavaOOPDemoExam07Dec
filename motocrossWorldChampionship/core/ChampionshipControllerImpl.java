package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.entities.PowerMotorcycle;
import motocrossWorldChampionship.entities.RaceImpl;
import motocrossWorldChampionship.entities.RiderImpl;
import motocrossWorldChampionship.entities.SpeedMotorcycle;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChampionshipControllerImpl implements ChampionshipController {
    private Repository<Rider> riderRepository;
    private Repository<Race> raceRepository;
   private Repository<Motorcycle> motorcycleRepository;

    public ChampionshipControllerImpl(Repository<Rider> riderRepository,
                                      Repository<Motorcycle> motorcycleRepository,
                                      Repository<Race> raceRepository) {
        this.riderRepository = riderRepository;
        this.raceRepository = raceRepository;
        this.motorcycleRepository = motorcycleRepository;
    }

    @Override
    public String createRider(String riderName) {
        Rider rider = this.riderRepository.getByName(riderName);
        if (rider == null) {
            rider = new RiderImpl(riderName);
            this.riderRepository.add(rider);
            return String.format("Rider %s is created.", riderName);
        } else {
            throw new IllegalArgumentException(String.format("Rider %s is already created.", riderName));
        }
    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {
        Motorcycle motorcycle = null;
        String result = "";

        switch (type) {
            case "Speed":
                motorcycle = new SpeedMotorcycle(model, horsePower);
                if (motorcycleRepository.getByName(model) != null) {
                    throw new IllegalArgumentException(String.format("Motorcycle %s is already created.", model));
                } else {
                    result = String.format("%s %s is created.", SpeedMotorcycle.class.getSimpleName(), model);
                }
                break;
            case "Power":
                motorcycle = new PowerMotorcycle(model, horsePower);
                if (motorcycleRepository.getByName(model) != null) {
                    throw new IllegalArgumentException(String.format("Motorcycle %s is already created.", model));
                } else {
                    result = String.format("%s %s is created.", PowerMotorcycle.class.getSimpleName(), model);
                }
                break;
        }
        this.motorcycleRepository.add(motorcycle);
        return result;
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {
        Rider rider = this.riderRepository.getByName(riderName);
        if (rider == null) {
            throw new NullPointerException(String.format("Rider %s could not be found.", riderName));
        }
        Motorcycle motorcycle = this.motorcycleRepository.getByName(motorcycleModel);
        if (motorcycle == null) {
            throw new NullPointerException(String.format("Motorcycle %s could not be found.", motorcycleModel));
        }
        rider.addMotorcycle(motorcycle);
        return String.format("Rider %s received motorcycle %s.", riderName, motorcycleModel);
    }

    @Override
    public String addRiderToRace(String raceName, String riderName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new NullPointerException(String.format("Race %s could not be found.", raceName));
        }
        Rider rider = this.riderRepository.getByName(riderName);
        if (rider == null) {
            throw new NullPointerException(String.format("Rider %s could not be found.", riderName));
        }
        race.addRider(rider);
        return String.format("Rider %s added in %s race.", riderName, raceName);
    }


    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new NullPointerException(String.format("Race %s could not be found.", raceName));
        }
        if (race.getRiders().size() < 3) {
            throw new IllegalArgumentException(String.format("Race %s cannot start with less than 3 participants.", raceName));
        }
        int laps = race.getLaps();

        List<Rider> winners = race.getRiders()
                .stream()
                .sorted(Comparator.comparingDouble(r -> -r.getMotorcycle().calculateRacePoints(laps)))
                .limit(3).collect(Collectors.toList());
        winners.get(0).winRace();
        this.raceRepository.remove(race);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Rider %s wins %s race.", winners.get(0).getName(), race.getName()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Rider %s is second in %s race.", winners.get(1).getName(), race.getName()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Rider %s is third in %s race.", winners.get(2).getName(), race.getName()));


        return sb.toString();
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = new RaceImpl(name, laps);
        if (this.raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format("Race %s is already created.", name));
        }
        this.raceRepository.add(race);
        return String.format("Race %s is created.", name);
    }
}
