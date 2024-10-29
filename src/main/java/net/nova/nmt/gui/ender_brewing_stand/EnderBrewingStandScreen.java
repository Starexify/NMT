package net.nova.nmt.gui.ender_brewing_stand;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.nova.nmt.NoMoreThings;

public class EnderBrewingStandScreen extends AbstractContainerScreen<EnderBrewingStandMenu> {
    private static final ResourceLocation FUEL_LENGTH_SPRITE = NoMoreThings.rl("container/ender_brewing_stand/fuel_length");
    private static final ResourceLocation BREW_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/brewing_stand/brew_progress");
    private static final ResourceLocation BUBBLES_SPRITE = ResourceLocation.withDefaultNamespace("container/brewing_stand/bubbles");
    private static final ResourceLocation BREWING_STAND_LOCATION = NoMoreThings.rl("textures/gui/container/ender_brewing_stand.png");
    private static final int[] BUBBLELENGTHS = new int[]{29, 24, 20, 16, 11, 6, 0};

    public EnderBrewingStandScreen(EnderBrewingStandMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(BREWING_STAND_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
        int k = this.menu.getFuel();
        int l = Mth.clamp((18 * k + 20 - 1) / 20, 0, 18);
        if (l > 0) {
            guiGraphics.blitSprite(FUEL_LENGTH_SPRITE, 18, 4, 0, 0, i + 60, j + 44, l, 4);
        }

        int i1 = this.menu.getBrewingTicks();
        if (i1 > 0) {
            int j1 = (int)(28.0F * (1.0F - (float)i1 / 400.0F));
            if (j1 > 0) {
                guiGraphics.blitSprite(BREW_PROGRESS_SPRITE, 9, 28, 0, 0, i + 97, j + 16, 9, j1);
            }

            j1 = BUBBLELENGTHS[i1 / 2 % 7];
            if (j1 > 0) {
                guiGraphics.blitSprite(BUBBLES_SPRITE, 12, 29, 0, 29 - j1, i + 63, j + 14 + 29 - j1, 12, j1);
            }
        }
    }
}
