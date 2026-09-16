package net.minheur.networking.C2S;

import net.minheur.cca_stuff.InitializeComponents;
import net.minheur.cca_stuff.PlayerComponent;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class SyncServerComponent {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
        boolean readBoolean = buf.readBoolean();
        String component = buf.readString();

        server.execute(()->{
            PlayerComponent playerComponent = InitializeComponents.PLAYER.get(player);

            switch (component) {
                case "beingCaptured": playerComponent.setBeingCaptured(readBoolean); break;
                case "cutscene": playerComponent.setDoingCutscene(readBoolean); break;
                case "flashlight": playerComponent.setFlashLightOn(readBoolean); break;
                case "glitch": playerComponent.setShouldInflictGlitchDamage(readBoolean); break;
                case "teleporting": playerComponent.setTeleporting(readBoolean); break;
            }

            playerComponent.sync();
        });
    }

}
