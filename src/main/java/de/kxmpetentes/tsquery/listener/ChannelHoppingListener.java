package de.kxmpetentes.tsquery.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.kxmpetentes.tsquery.BotApplication;
import de.kxmpetentes.tsquery.objects.ChannelHistory;
import de.kxmpetentes.tsquery.objects.Config;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 28.02.2021 um 23:49
 */

public class ChannelHoppingListener extends TS3EventAdapter {

    private final Timer timer = new Timer();
    private final TS3Api api = BotApplication.getInstance().getApi();
    private final Config config = BotApplication.getInstance().getConfig();
    private final HashMap<Client, ChannelHistory> clientChannelHistory = new HashMap<>();

    public ChannelHoppingListener(EventManager eventManager) {
        eventManager.addEvent(this);
        startTimer();
    }

    @Override
    public void onClientMoved(ClientMovedEvent event) {
        Client client = api.getClientInfo(event.getClientId());

        if (client == null) {
            return;
        }

        ChannelHistory channelHistory = clientChannelHistory.get(client);
        channelHistory.addChannel();
        clientChannelHistory.put(client, channelHistory);

        switch (channelHistory.getChannelHopping()) {
            case 1:
                api.sendPrivateMessage(client.getId(), "ChannelHopping ist nicht erlaubt!");
            case 2:
                api.kickClientFromServer("ChannelHopping ist nicht erlaubt!", client.getId());
        }
    }

    @Override
    public void onClientJoin(ClientJoinEvent event) {
        Client client = api.getClientInfo(event.getClientId());

        if (client == null) {
            return;
        }

        clientChannelHistory.put(client, new ChannelHistory());
    }

    @Override
    public void onClientLeave(ClientLeaveEvent event) {
        Client client = api.getClientInfo(event.getClientId());

        clientChannelHistory.remove(client);
    }

    public void startTimer() {
        for (Client client : api.getClients()) {
            clientChannelHistory.put(client, new ChannelHistory());
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                clientChannelHistory.values().forEach(ChannelHistory::removeChannel);

            }
        }, 5 * 1000, 5 * 1000);
    }

    public void cancelTimer() {
        timer.cancel();
    }
}
