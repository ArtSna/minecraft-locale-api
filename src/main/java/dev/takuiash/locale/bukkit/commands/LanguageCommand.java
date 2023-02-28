package dev.takuiash.locale.bukkit.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.takuiash.commons.command.Arguments;
import dev.takuiash.commons.command.Command;
import dev.takuiash.locale.bukkit.LocaleAPI;
import dev.takuiash.locale.bukkit.LocalePlugin;
import dev.takuiash.locale.bukkit.MessageManager;

public class LanguageCommand extends Command {
	
	private final MessageManager message;
	
	public LanguageCommand(LocalePlugin plugin) {
		super("language");
		
		this.message = plugin.getMessage();
	}

	@Override
	public void perform(CommandSender sender, Arguments args) {
		message
		.replaceAll("<language>", LocaleAPI.getLanguage((Player) sender).getName())
		.sendMessage((Player) sender, "current-language");
	}

}
