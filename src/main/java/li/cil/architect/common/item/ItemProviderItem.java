package li.cil.architect.common.item;

import li.cil.architect.common.config.Constants;
import li.cil.architect.common.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.List;

public final class ItemProviderItem extends AbstractProvider {
    /**
     * Get a list of all valid item handlers accessible via providers in the
     * specified inventory, in range of the specified position.
     *
     * @param consumerPos the position to base range checks on.
     * @param inventory   the inventory to get providers from.
     * @return the list of valid item handlers available.
     */
    public static List<IItemHandler> findProviders(final Vec3d consumerPos, final IItemHandler inventory) {
        return AbstractProvider.findProviders(consumerPos, inventory, Items::isItemProvider, ItemProviderItem::getItemHandlerCapability);
    }

    // --------------------------------------------------------------------- //
    // AbstractProvider

    @Override
    protected boolean isValidTarget(final TileEntity tileEntity, final EnumFacing side) {
        return tileEntity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
    }

    @Override
    protected String getTooltip() {
        return Constants.TOOLTIP_PROVIDER_ITEM;
    }

    // --------------------------------------------------------------------- //

    @Nullable
    private static IItemHandler getItemHandlerCapability(final ItemStack stack, final TileEntity tileEntity) {
        return tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, getSide(stack));
    }
}
