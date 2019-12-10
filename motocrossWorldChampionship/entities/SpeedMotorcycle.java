package motocrossWorldChampionship.entities;


public class SpeedMotorcycle extends MotorcycleImpl {
    private static final double CUBIC_CENTIMETERS = 125;
    private static final int MIN_HP = 50;
    private static final int MAX_HP = 69;

    public SpeedMotorcycle(String model, int horsepower) {
        super(model, horsepower, CUBIC_CENTIMETERS);
    }

    @Override
    protected void setHorsepower(int horsepower) {
        if (horsepower < MIN_HP || horsepower > MAX_HP) {
            throw new IllegalArgumentException(String.format("Invalid horse power: %d.", horsepower));
        }
        super.setHorsepower(horsepower);
    }
}
