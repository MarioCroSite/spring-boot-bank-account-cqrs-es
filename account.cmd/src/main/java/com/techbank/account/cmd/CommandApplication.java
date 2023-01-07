package com.techbank.account.cmd;

import com.techbank.account.cmd.api.commands.*;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {

	private CommandDispatcher commandDispatcher;

	private CommandHandler commandHandler;

	public CommandApplication(CommandDispatcher commandDispatcher, CommandHandler commandHandler) {
		this.commandDispatcher = commandDispatcher;
		this.commandHandler = commandHandler;
	}

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(OpenAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DepositFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(WithdrawFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(RestoreReadDbCommand.class, commandHandler::handle);
	}

}
