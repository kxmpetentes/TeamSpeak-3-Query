package de.kxmpetentes.tsquery.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.kxmpetentes.tsquery.BotApplication;
import de.kxmpetentes.tsquery.config.Config;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 28.02.2021 um 23:16
 */

public class AfkListener {

    private final Timer timer = new Timer();
    private final TS3Api api;
    private final Config config;

    public AfkListener(BotApplication botApplication) {
        this.api = botApplication.getApi();
        this.config = botApplication.getConfig();

        startTimer();
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                for (Client client : api.getClients()) {

                    int afkChannelId = config.getAfkChannelId();
                    int clientId = client.getId();

                    if (client.getIdleTime() >= 0 && client.isAway() && client.getChannelId() != afkChannelId) {
                        api.moveClient(clientId, afkChannelId);
                        api.sendPrivateMessage(clientId, config.getAfkMessage());
                    }

                }

            }
        }, 0, 1);
    }

    public void cancelTimer() {
        timer.cancel();
    }

}
