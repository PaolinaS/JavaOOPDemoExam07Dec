package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.entities.interfaces.Motorcycle;

public abstract class MotorcycleImpl implements Motorcycle {
    private String model;
    private int horsepower;
    private double cubicCentimeters;

    protected MotorcycleImpl(String model, int horsepower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsepower(horsepower);
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setModel(String model) {
        if (model == null || model.trim().isEmpty() || model.length() < 4){
            throw new IllegalArgumentException(String.format("Model %s cannot be less than 4 symbols.", model));
        }
        this.model = model;
    }

    protected void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return this.cubicCentimeters / this.horsepower * laps;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsepower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }
}