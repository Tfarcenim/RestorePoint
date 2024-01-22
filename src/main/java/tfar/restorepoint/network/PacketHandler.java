package tfar.restorepoint.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;
import tfar.restorepoint.RestorePoint;

public class PacketHandler {

    public static final ResourceLocation teleport = new ResourceLocation(RestorePoint.MODID,"teleport");
    public static final ResourceLocation time_of_day = new ResourceLocation(RestorePoint.MODID,"time_of_day");
    public static void registerMessages() {
        ServerPlayNetworking.registerGlobalReceiver(teleport,new ServerHandler<>(C2STeleportPacket::new));
        ServerPlayNetworking.registerGlobalReceiver(time_of_day,new ServerHandler<>(C2SSetTimePacket::new));
    }
}
