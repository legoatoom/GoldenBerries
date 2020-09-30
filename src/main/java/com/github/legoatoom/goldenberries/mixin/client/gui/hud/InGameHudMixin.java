package com.github.legoatoom.goldenberries.mixin.client.gui.hud;

import com.github.legoatoom.goldenberries.GoldenBerries;
import com.github.legoatoom.goldenberries.entity.effect.ModStatusEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    private static final Identifier MOD_GUI_ICONS_TEXTURE = new Identifier(GoldenBerries.MOD_ID,"textures/gui/icons.png");

    @Final
    @Shadow private MinecraftClient client;

    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @Inject(
            method = "renderStatusBars(Lnet/minecraft/client/util/math/MatrixStack;)V",
            at = @At(
                    value = "INVOKE_STRING",
                    target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V",
                    args = {"ldc=health"}
            )
    )
    private void healthStarts(MatrixStack matrices, CallbackInfo ci){
        PlayerEntity playerEntity = this.getCameraPlayer();
        if (playerEntity.hasStatusEffect(ModStatusEffects.POISON_RESISTANCE)){
            this.client.getTextureManager().bindTexture(MOD_GUI_ICONS_TEXTURE);
        }
    }

    @Inject(
            method = "renderStatusBars(Lnet/minecraft/client/util/math/MatrixStack;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;getRiddenEntity()Lnet/minecraft/entity/LivingEntity;"
            )
    )
    private void healthEnds(MatrixStack matrices, CallbackInfo ci){
        this.client.getTextureManager().bindTexture(DrawableHelper.GUI_ICONS_TEXTURE);
    }
}
