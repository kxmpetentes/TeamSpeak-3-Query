package de.kxmpetentes.tsquery.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import de.kxmpetentes.tsquery.BotApplication;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 28.02.2021 um 08:42
 */

public class EventManager {

    private final TS3Api api;

    public EventManager(BotApplication botApplication) {
        api = botApplication.getApi();
    }

    public void addEvent(TS3Listener listener) {
        api.addTS3Listeners(listener);
    }

    public void registerAllEvents() {
        api.registerAllEvents();
    }

}
