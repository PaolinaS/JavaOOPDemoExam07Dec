package motocrossWorldChampionship.entities;

public class PowerMotorcycle extends MotorcycleImpl {
    private static final double CUBIC_CENTIMETERS = 450;
    private static final int MIN_HP = 70;
    private static final int MAX_HP = 100;

    public PowerMotorcycle(String model, int horsepower) {
        super(model, horsepower, CUBIC_CENTIMETERS);
    }

    @Override
    public void setHorsepower(int horsepower) {
        if (horsepower < MIN_HP || horsepower > MAX_HP) {
            throw new IllegalArgumentException(String.format("Invalid horse power: %d.", horsepower));
        }
        super.setHorsepower(horsepower);
    }
}
