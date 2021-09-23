package bot.util.settings;

import bot.run.AbitVTBot;

public class SettingsReceiver {
    private final AbitVTBot bot;

    public SettingsReceiver(AbitVTBot bot) {
        this.bot = bot;
    }

    public void setSettings(SettingsInvoker invoker) {
        invoker.add("⚙️ Settings \uD83D\uDEE0", new Settings(bot));
        invoker.add("⚙️ En \uD83C\uDDFA\uD83C\uDDF8", new EN(bot));
        invoker.add("⚙️ Ru \uD83C\uDDF7\uD83C\uDDFA", new RU(bot));
    }
}
