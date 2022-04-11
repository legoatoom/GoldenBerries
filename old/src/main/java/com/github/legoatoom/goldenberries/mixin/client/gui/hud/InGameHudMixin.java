/*
 * This file is part of GoldenBerries.
 *
 * GoldenBerries is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GoldenBerries is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GoldenBerries.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.legoatoom.goldenberries.mixin.client.gui.hud;

import com.github.legoatoom.goldenberries.GoldenBerries;
import com.github.legoatoom.goldenberries.entity.effect.ModStatusEffects;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.minecraft.client.gui.hud.InGameHud.HeartType.*;

/**
 * Mixin class for the {@link InGameHud} that creates the blue hearts when the user has the potion effect on.
 * This class switches what texture the renderer should use to render the hearts.
 * Because I can't directly locate where the code should begin and stop I had to use two injects to find a start and
 * endpoint to turn off the texture and replace the original texture.
 *
 * @author legoatoom
 */
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {

    private static final Identifier MOD_GUI_ICONS_TEXTURE = new Identifier(GoldenBerries.MOD_ID,"textures/gui/icons.png");


    private int heartX;
    private int heartY;
    private int heartIndex;

    @Shadow protected abstract void drawHeart(MatrixStack matrices, InGameHud.HeartType type, int x, int y, int v, boolean blinking, boolean halfHeart);

    @Inject(method = "renderHealthBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawHeart(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/gui/hud/InGameHud$HeartType;IIIZZ)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private void storeLocals(MatrixStack matrices, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci, InGameHud.HeartType type, int i, int j, int k, int l, int m, int n, int o, int p, int q) {
        this.heartX = p;
        this.heartY = q;
        this.heartIndex = m;
    }

    @Inject(method = "renderHealthBar", at =
    @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/hud/InGameHud;drawHeart(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/gui/hud/InGameHud$HeartType;IIIZZ)V",
            ordinal = 3,
            shift = At.Shift.BY,
            by = 2))
    private void renderOverlay(MatrixStack matrices, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        if (player.hasStatusEffect(ModStatusEffects.POISON_RESISTANCE)){
            RenderSystem.setShaderTexture(0, MOD_GUI_ICONS_TEXTURE);
            drawResistanceHearts(matrices, player, lastHealth, health, blinking);
            RenderSystem.setShaderTexture(0, GUI_ICONS_TEXTURE);
        }
    }

    /**
     * Does all the drawing of the hearts.
     * @see InGameHud#renderHealthBar(MatrixStack, PlayerEntity, int, int, int, int, float, int, int, int, boolean)
     */
    private void drawResistanceHearts(MatrixStack matrices, PlayerEntity player, int lastHealth, int health, boolean blinking) {
        InGameHud.HeartType heartType = player.hasStatusEffect(StatusEffects.POISON) ? POISIONED : (player.hasStatusEffect(StatusEffects.WITHER) ? WITHERED : (player.isFreezing() ? FROZEN : NORMAL));
        int i = 9 * (player.world.getLevelProperties().isHardcore() ? 5 : 0);
        int heartIndex2 = heartIndex * 2;
        boolean halfHeart;
        if (blinking && heartIndex2 < health) {
            halfHeart = heartIndex2 + 1 == health;
            this.drawHeart(matrices, heartType, heartX, heartY, i, true, halfHeart);
        }
        if (heartIndex2 < lastHealth) {
            halfHeart = heartIndex2 + 1 == lastHealth;
            this.drawHeart(matrices, heartType, heartX, heartY, i, blinking, halfHeart);
        }
    }
}
