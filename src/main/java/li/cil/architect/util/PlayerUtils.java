package li.cil.architect.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public final class PlayerUtils {
    private static final int MIN_AIM_DISTANCE = 4;
    private static final int MAX_AIM_DISTANCE = 10;

    private static float freeAimDistance = MIN_AIM_DISTANCE;

    /**
     * Change the distance at which the free-aim pointer is positioned.
     *
     * @param delta the amount by which to change the distance.
     */
    public static void changeFreeAimDistance(final float delta) {
        freeAimDistance = MathHelper.clamp(freeAimDistance + delta, MIN_AIM_DISTANCE, MAX_AIM_DISTANCE);
    }

    public static BlockPos getLookAtPos(final EntityPlayer player) {
        final Vec3d lookVec = player.getLookVec();
        final Vec3d eyePos = player.getPositionEyes(1);
        return new BlockPos(eyePos.add(lookVec.scale(freeAimDistance)));
    }

    public static EnumFacing getPrimaryFacing(final EntityPlayer player) {
        final Vec3d lookVec = player.getLookVec();
        final double absX = Math.abs(lookVec.xCoord);
        final double absY = Math.abs(lookVec.yCoord);
        final double absZ = Math.abs(lookVec.zCoord);

        if (absX > absY && absX > absZ) {
            if (lookVec.xCoord > 0) {
                return EnumFacing.EAST;
            } else {
                return EnumFacing.WEST;
            }
        } else if (absY > absZ) {
            if (lookVec.yCoord > 0) {
                return EnumFacing.UP;
            } else {
                return EnumFacing.DOWN;
            }
        } else {
            if (lookVec.zCoord > 0) {
                return EnumFacing.SOUTH;
            } else {
                return EnumFacing.NORTH;
            }
        }
    }

    private PlayerUtils() {
    }
}
