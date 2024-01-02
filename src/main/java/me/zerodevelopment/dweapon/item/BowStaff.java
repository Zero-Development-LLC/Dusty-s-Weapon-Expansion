package me.zerodevelopment.dweapon.item;

import me.zerodevelopment.dweapon.Manager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BowStaff extends SwordItem {

    public static double forwardJumpSpeed = .7d;
    public static double upJumpSpeed = 0.6d;

    public BowStaff(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            if (user.isOnGround()) {
                Vec3d look = Vec3d.fromPolar(user.getPitch(), user.getYaw());
                look = look.subtract(0, look.y, 0);
                double distance = look.length();
                if (distance != 0) {
                    look = look.multiply(1d/distance).multiply(forwardJumpSpeed).add(0, upJumpSpeed, 0);
                }else {
                    look = new Vec3d(0, forwardJumpSpeed, 0);
                }

                user.addVelocity(look);
            }
        }else {
            if (user.isOnGround()) {
                ItemStack itemStack = user.getMainHandStack();
                itemStack.damage(1, user, playerEntity -> {});
            }
        }
        return super.use(world, user, hand);
    }
}
