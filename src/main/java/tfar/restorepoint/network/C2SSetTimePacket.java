package tfar.restorepoint.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class C2SSetTimePacket implements C2SModPacket {

    private int timeOfDay;

    public C2SSetTimePacket(int timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public C2SSetTimePacket(FriendlyByteBuf buf) {
        timeOfDay = buf.readInt();
    }

    @Override
    public void handleServer(ServerPlayer player) {
        player.serverLevel().setDayTime(timeOfDay);
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeInt(timeOfDay);
    }
}
