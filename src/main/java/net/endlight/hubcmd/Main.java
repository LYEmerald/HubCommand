package net.endlight.hubcmd;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import net.endlight.hubcmd.command.HubCommand;
import net.endlight.hubcmd.listener.HubListener;

public class Main extends PluginBase {

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.getLogger().info(TextFormat.GREEN + "HubCommand 已经启用!");
        this.getDataFolder().mkdirs();
        this.saveDefaultConfig();
        Config config = this.getConfig();
        this.getServer().getCommandMap().register("hub", new HubCommand());
        this.getServer().getPluginManager().registerEvents(new HubListener(),this);
    }

    @Override
    public void onDisable() {
        this.getLogger().info(TextFormat.GREEN + "HubCommand 已经关闭,感谢您的使用!");
    }
}
