package de.kxmpetentes.tsquery.objects;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 01.03.2021 um 15:30
 */

public class ChannelHistory {

    int channels = 0;

    public void addChannel() {
        if (channels >= 0) {
            channels++;
        }
    }

    public void removeChannel() {
        channels--;
    }

    public int getChannelHopping() {
        switch (channels) {
            case 8:
                return 1;
            case 10:
                return 2;
            default:
                return 0;
        }
    }

    public int getChannels() {
        return channels;
    }
}
