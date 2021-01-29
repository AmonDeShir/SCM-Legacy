package com.amadornes.rscircuits;

import com.amadornes.rscircuits.content.block.Circuit;
import com.amadornes.rscircuits.content.item.Screwdriver;
import com.amadornes.rscircuits.registrate.SCMRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;

public class AllItems {
    private static final SCMRegistrate REGISTRATE = SCM.registrate().itemGroup(() -> SCM.createTab);

    public static final ItemEntry<Screwdriver> SCREWDRIVER = REGISTRATE.item("screwdriver", Screwdriver::new).register();

    public static void register() {}
}
