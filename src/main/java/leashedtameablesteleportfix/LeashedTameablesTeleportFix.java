package leashedtameablesteleportfix;

import org.bukkit.plugin.java.JavaPlugin;

public class LeashedTameablesTeleportFix extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TameableListener(), this);
    }
}
