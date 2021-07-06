package de.jeff_media.fasterminecarts.listeners;

import de.jeff_media.fasterminecarts.FasterMinecarts;
import de.jeff_media.fasterminecarts.config.Config;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;

public class MinecartListener implements Listener {

    private final FasterMinecarts main = FasterMinecarts.getInstance();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMinecartCreate(VehicleCreateEvent event) {
        if(!(event.getVehicle() instanceof Minecart)) return;
        Minecart minecart = (Minecart) event.getVehicle();
        double maxSpeed = main.getMaxSpeed(minecart);
        minecart.setMaxSpeed(maxSpeed);
        if(Config.get().isDebug()) {
            System.out.println("Max Speed: " + maxSpeed);
        }
    }

}
