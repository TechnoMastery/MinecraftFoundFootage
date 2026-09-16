package net.minheur.networking.C2S;

import net.minheur.cca_stuff.InitializeComponents;
import net.minheur.cca_stuff.PlayerComponent;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class SeeActiveSkinwalkerSync {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){

        boolean canSeeSkinwalker = buf.readBoolean();

        server.execute(()->{
            PlayerComponent playerComponent = InitializeComponents.PLAYER.get(player);
            playerComponent.setCanSeeActiveSkinWalkerTarget(canSeeSkinwalker);
        });
    }
}
