package by.training.epam.controller.command;

public enum CommandName {
	
	SIGN_IN,
	SIGN_OUT,
	SIGN_UP,
	ERROR,
	LOCALE,
	ADD_DRINK,
	ADD_INGREDIENT,
	ADD_DRINK_TO_ORDER,
	PUSH_ORDER,
	GOTO_MAIN_PAGE,
	GOTO_SIGN_IN_PAGE,
	GOTO_REGISTRATION_PAGE,
	GOTO_ORDER_PAGE,
	GOTO_UPDATE_ORDER_PAGE,
	GOTO_UPDATE_ORDER_DRINK_PAGE,
	REMOVE_INGREDIENT,
	REMOVE_DRINK,
	DO_NOTHING,
	UPDATE_ORDER_REMOVE_DRINK,
	UPDATE_ORDER_REMOVE_EXTRA;
	
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
