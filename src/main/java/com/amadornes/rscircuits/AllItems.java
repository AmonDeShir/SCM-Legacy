package com.amadornes.rscircuits;

import com.amadornes.rscircuits.content.item.Screwdriver;
import com.amadornes.rscircuits.registrate.SCMRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.item.BlockItem;

public class AllItems {
    private static final SCMRegistrate REGISTRATE = SCM.registrate().itemGroup(() -> SCM.createTab);

    public static final ItemEntry<Screwdriver> SCREWDRIVER = REGISTRATE
            .item("screwdriver", Screwdriver::new)
            .register();

    //Block items
    public static final ItemEntry<BlockItem> CIRCUIT = REGISTRATE
            .item("circuit", (properties) -> new BlockItem(AllBlocks.CIRCUIT.get(), properties))
            .register();

    public static void register() {}
}
