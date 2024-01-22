package tfar.restorepoint;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import tfar.restorepoint.mixin.client.FabricSlider;

public class ChangeTimeScreen extends Screen {

    private static final ResourceLocation DEMO_BACKGROUND_LOCATION = new ResourceLocation("textures/gui/demo_background.png");

    protected ChangeTimeScreen(Component title) {
        super(title);
    }

    protected int imageWidth = 176;
    protected int imageHeight = 166;

    @Override
    protected void init() {
        super.init();

        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;

        addWidget(new FabricSlider(i + 38,j + 46,100,20,Component.empty(),Component.empty(),
                0,24000,120,1,0,true));
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        int i = (this.width - 248) / 2;
        int j = (this.height - 166) / 2;
        guiGraphics.blit(DEMO_BACKGROUND_LOCATION, i, j, 0, 0, 248, 166);
    }

}
