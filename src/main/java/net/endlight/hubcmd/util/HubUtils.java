package net.endlight.hubcmd.util;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import net.endlight.hubcmd.Main;

import java.net.InetSocketAddress;

public class HubUtils {

    public static Position strToPos(String str) {
        double x = Double.valueOf(str.split(":")[0]);
        double y = Double.valueOf(str.split(":")[1]);
        double z = Double.valueOf(str.split(":")[2]);
        Level level = Main.getInstance().getServer().getLevelByName(str.split(":")[3]);
        return new Position(x, y, z, level);
    }

    public static void teleportToLobby(Player player) {
        String strPos = Main.getInstance().getConfig().getString("teleport.position");
        Position position = HubUtils.strToPos(strPos);
        player.teleport(position);
    }

    public static void transferToLobby(Player player) {
        String ip = Main.getInstance().getConfig().getString("transfer.ip");
        int port = Main.getInstance().getConfig().getInt("transfer.port");
        InetSocketAddress address = new InetSocketAddress(ip,port);
        player.transfer(address);
    }

    public static void quitGame(Player player, String ln) {
        for (String cl : Main.getInstance().getConfig().getStringList(ln)){
            player.getServer().dispatchCommand(player,cl);
        }
    }
}
