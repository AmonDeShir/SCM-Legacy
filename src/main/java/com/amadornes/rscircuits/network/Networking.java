package com.amadornes.rscircuits.network;

import com.amadornes.rscircuits.SCM;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {
    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    public  static int nextID() {
        return  ID++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(SCM.asResource(SCM.MOD_ID), () -> "1.0", s -> true, s -> true);

      /*  INSTANCE.registerMessage(nextID(),
                PacketCircuit.CursorMove.class,
                PacketCircuit.CursorMove::toBytes,
                PacketCircuit.CursorMove::new,
                PacketCircuit::handle);
       */
    }
}
