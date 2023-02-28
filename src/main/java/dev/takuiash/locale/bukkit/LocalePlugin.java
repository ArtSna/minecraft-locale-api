package dev.takuiash.locale.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.pretty.database.Database;

import dev.takuiash.commons.command.CommandManager;
import dev.takuiash.locale.bukkit.commands.LanguageCommand;
import dev.takuiash.locale.bukkit.controllers.LocaleController;
import dev.takuiash.locale.bukkit.database.files.ConfigFile;
import dev.takuiash.locale.bukkit.database.repositories.LocaleRepository;
import dev.takuiash.locale.bukkit.listeners.LocaleListener;

public class LocalePlugin extends JavaPlugin {

	private CommandManager commandManager = new CommandManager(this);
	
	private ConfigFile configFile = new ConfigFile(this);
	
	private Database database = new Database(getDataFolder(), "database.db");
	
	private LocaleRepository localeRepository = new LocaleRepository(database);
	private LocaleController localeController = new LocaleController(localeRepository);
	
	private MessageManager message;
	
	@Override
	public void onEnable() {
		message = new MessageManager(this);
		
		commandManager.registerCommand(new LanguageCommand(this));
		
		message.saveMessageResource(Locale.en_us, "lang/messages-en_us.yml");
		message.saveMessageResource(Locale.pt_br, "lang/messages-pt_br.yml");
		
		Bukkit.getPluginManager().registerEvents(new LocaleListener(this), this);
	}
	
	public ConfigFile getConfig() {
		return configFile;
	}

	public LocaleController getLocaleController() {
		return localeController;
	}
	
	public MessageManager getMessage() {
		return message;
	}
}
