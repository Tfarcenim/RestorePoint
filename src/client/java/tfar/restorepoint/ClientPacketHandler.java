package tfar.restorepoint;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import tfar.restorepoint.network.C2SModPacket;

public class ClientPacketHandler {
    public static void sendToServer(C2SModPacket msg, ResourceLocation channel) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        msg.write(buf);
        ClientPlayNetworking.send(channel, buf);
    }
}
