package net.minestom.server.instance.block;

import net.minestom.server.coordinate.Point;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public sealed interface BlockMutation {

    @NotNull
    Block.Getter instance();

    @NotNull
    Point blockPosition();

    @NotNull
    Block block();

    @Nullable
    BlockFace blockFace();

    BlockMutation withBlock(@NotNull Block newBlock);

    record Instance(
        @NotNull Block.Getter instance, @NotNull Point blockPosition,
        @NotNull Block block, @Nullable BlockFace blockFace
    ) implements BlockMutation {

        @Override
        public BlockMutation withBlock(@NotNull Block newBlock) {
            return new Instance(instance, blockPosition, newBlock, blockFace);
        }
    }

    record Player(
        @NotNull Block.Getter instance, @NotNull Point blockPosition,
        @NotNull Block block, @NotNull BlockFace blockFace,
        @NotNull net.minestom.server.entity.Player player
    ) implements BlockMutation {

        public @NotNull net.minestom.server.entity.Player player() {
            return player;
        }

        @Override
        public BlockMutation withBlock(@NotNull Block newBlock) {
            return new Player(instance, blockPosition, newBlock, blockFace, player);
        }
    }
}