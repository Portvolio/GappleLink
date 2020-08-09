package wtf.violet.portvolio.gapplelink.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import wtf.violet.portvolio.gapplelink.GappleLink;

public class AppleListener implements Listener
{

    private final GappleLink link;

    public AppleListener(GappleLink link)
    {
        this.link = link;
    }

    @EventHandler
    public void onConsume(final PlayerItemConsumeEvent event)
    {
        if (event.getItem().getType().equals(Material.GOLDEN_APPLE))
        {
            link.incrementApples(event.getPlayer());
        }
    }

}
