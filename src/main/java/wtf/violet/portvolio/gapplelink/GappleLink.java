package wtf.violet.portvolio.gapplelink;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.violet.portvolio.common.StartScenarioCommand;
import wtf.violet.portvolio.gapplelink.listener.AppleListener;

import java.util.HashMap;
import java.util.Map;

public final class GappleLink extends JavaPlugin
{

    private final Map<Player, Integer> applesByPlayer = new HashMap<>();
    private boolean scenarioStarted = false;

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new AppleListener(this), this);

        new StartScenarioCommand(this, (task) ->
        {
            final Player highest = getHighestApples();

            if (highest == null)
            {
                return;
            }

            highest.damage(0xDEAD);
        }, 20 * 60).register();
    }

    /**
     * Get the player with the highest amount of consumed golden apples.
     * @return Player, or null if no players are recorded
     */
    private Player getHighestApples()
    {
        Player highest = null;
        int amount = 0;

        for (final Player player : getServer().getOnlinePlayers())
        {
            final int apples = applesByPlayer.getOrDefault(player, 0);

            if (highest == null || apples > amount)
            {
                highest = player;
                amount = apples;
            }
        }

        return highest;
    }

    public void incrementApples(final Player player)
    {
        applesByPlayer.merge(player, 1, Integer::sum);
    }

    public void setScenarioStarted()
    {
        scenarioStarted = true;
    }

    public boolean isScenarioStarted()
    {
        return scenarioStarted;
    }

}
