package li.cil.architect.common.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public final class MessageBlueprintPlace implements IMessage {
    private EnumHand hand;
    private BlockPos pos;
    private boolean allowPartial;

    public MessageBlueprintPlace(final EnumHand hand, final BlockPos pos, final boolean allowPartial) {
        this.hand = hand;
        this.pos = pos;
        this.allowPartial = allowPartial;
    }

    @SuppressWarnings("unused") // For deserialization.
    public MessageBlueprintPlace() {
    }

    // --------------------------------------------------------------------- //

    public EnumHand getHand() {
        return hand;
    }

    public BlockPos getPos() {
        return pos;
    }

    public boolean allowPartial() {
        return allowPartial;
    }

    // --------------------------------------------------------------------- //
    // IMessage

    @Override
    public void fromBytes(final ByteBuf buf) {
        final PacketBuffer packet = new PacketBuffer(buf);
        hand = packet.readEnumValue(EnumHand.class);
        pos = packet.readBlockPos();
        allowPartial = packet.readBoolean();
    }

    @Override
    public void toBytes(final ByteBuf buf) {
        final PacketBuffer packet = new PacketBuffer(buf);
        packet.writeEnumValue(hand);
        packet.writeBlockPos(pos);
        packet.writeBoolean(allowPartial);
    }
}
