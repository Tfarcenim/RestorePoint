package tfar.restorepoint;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.impl.client.screen.ScreenExtensions;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.glfw.GLFW;
import tfar.restorepoint.network.C2STeleportPacket;
import tfar.restorepoint.network.PacketHandler;

public class RestorePointClient implements ClientModInitializer {

	public static final KeyMapping save = new KeyMapping("save", GLFW.GLFW_MOUSE_BUTTON_4,RestorePoint.MODID);
	public static final KeyMapping load = new KeyMapping("load", GLFW.GLFW_MOUSE_BUTTON_5,RestorePoint.MODID);


	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	//	UseItemCallback.EVENT.register(this::interact);
		KeyBindingHelper.registerKeyBinding(save);
		KeyBindingHelper.registerKeyBinding(load);
		ClientTickEvents.START_CLIENT_TICK.register(this::clientTick);
		ScreenEvents.AFTER_INIT.register(this::initScreen);
	}

	private void initScreen(Minecraft minecraft, Screen screen, int i, int i1) {
		if (screen instanceof OptionsScreen optionsScreen) {
			Screens.getButtons(optionsScreen).add(new Button.Builder(Component.literal("T"),
					button -> Minecraft.getInstance().setScreen(new ChangeTimeScreen(Component.literal("Set time of day:"))))
					.size(20,20).pos(screen.width / 2 - 180,30)
					.build());
		}
	}

	private void clientTick(Minecraft minecraft) {
		Player player = minecraft.player;
		if (player != null) {
			while (save.consumeClick()) {
				position = player.getPosition(0);
				pitch = player.getYRot();
				yaw = player.getXRot();
			}

			while (load.consumeClick()) {
				if (position != null) {
					teleportPlayer();
				}
			}
		}
	}

	void teleportPlayer() {
		ClientPacketHandler.sendToServer(new C2STeleportPacket(position,pitch,yaw), PacketHandler.teleport);
	}


	InteractionResultHolder<ItemStack> interact(Player player, Level world, InteractionHand hand) {
		if (player.getItemInHand(hand).getItem() == Items.STICK) {
			if (player.level().isClientSide) {
				Minecraft.getInstance().setScreen(new ChangeTimeScreen(Component.literal("Set time of day:")));
			}
			return InteractionResultHolder.success(player.getItemInHand(hand));
		}
		return InteractionResultHolder.pass(ItemStack.EMPTY);
	}

	public static Vec3 position;

	public static float pitch;
	public static float yaw;

}