package tfar.restorepoint.mixin.client;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.restorepoint.RestorePointClient;

@Mixin(GameRenderer.class)
public class ExampleClientMixin {
	@Inject(at = @At("HEAD"), method = "getFov", cancellable = true)
	private void run(Camera activeRenderInfo, float partialTicks, boolean useFOVSetting, CallbackInfoReturnable<Double> cir) {
		if (RestorePointClient.FOVTicksLeft > 0) {
			cir.setReturnValue(RestorePointClient.forceFOV);
		}
	}
}