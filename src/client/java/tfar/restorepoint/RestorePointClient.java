package tfar.restorepoint;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class RestorePointClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		UseItemCallback.EVENT.register(this::interact);
	}

	InteractionResultHolder<ItemStack> interact(Player player, Level world, InteractionHand hand) {
		if (player.getItemInHand(hand).getItem() == Items.STICK) {
			Minecraft.getInstance().setScreen(new ChangeTimeScreen(Component.empty()));
			return InteractionResultHolder.success(player.getItemInHand(hand));
		}
		return InteractionResultHolder.pass(ItemStack.EMPTY);
	}


}