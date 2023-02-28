package dev.takuiash.locale.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import dev.takuiash.locale.bukkit.controllers.LocaleController;

public class LocaleAPI {

	private static LocaleController controller = JavaPlugin.getPlugin(LocalePlugin.class).getLocaleController();
	
	public static void changeLanguage(Player player, Locale locale) {
		controller.changeLanguage(player, locale);
	}
	
	public static Locale getLanguage(Player player) {
		return controller.getPlayerLocale(player);
	}
}
