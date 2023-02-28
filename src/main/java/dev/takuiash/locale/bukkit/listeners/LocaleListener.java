package dev.takuiash.locale.bukkit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;

import dev.takuiash.locale.bukkit.Locale;
import dev.takuiash.locale.bukkit.LocalePlugin;
import dev.takuiash.locale.bukkit.controllers.LocaleController;

public class LocaleListener implements Listener {
	
	private LocaleController controller;
	
	public LocaleListener(LocalePlugin plugin) {
		this.controller = plugin.getLocaleController();
	}

	@EventHandler
	public void onPlayerLocaleChange(PlayerLocaleChangeEvent e) {
		controller.changeLanguage(e.getPlayer(), Locale.valueOf(e.getLocale()));
	}

	
}
