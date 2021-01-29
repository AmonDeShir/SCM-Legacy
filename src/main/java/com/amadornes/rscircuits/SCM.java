package com.amadornes.rscircuits;

import com.amadornes.rscircuits.content.tab.SCMCreativeTab;
import com.amadornes.rscircuits.registrate.SCMRegistrate;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.NonNullLazyValue;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(SCM.MOD_ID)
public class SCM {
    public static final String MOD_ID = "rscircuits";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final ItemGroup createTab = new SCMCreativeTab();

    private static final NonNullLazyValue<SCMRegistrate> registrate = SCMRegistrate.lazy(MOD_ID);

    public SCM() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        AllBlocks.register();
        AllItems.register();

        modEventBus.addListener(SCM::init);
    }

    private static void init(final FMLCommonSetupEvent event) {

    }

    public static SCMRegistrate registrate() {
        return registrate.get();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }


}
