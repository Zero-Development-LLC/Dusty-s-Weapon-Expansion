package me.zerodevelopment.dweapon.item;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Firewand extends ToolItem {

    public static final float CHARGE_TIME = 2, FIREBALL_SPEED = 1.2f, EXPLOSION_POWER = 3, MIN_CHARGE_TIME = 0f, MIN_TO_FIRE_CHARGE = 1.5f;

    public Firewand(ToolMaterial material, Settings settings) {
        super(material, settings);
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
        if (user != null && (chargeTime = (float)(getMaxUseTime(stack)-remainingUseTicks)/20f) >= MIN_CHARGE_TIME) {
            if (world.isClient) {
                user.swingHand(user.getActiveHand());
            }else {
                Vec3d lookd = Vec3d.fromPolar(user.getPitch(), user.getYaw());
                Vec3d v = lookd.multiply(FIREBALL_SPEED);
                chargeTime /= CHARGE_TIME;
                chargeTime = chargeTime > 1 ? 1 : chargeTime;
                if (EXPLOSION_POWER*chargeTime<MIN_TO_FIRE_CHARGE) {
                    SmallFireballEntity fireball = new SmallFireballEntity(world, user, lookd.x, lookd.y, lookd.z);
                    fireball.setPosition(user.getEyePos().add(lookd.multiply(0.75)));
                    world.spawnEntity(fireball);
                    stack.damage(1, user, (p) -> {p.sendToolBreakStatus(user.getActiveHand());});
                } else {
                    FireballEntity fireball = new FireballEntity(world, user, lookd.x, lookd.y, lookd.z, (int)(EXPLOSION_POWER*chargeTime));
                    fireball.setPosition(user.getEyePos().add(lookd.multiply(0.75)));
                    world.spawnEntity(fireball);
                    stack.damage((int)(EXPLOSION_POWER*chargeTime), user, (p) -> {p.sendToolBreakStatus(user.getActiveHand());});
                }


            }
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target != null) {
            //as long as fire aspect
            target.setFireTicks(80);
            stack.damage(1, attacker, p -> p.sendToolBreakStatus(attacker.getActiveHand()));
        }
        return super.postHit(stack, target, attacker);
    }
}
