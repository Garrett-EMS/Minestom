package net.minestom.demo.block;

import net.kyori.adventure.key.Key;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.instance.block.BlockMutation;
import org.jetbrains.annotations.NotNull;

public class TestBlockHandler implements BlockHandler {
    public static final BlockHandler INSTANCE = new TestBlockHandler();

    @Override
    public @NotNull Key getKey() {
        return Key.key("minestom", "test");
    }

    @Override
    public @NotNull BlockMutation onPlace(@NotNull BlockMutation mutation) {
        return mutation.setBlock(Block.DIAMOND_BLOCK);
    }

    @Override
    public @NotNull BlockMutation onDestroy(@NotNull BlockMutation mutation) {
        return mutation.setBlock(Block.EMERALD_BLOCK);
    }
}
