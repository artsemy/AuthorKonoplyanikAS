package by.training.epam.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.training.epam.controller.command.impl.PreAddDrink;
import by.training.epam.controller.command.impl.PreAddIngredient;
import by.training.epam.controller.command.impl.ChangeLang;
import by.training.epam.controller.command.impl.Error;
import by.training.epam.controller.command.impl.SignIn;
import by.training.epam.controller.command.impl.SignOut;
import by.training.epam.controller.command.impl.SignUp;

public class CommandFactory {
	
	private static final Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandFactory() {
		commands.put(CommandName.SIGN_IN, new SignIn());
        commands.put(CommandName.SIGN_UP, new SignUp());
        commands.put(CommandName.SIGN_OUT, new SignOut());
        commands.put(CommandName.ERROR, new Error());
        commands.put(CommandName.LOCALE, new ChangeLang());
        commands.put(CommandName.ADD_DRINK, new PreAddDrink());
        commands.put(CommandName.ADD_INGREDIENT, new PreAddIngredient());
	}

    public Command getCommand(String cName){
        CommandName commandName = CommandName.parse(cName);
        Command command = commands.get(commandName);
        return command;
    }

}
