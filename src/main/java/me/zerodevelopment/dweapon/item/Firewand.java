package me.zerodevelopment.dweapon.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Firewand extends ToolItem {

    public static final float CHARGE_TIME = 3, FIREBALL_SPEED = 1.2f, EXPLOSION_POWER = 5, MIN_CHARGE_TIME = 0.25f;

    public Firewand(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        float chargeTime;
        if (user != null && !world.isClient && (chargeTime = (float)(getMaxUseTime(stack)-remainingUseTicks)/20f) >= MIN_CHARGE_TIME) {
            Vec3d lookd = Vec3d.fromPolar(user.getPitch(), user.getYaw());
            Vec3d v = lookd.multiply(FIREBALL_SPEED);
            chargeTime /= CHARGE_TIME;
            chargeTime = chargeTime > 1 ? 1 : chargeTime;
            FireballEntity fireball = new FireballEntity(world, user, lookd.x, lookd.y, lookd.z, (int)(EXPLOSION_POWER*chargeTime));
            fireball.setPosition(user.getEyePos().add(lookd.multiply(0.25)));
            world.spawnEntity(fireball);
            stack.damage((int)(EXPLOSION_POWER*chargeTime), user, (p) -> {p.sendToolBreakStatus(user.getActiveHand());});
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }
}
