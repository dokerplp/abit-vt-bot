package bot.commands

import bot.run.AbitVTBot

class Receiver(private val bot : AbitVTBot) {
    fun setCommands(invoker: Invoker) {
        invoker.add("/help", HelpCommand(bot))
        invoker.add("/faq", FaqCommand(bot))
        invoker.add("/subjects", SubjectsCommand(bot))
        invoker.add("/links", LinksCommand(bot))
        invoker.add("/plan", PlanCommand(bot))
        invoker.add("/author", AuthorCommand(bot))
        invoker.add("/settings", SettingsCommand(bot))
        invoker.add("/start", StartCommand(bot))
    }
}