package de.kxmpetentes.tsquery.config;

import lombok.Data;

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

}
