package tfar.restorepoint;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import tfar.restorepoint.mixin.client.FabricSlider;

public class ChangeTimeScreen extends Screen {
    protected ChangeTimeScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        addWidget(new FabricSlider(1,1,1,1,Component.empty(),Component.empty(),0,1,1,0,0,true));
    }
}
