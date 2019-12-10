package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;

import java.util.Scanner;

public class EngineImpl implements Engine {
    private ChampionshipController controller;
    private Scanner scanner;

    public EngineImpl(ChampionshipController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();
                if (result.equals("End")) {
                    break;
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }
            System.out.println(result);
        }

    }

    private String processInput() {
        String input = this.scanner.nextLine();
        String[] inputData = input.split("\\s+");
        String command = inputData[0];
        String result = "";
        switch (command) {
            case "CreateRider":
                result = this.controller.createRider(inputData[1]);
                break;
            case "CreateMotorcycle":
                result = this.controller
                        .createMotorcycle(inputData[1], inputData[2], Integer.parseInt(inputData[3]));
                break;
            case "AddMotorcycleToRider":
                result = this.controller.addMotorcycleToRider(inputData[1], inputData[2]);
                break;
            case "AddRiderToRace":
                result = this.controller.addRiderToRace(inputData[1], inputData[2]);
                break;
            case "CreateRace":
                result = this.controller.createRace(inputData[1],Integer.parseInt(inputData[2]));
                break;
            case "StartRace":
                result = this.controller.startRace(inputData[1]);
                break;
            case "End":
                result = "End";
                break;
        }
        return result.trim();
    }

}
