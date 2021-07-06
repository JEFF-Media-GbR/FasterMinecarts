package de.jeff_media.fasterminecarts.listeners;

import de.jeff_media.fasterminecarts.config.Config;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;

import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SpeedometerListener implements Listener {

    HashMap<Minecart, SpeedometerData> speedometerMap = new HashMap<>();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMinecartMove(VehicleMoveEvent event) {
        if(!Config.get().isSpeedometer()) return;
        if(!(event.getVehicle() instanceof Minecart)) return;
        Minecart minecart = (Minecart) event.getVehicle();
        double speed = getSpeed(minecart);
        BaseComponent[] component = TextComponent.fromLegacyText(String.format(Config.get().getSpeedometerText(),speed));
        for(Entity player : minecart.getPassengers().stream().filter(entity -> entity instanceof Player).collect(Collectors.toList())) {
            ((Player)player).spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
        }
    }

    // Blocks per second
    public double getSpeed(Minecart minecart) {
        SpeedometerData data = speedometerMap.get(minecart);
        speedometerMap.put(minecart, new SpeedometerData(minecart.getLocation()));
        if(data == null) return 0;
        long timeDifference = System.currentTimeMillis() - data.getTime();
        double distance = data.getLastLocation().distance(minecart.getLocation());
        double speed = distance / timeDifference * 1000;
        return speed;
    }

    @RequiredArgsConstructor
    public static class SpeedometerData {
        @Getter private final Location lastLocation;
        @Getter private final long time = System.currentTimeMillis();
    }


}
