package by.training.epam.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.training.epam.controller.command.impl.PreAddDrink;
import by.training.epam.controller.command.impl.PreAddIngredient;
import by.training.epam.controller.command.impl.PreAddOrderDrink;
import by.training.epam.controller.command.impl.PushOrder;
import by.training.epam.controller.command.impl.RemoveDrink;
import by.training.epam.controller.command.impl.RemoveIngredient;
import by.training.epam.controller.command.impl.ChangeLang;
import by.training.epam.controller.command.impl.DoNothing;
import by.training.epam.controller.command.impl.Error;
import by.training.epam.controller.command.impl.GotoMainPage;
import by.training.epam.controller.command.impl.GotoOrderPage;
import by.training.epam.controller.command.impl.GotoRegistrationPage;
import by.training.epam.controller.command.impl.GotoSignInPage;
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
        commands.put(CommandName.ADD_DRINK_TO_ORDER, new PreAddOrderDrink());
        commands.put(CommandName.PUSH_ORDER, new PushOrder());
        commands.put(CommandName.GOTO_MAIN_PAGE, new GotoMainPage());
        commands.put(CommandName.GOTO_SIGN_IN_PAGE, new GotoSignInPage());
        commands.put(CommandName.GOTO_REGISTRATION_PAGE, new GotoRegistrationPage());
        commands.put(CommandName.GOTO_ORDER_PAGE, new GotoOrderPage());
        commands.put(CommandName.REMOVE_INGREDIENT, new RemoveIngredient());
        commands.put(CommandName.REMOVE_DRINK, new RemoveDrink());
        commands.put(CommandName.DO_NOTHING, new DoNothing());
	}

    public Command getCommand(String cName){
        CommandName commandName = CommandName.parse(cName);
        Command command = commands.get(commandName);
        return command;
    }

}
