package de.kxmpetentes.tsquery.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import de.kxmpetentes.tsquery.BotApplication;
import de.kxmpetentes.tsquery.config.Config;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 28.02.2021 um 23:49
 */

public class ChannelHoppingListener extends TS3EventAdapter {

    private final TS3Api api = BotApplication.getInstance().getApi();
    private final Config config = BotApplication.getInstance().getConfig();

    public ChannelHoppingListener(EventManager eventManager) {
        eventManager.addEvent(this);
    }

    @Override
    public void onClientMoved(ClientMovedEvent event) {

    }
}
