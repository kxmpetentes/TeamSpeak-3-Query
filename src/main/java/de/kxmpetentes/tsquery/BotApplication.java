package de.kxmpetentes.tsquery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.exception.TS3CommandFailedException;
import de.kxmpetentes.tsquery.config.Config;
import de.kxmpetentes.tsquery.listener.EventManager;
import de.kxmpetentes.tsquery.listener.SupportListener;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 28.02.2021 um 08:02
 */

@Getter
public class BotApplication {

    @Getter
    private static BotApplication instance;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private TS3Api api;
    private TS3Query query;
    private Config config;
    private EventManager eventManager;

    public void onEnable() {

        instance = this;

        loadConfig();
        loadQuery();
        loadEventManager();

    }

    public void onDisable() {
        if (query.isConnected()) {
            query.exit();
        }
    }

    @SneakyThrows
    private void loadConfig() {

        File file = new File("config.json");

        if (!file.exists()) {
            config = new Config();
            objectMapper.writeValue(file, config);

            System.out.println("Config created!");
            System.exit(-1);
        }

        config = objectMapper.readValue(file, Config.class);

    }

    private void loadQuery() {
        final TS3Config ts3Config = new TS3Config();
        ts3Config.setHost(config.getHost());
        ts3Config.setEnableCommunicationsLogging(true);

        query = new TS3Query(ts3Config);
        query.connect();

        api = query.getApi();
        api.login(config.getUsername(), config.getPassword());
        api.selectVirtualServerById(config.getVirtualServerId());

        try {
            api.setNickname(config.getNickname());
        } catch (TS3CommandFailedException e) {
            api.setNickname(config.getNickname() + "#" + ThreadLocalRandom.current().nextInt(100));
        }
    }

    private void loadEventManager() {
        eventManager = new EventManager(this);

        new SupportListener(eventManager);
    }

}
