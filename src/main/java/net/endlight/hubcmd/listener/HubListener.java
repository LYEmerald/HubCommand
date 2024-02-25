package net.endlight.hubcmd.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import net.endlight.hubcmd.Main;
import net.endlight.hubcmd.util.HubUtils;

public class HubListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Main.getInstance().getConfig().getBoolean("HubWhenJoin")){
            Main.getInstance().getServer().getScheduler().scheduleDelayedTask(Main.getInstance(),() -> {
                HubUtils.teleportToLobby(player);
            },Main.getInstance().getConfig().getInt("DelayTime"));
        }
    }
}
