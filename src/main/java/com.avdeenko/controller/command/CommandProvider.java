package com.avdeenko.controller.command;

import com.avdeenko.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<ParameterName, Command> commands = new HashMap<>();

    public CommandProvider(){
        commands.put(ParameterName.CREATE_HOUSE, new CreateHouse());
        commands.put(ParameterName.CREATE_APARTMENTS, new CreateApartments());
        commands.put(ParameterName.CREATE_RESIDENTS, new CreateResidents());
        commands.put(ParameterName.CREATE_HOUSE_AUTO, new CreateHouseAuto());
        commands.put(ParameterName.DISPLAY_HOUSE, new DisplayHouse());
        commands.put(ParameterName.DELETE_HOUSE, new DeleteHouse());
        commands.put(ParameterName.COMPARE_HOUSE, new CompareHouse());
        commands.put(ParameterName.CALCULATE_SQUARE_AND_RESIDENTS, new CalculateSquareAndResidents());
    }

    public Command getCommand(String commandName){
        Command command;
        ParameterName valueName;

        commandName = commandName.toUpperCase();
        valueName = ParameterName.valueOf(commandName);

        command = commands.get(valueName);

        return command;
    }
}
