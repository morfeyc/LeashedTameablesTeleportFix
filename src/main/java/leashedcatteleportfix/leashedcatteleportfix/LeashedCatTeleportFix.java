package leashedcatteleportfix.leashedcatteleportfix;

import org.bukkit.plugin.java.JavaPlugin;

public final class LeashedCatTeleportFix extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }
}
