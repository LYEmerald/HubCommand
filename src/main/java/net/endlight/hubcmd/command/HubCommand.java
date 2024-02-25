package net.endlight.hubcmd.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import net.endlight.hubcmd.Main;
import net.endlight.hubcmd.util.HubUtils;

import java.util.List;

public class HubCommand extends Command {

    public HubCommand() {
        super("hub", "Hub Command","Usage: /hub", new String[]{"lobby"});
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            boolean oneServerMode = Main.getInstance().getConfig().getBoolean("OneServerMode");
            List<String> worlds  = Main.getInstance().getConfig().getStringList("GameWorld");
            for (String msg : Main.getInstance().getConfig().getStringList("HubMessage")){
                player.sendMessage(msg);
            }
            if (oneServerMode){
                String ln = player.getLevel().getName();
                if (worlds.contains(ln)){
                    HubUtils.quitGame(player,ln);
                    if (Main.getInstance().getConfig().getBoolean("HubWhenQuit")) {
                        HubUtils.teleportToLobby(player);
                    }
                } else {
                    HubUtils.teleportToLobby(player);
                }
            } else {
                HubUtils.transferToLobby(player);
            }
            return true;
        } else {
            Main.getInstance().getLogger().info(TextFormat.RED + "此命令不能在控制台执行!");
            return false;
        }
    }
}
