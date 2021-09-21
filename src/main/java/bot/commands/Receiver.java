package bot.commands;

import bot.run.AbitVTBot;

public class Receiver {
    public void setCommands(AbitVTBot bot, Invoker invoker){
        invoker.add("help", new HelpCommand(bot));
    }
}
