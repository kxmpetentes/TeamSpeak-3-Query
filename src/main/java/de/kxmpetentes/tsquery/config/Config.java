package de.kxmpetentes.tsquery.config;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 28.02.2021 um 08:03
 */

@Data
public class Config {

    private String host = "host";
    private String username = "serveradmin";
    private String password = "secretpassword";
    private String nickname = "VeryStupidBot";
    private int virtualServerId = 1;
    private int supportChannelId = 100;
    private ArrayList<Integer> supportNotificationRanks = new ArrayList<>();
    private int afkChannelId = 100;
    private String afkMessage = "Du wurdest in den AFK-Channel gemoved";

}
