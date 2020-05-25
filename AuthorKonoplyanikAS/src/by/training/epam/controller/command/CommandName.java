package by.training.epam.controller.command;

public enum CommandName {
	
	SIGN_IN,
	SIGN_OUT,
	SIGN_UP,
	ERROR,
	LOCALE,
	ADD_DRINK,
	ADD_INGREDIENT,
	ADD_DRINK_TO_ORDER;
	
	public static CommandName parse(String name) {
		CommandName commandName;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException | NullPointerException e) {
			commandName = CommandName.ERROR;
		}
		return commandName;
	}
	
}
