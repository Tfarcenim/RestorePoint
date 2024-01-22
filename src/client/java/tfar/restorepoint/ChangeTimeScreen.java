package tfar.restorepoint;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import tfar.restorepoint.network.C2SSetTimePacket;
import tfar.restorepoint.network.PacketHandler;

public class ChangeTimeScreen extends Screen {

    private static final ResourceLocation DEMO_BACKGROUND_LOCATION = new ResourceLocation("textures/gui/demo_background.png");

    protected int titleLabelX;
    protected int titleLabelY;

    protected ChangeTimeScreen(Component title) {
        super(title);
        imageWidth +=30;
        titleLabelX = 10;
        titleLabelY = 10;
    }

    protected int imageWidth = 176;
    protected int imageHeight = 166;

    @Override
    protected void init() {
        super.init();

        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;

        long daytime = minecraft.level.getDayTime() % 24000;

        addRenderableWidget(new FabricSlider(i + 38,j + 46,100,20,Component.empty(),Component.empty(),
                0,24000,(int)daytime,1,0,true){
            @Override
            public void onRelease(double mouseX, double mouseY) {
                super.onRelease(mouseX, mouseY);
                Minecraft.getInstance().level.setDayTime(getValueInt());
                ClientPacketHandler.sendToServer(new C2SSetTimePacket(this.getValueInt()), PacketHandler.time_of_day);
            }
        });
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderLabels(guiGraphics,mouseX,mouseY);
    }

    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {

        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;

        guiGraphics.drawString(this.font, this.title, this.titleLabelX + i, this.titleLabelY + j, 0x404040, false);
        //guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 0x404040, false);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        int i = (this.width - 248) / 2;
        int j = (this.height - 166) / 2;
        guiGraphics.blit(DEMO_BACKGROUND_LOCATION, i, j, 0, 0, 248, 166);
    }

}
