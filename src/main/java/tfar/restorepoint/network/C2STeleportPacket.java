package tfar.restorepoint.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class C2STeleportPacket implements C2SModPacket {


    private final Vec3 pos;
    private final float pitch;//xRot
    private final float yaw;//yRot

    public C2STeleportPacket(Vec3 pos, float pitch, float yaw) {
        this.pos = pos;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public C2STeleportPacket(FriendlyByteBuf buf) {
        pos = buf.readVec3();
        pitch = buf.readFloat();
        yaw = buf.readFloat();
    }

    @Override
    public void handleServer(ServerPlayer player) {
        player.teleportTo(player.serverLevel(),pos.x,pos.y,pos.z,pitch,yaw);
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeVec3(pos);
        buf.writeFloat(pitch);
        buf.writeFloat(yaw);
    }
}
