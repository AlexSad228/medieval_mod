package net.alexsad.medieval_mod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab MEDIEVAL_MOD_TAB = new CreativeModeTab("medieval_mod_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BATTLEAXE.get());
        }
    };
}
