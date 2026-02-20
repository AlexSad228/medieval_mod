package net.alexsad.medieval_mod.item;

import net.alexsad.medieval_mod.MedievalMod;
import net.alexsad.medieval_mod.item.custom.FireCharmItem;
import net.alexsad.medieval_mod.item.custom.WaterCharmItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MedievalMod.MODID);

    public static final RegistryObject<Item> KATANA_BLADE = ITEMS.register("katana_blade",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KATANA = ITEMS.register("katana",
            () -> new SwordItem(Tiers.IRON, 3, (-(2.6f)), new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> RAPIER_BLADE = ITEMS.register("rapier_blade",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAPIER = ITEMS.register("rapier",
            () -> new SwordItem(Tiers.IRON, 1, (-(2.3f)), new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> BATTLEAXE = ITEMS.register("battleaxe",
            () -> new SwordItem(Tiers.IRON,7, (-(2.9f)), new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> DAGGER = ITEMS.register("dagger",
            () -> new SwordItem(Tiers.IRON, 0, (-(1.8f)), new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MACE_HEAD = ITEMS.register("mace_head",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MACE = ITEMS.register("mace",
            () -> new SwordItem(Tiers.IRON, 5, (-(3.1f)), new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SWORD_PART1 = ITEMS.register("giant_sword_bottom_part",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SWORD_PART2 = ITEMS.register("giant_sword_middle_part",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SWORD_PART3 = ITEMS.register("giant_sword_upper_part",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SWORD_BLADE = ITEMS.register("giant_sword_blade",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GIANT_SWORD = ITEMS.register("giant_sword",
            () -> new SwordItem(Tiers.IRON, 12, (-(3.7f)), new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> FIRE_CHARM = ITEMS.register("fire_charm",
            () -> new FireCharmItem(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> WATER_CHARM = ITEMS.register("water_charm",
            () -> new WaterCharmItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
