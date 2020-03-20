package by.training.epam.controller.command;

import java.util.HashMap;
import java.util.Map;

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
	}

    public Command getCommand(String cName){
        CommandName commandName = CommandName.parse(cName);
        Command command = commands.get(commandName);
        return command;
    }

}
