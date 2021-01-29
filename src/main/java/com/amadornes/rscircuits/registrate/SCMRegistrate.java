//Based on https://github.com/Creators-of-Create/Create/blob/d35bdab51a92999ef3aec49e2f7d2f36a85fc684/src/main/java/com/simibubi/create/foundation/data/CreateRegistrate.java
package com.amadornes.rscircuits.registrate;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.NonNullLazyValue;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class SCMRegistrate extends AbstractRegistrate<SCMRegistrate> {


    protected SCMRegistrate(String modid) {
        super(modid);
    }

    public static NonNullLazyValue<SCMRegistrate> lazy(String modid) {
        return new NonNullLazyValue<>(
                () -> new SCMRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get()
                        .getModEventBus()));
    }
}
