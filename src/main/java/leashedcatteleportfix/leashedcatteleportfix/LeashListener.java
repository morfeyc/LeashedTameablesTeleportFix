package leashedcatteleportfix.leashedcatteleportfix;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.EntityUnleashEvent;

import java.util.HashMap;
import java.util.Map;

public class LeashListener implements Listener {

    private Map<Integer, Location> tameablePrevPos = new HashMap<>();

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityTeleported(final EntityTeleportEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof Tameable)) return;
        Tameable tameable = (Tameable) entity;

        if (!tameable.isLeashed() || !tameable.isTamed()) return;

        Entity leashHolder = tameable.getLeashHolder();
        if (!(leashHolder instanceof Player)) {
            event.setCancelled(true);
            tameablePrevPos.put(entity.getEntityId(), entity.getLocation());
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onEntityUnleashEvent(final EntityUnleashEvent event){
        if (!event.getReason().equals(EntityUnleashEvent.UnleashReason.DISTANCE)) return;
        Entity leashHolder = ((LivingEntity) event.getEntity()).getLeashHolder();

        if (!(leashHolder instanceof Player)){
            Entity entity = event.getEntity();
            entity.teleport(tameablePrevPos.get(entity.getEntityId()));
            tameablePrevPos.remove(entity.getEntityId());
            event.setCancelled(true);
        }
    }
}
