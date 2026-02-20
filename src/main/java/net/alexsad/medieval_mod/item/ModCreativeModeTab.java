package net.alexsad.medieval_mod.item;

import net.alexsad.medieval_mod.MedievalMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MedievalMod.MODID);

    public static final RegistryObject<CreativeModeTab> MEDIEVAL_MOD_TAB = CREATIVE_MODE_TABS.register("medieval_mod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BATTLEAXE.get()))
                    .title(Component.translatable("itemGroup.medieval_mod_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.KATANA_BLADE.get());
                        pOutput.accept(ModItems.KATANA.get());
                        pOutput.accept(ModItems.RAPIER_BLADE.get());
                        pOutput.accept(ModItems.RAPIER.get());
                        pOutput.accept(ModItems.DAGGER.get());
                        pOutput.accept(ModItems.BATTLEAXE.get());
                        pOutput.accept(ModItems.MACE_HEAD.get());
                        pOutput.accept(ModItems.MACE.get());
                        pOutput.accept(ModItems.SWORD_PART1.get());
                        pOutput.accept(ModItems.SWORD_PART2.get());
                        pOutput.accept(ModItems.SWORD_PART3.get());
                        pOutput.accept(ModItems.SWORD_BLADE.get());
                        pOutput.accept(ModItems.GIANT_SWORD.get());
                        pOutput.accept(ModItems.FIRE_CHARM.get());
                        pOutput.accept(ModItems.WATER_CHARM.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
