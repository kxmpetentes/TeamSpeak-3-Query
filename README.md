# TeamSpeak-3-Query

Example Class for SpigotPlugin
```Java
public class TestPlugin extends JavaPlugin {

    private final BotApplication botApplication = new BotApplication();

    @Override
    public void onEnable() {
        botApplication.onEnable();
    }

    @Override
    public void onDisable() {
        botApplication.onDisable();
    }
}
```

Example Class for BungeePlugin
```Java
public class TestPlugin extends Plugin {

    private final BotApplication botApplication = new BotApplication();

    @Override
    public void onEnable() {
        botApplication.onEnable();
    }

    @Override
    public void onDisable() {
        botApplication.onDisable();
    }
}
```

