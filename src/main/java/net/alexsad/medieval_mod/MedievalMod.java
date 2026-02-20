package net.alexsad.medieval_mod;

import com.mojang.logging.LogUtils;
import net.alexsad.medieval_mod.item.ModCreativeModeTab;
import net.alexsad.medieval_mod.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;


@Mod(MedievalMod.MODID)
public class MedievalMod
{

    public static final String MODID = "medieval_mod";

    private static final Logger LOGGER = LogUtils.getLogger();


    public MedievalMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);

        ModCreativeModeTab.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static class WorldVariables extends SavedData {
        public static final String DATA_NAME = "medieval_mod_worldvars";
        public double time = 0;

        public static WorldVariables load(CompoundTag tag) {
            WorldVariables data = new WorldVariables();
            data.read(tag);
            return data;
        }

        public void read(CompoundTag nbt) {
            time = nbt.getDouble("time");
        }

        @Override
        public CompoundTag save(CompoundTag nbt) {
            nbt.putDouble("time", time);
            return nbt;
        }

        public void syncData(LevelAccessor level) {
            this.setDirty();
            if (level instanceof Level level1 && !level1.isClientSide())
                MedievalMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level1::dimension), new SavedDataSyncMessage(1, this));
        }

        static WorldVariables clientSide = new WorldVariables();

        public static WorldVariables get(LevelAccessor world) {
            if (world instanceof ServerLevel level) {
                return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
            } else {
                return clientSide;
            }
        }
    }

    public static class MapVariables extends SavedData {
        public static final String DATA_NAME = "medieval_mod_mapvars";

        public static MapVariables load(CompoundTag tag) {
            MapVariables data = new MapVariables();
            data.read(tag);
            return data;
        }

        public void read(CompoundTag nbt) {
        }

        @Override
        public CompoundTag save(CompoundTag nbt) {
            return nbt;
        }
    }

    public static class SavedDataSyncMessage {
        public int type;
        public SavedData data;

        public SavedDataSyncMessage(FriendlyByteBuf buffer) {
            this.type = buffer.readInt();
            this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
            if (this.data instanceof MapVariables _mapvars)
                _mapvars.read(buffer.readNbt());
            else if (this.data instanceof WorldVariables _worldvars)
                _worldvars.read(buffer.readNbt());
        }

        public SavedDataSyncMessage(int type, SavedData data) {
            this.type = type;
            this.data = data;
        }
    }
}
