package wtf.violet.portvolio.gapplelink.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wtf.violet.portvolio.gapplelink.GappleLink;

public final class StartScenarioCommand implements CommandExecutor
{

    private final GappleLink link;

    public StartScenarioCommand(GappleLink link)
    {
        this.link = link;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (link.isScenarioStarted())
        {
            sender.sendMessage(ChatColor.RED + "Scenario is already started!");
            return true;
        }

        link.setScenarioStarted();

        link.getServer().getScheduler().runTaskTimer(link, () ->
        {
            final Player highest = link.getHighestApples();

            if (highest == null)
            {
                return;
            }

            highest.damage(0xDEAD);
        }, 20 * 60, 20 * 60);

        sender.sendMessage(ChatColor.GREEN + "The scenario has been started!");

        return true;
    }

}
