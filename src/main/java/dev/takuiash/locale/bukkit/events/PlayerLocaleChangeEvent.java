package dev.takuiash.locale.bukkit.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import dev.takuiash.locale.bukkit.Locale;

public class PlayerLocaleChangeEvent extends PlayerEvent {
	
    private static final HandlerList HANDLERS = new HandlerList();

    private final Locale locale;
    private final Locale oldLocale;

    public PlayerLocaleChangeEvent(final Player who, final Locale locale, final Locale oldLocale) {
        super(who);
        
        this.locale = locale;
        this.oldLocale = oldLocale;
    }

    /**
     * Gets the local of the player.
     *
     * @return The player's new locale.
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Gets the old local the player used before.
     *
     * @return The old player's locale. Null when had no locale before.
     */
    public Locale getOldLocale() {
        return oldLocale;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
