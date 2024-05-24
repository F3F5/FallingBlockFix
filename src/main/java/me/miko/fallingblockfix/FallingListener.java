package me.miko.fallingblockfix;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.Listener;

import java.util.stream.Collectors;
import java.util.List;

public class FallingListener implements Listener {
    private final FallingBlockFix plugin;

    public FallingListener(final FallingBlockFix plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void fixFallings(final EntityChangeBlockEvent event) {
        if (event.getEntityType() == EntityType.FALLING_BLOCK) {
            final List<Entity> allEntitiesInRadius = (List<Entity>) event.getEntity().getNearbyEntities(this.plugin.getConfig().getDouble("fallingBlock.xRadius"), this.plugin.getConfig().getDouble("fallingBlock.yRadius"), this.plugin.getConfig().getDouble("fallingBlock.zRadius")).stream().filter(entity -> entity.getType() == EntityType.FALLING_BLOCK).collect(Collectors.toList());
            if (allEntitiesInRadius.size() > this.plugin.getConfig().getInt("fallingBlock.maxCountInRange")) {
                for (final Entity entitiesInRadius : allEntitiesInRadius) {
                    entitiesInRadius.remove();
                }
            }
        }
    }
}