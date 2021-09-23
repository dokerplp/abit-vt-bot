package bot.commands;

import bot.run.AbitVTBot;

public class Receiver {

    private final AbitVTBot bot;

    public Receiver(AbitVTBot bot) {
        this.bot = bot;
    }

    public void setCommands(Invoker invoker){
        invoker.add("/help", new HelpCommand(bot));
        invoker.add("/faq", new FaqCommand(bot));
        invoker.add("/subjects", new SubjectsCommand(bot));
        invoker.add("/links", new LinksCommand(bot));
        invoker.add("/plan", new PlanCommand(bot));
        invoker.add("/author", new AuthorCommand(bot));
        invoker.add("/settings", new SettingsCommand(bot));
        invoker.add("/start", new StartCommand(bot));
    }
}
