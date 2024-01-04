package me.zerodevelopment.dweapon;

import me.zerodevelopment.dweapon.item.Firewand;
import me.zerodevelopment.dweapon.item.ItemManager;
import net.fabricmc.api.ClientModInitializer;

import static me.zerodevelopment.dweapon.item.ItemManager.*;

public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerChargable(FIREWAND, Firewand.CHARGE_TIME);
        registerWhip(WHIP);
    }
}
