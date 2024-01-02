package me.zerodevelopment.dweapon;

import me.zerodevelopment.dweapon.item.Firewand;
import net.fabricmc.api.ClientModInitializer;

import static me.zerodevelopment.dweapon.item.ItemManager.FIREWAND;
import static me.zerodevelopment.dweapon.item.ItemManager.registerChargable;

public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerChargable(FIREWAND, Firewand.CHARGE_TIME);
    }
}
