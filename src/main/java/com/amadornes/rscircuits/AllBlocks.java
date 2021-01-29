package com.amadornes.rscircuits;

import com.amadornes.rscircuits.content.block.Circuit;
import com.amadornes.rscircuits.registrate.SCMRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;

public class AllBlocks {
    private static final SCMRegistrate REGISTRATE = SCM.registrate().itemGroup(() -> SCM.createTab);

    public static final BlockEntry<Circuit> CIRCUIT = REGISTRATE.block("circuit", Circuit::new).register();

    public static void register() {}
}
