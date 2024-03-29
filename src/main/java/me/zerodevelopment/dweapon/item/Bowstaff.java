package me.zerodevelopment.dweapon.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Bowstaff extends SwordItem {

    public static double forwardJumpSpeed = .7d;
    public static double upJumpSpeed = .6d;
    public static int COOL_DOWN = 26;

    public Bowstaff(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user != null && !user.getItemCooldownManager().isCoolingDown(this)) {
            if (world.isClient) {
                Vec3d look = Vec3d.fromPolar(user.getPitch(), user.getYaw());
                look = look.subtract(0, look.y, 0);
                double distance = look.length();
                if (distance != 0) {
                    look = look.multiply(1d/distance).multiply(forwardJumpSpeed).add(0, upJumpSpeed, 0);
                }else {
                    look = new Vec3d(0, forwardJumpSpeed, 0);
                }
                user.swingHand(user.getActiveHand());
                user.addVelocity(look);
                /* removed ground check for now
                if (user.isOnGround()) {

                }

                 */
            }else {
                ItemStack itemStack = user.getMainHandStack();
                itemStack.damage(1, user, (p) -> p.sendToolBreakStatus(user.getActiveHand()));
                user.getItemCooldownManager().set(this, COOL_DOWN);

                /* removed ground check for now
                if (user.isOnGround()) {

                }
                 */
            }
        }
        return super.use(world, user, hand);
    }
}
