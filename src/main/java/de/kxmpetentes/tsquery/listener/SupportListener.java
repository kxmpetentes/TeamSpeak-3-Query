package de.kxmpetentes.tsquery.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.kxmpetentes.tsquery.BotApplication;
import de.kxmpetentes.tsquery.objects.Config;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 28.02.2021 um 08:54
 */

public class SupportListener extends TS3EventAdapter {

    private final TS3Api api = BotApplication.getInstance().getApi();
    private final Config config = BotApplication.getInstance().getConfig();

    public SupportListener(EventManager eventManager) {
        eventManager.addEvent(this);
    }

    @Override
    public void onClientMoved(ClientMovedEvent event) {
        Client client = api.getClientInfo(event.getClientId());

        if (client == null) {
            return;
        }

        if (event.getTargetChannelId() == config.getSupportChannelId()) {

            boolean support = false;

            for (Client apiClient : api.getClients()) {
                for (int rankId : config.getSupportNotificationRanks()) {
                    if (apiClient.isInServerGroup(rankId)) {
                        support = true;
                        api.sendPrivateMessage(apiClient.getId(), client.getNickname() + " benötigt Support!");
                    }
                }
            }

            if (support) {
                api.sendPrivateMessage(client.getId(), "Es wurden Supporter benachrichtigt.");
            } else {
                api.sendPrivateMessage(client.getId(), "Es sind derzeit keine Supporter verfügbar.");
            }

        }
    }
}
