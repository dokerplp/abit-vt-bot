package bot.commands;

import bot.run.AbitVTBot;

public class Receiver {

    private final AbitVTBot bot;

    public Receiver(AbitVTBot bot) {
        this.bot = bot;
    }

    public void setCommands(Invoker invoker){
        invoker.add("/help", new HelpCommand(bot));
        invoker.add("/start", new StartCommand(bot));
    }
}
