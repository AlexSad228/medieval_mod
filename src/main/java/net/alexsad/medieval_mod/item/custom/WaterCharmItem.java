package net.alexsad.medieval_mod.item.custom;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class WaterCharmItem extends Item {
    public WaterCharmItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.medieval_mod.water_charm.tooltip"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(level, player, hand);
        ItemStack itemstack = ar.getObject();
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        WaterCharmProcedure.execute(level, x, y, z, player);
        return ar;
    }
    public class WaterCharmProcedure {
        public static void execute(LevelAccessor level, double x, double y, double z, Entity player) {
            if (player == null)
                return;
            if ((player instanceof Player _plr ? _plr.experienceLevel : 0) >= 5) {
                if ((!level.getLevelData().isRaining()) && !level.getLevelData().isThundering()) {
                    if (level instanceof ServerLevel _level)
                        _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
                                "weather rain");
                    if (player instanceof Player _player)
                        _player.giveExperienceLevels(-(5));
                } else {
                    if (player instanceof Player _player && !_player.level().isClientSide())
                        _player.displayClientMessage(Component.literal((Component.translatable("item.medieval_mod.water_charm.raining").getString())), true);
                }
            } else {
                if (player instanceof Player _player && !_player.level().isClientSide())
                    _player.displayClientMessage(Component.literal((Component.translatable("item.medieval_mod.water_charm.low_level").getString())), true);
            }
        }
    }
}
