package tfar.restorepoint.mixin.client;

import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.restorepoint.RestorePointClient;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin extends Input {

    @Inject(method = "tick",at = @At("RETURN"))
    private void resetInputs(boolean isSneaking, float sneakingSpeedMultiplier, CallbackInfo ci) {
        if (RestorePointClient.inputTicksLeft > 0) {
            this.up = false;
            this.down = false;
            this.left = false;
            this.right = false;
            this.leftImpulse = 0;
            this.forwardImpulse = 0;
        }
    }
}
