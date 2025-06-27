package net.minestom.server.instance.block;

import net.minestom.server.coordinate.Point;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BlockMutation {

    @NotNull
    Block.Getter getInstance();

    @NotNull
    Point getBlockPosition();

    @NotNull
    Block getBlock();

    @Nullable
    BlockFace getBlockFace();

    void setBlock(@NotNull Block newBlock);

    interface InstanceMutation extends BlockMutation {
        static InstanceMutation of(@NotNull Block.Getter instance, @NotNull Point blockPosition,
                                   @NotNull Block newBlock, @Nullable BlockFace blockFace) {

            return new InstanceMutation() {
                private Block currentNewBlock = newBlock;

                @Override
                public @NotNull Block.Getter getInstance() { return instance; }

                @Override
                public @NotNull Point getBlockPosition() { return blockPosition; }

                @Override
                public @NotNull Block getBlock() { return currentNewBlock; }

                @Override
                public @Nullable BlockFace getBlockFace() { return blockFace; }

                @Override
                public void setBlock(@NotNull Block newBlock) {
                    this.currentNewBlock = newBlock;
                }
            };
        }
    }

    interface PlayerMutation extends BlockMutation {
        @NotNull Player getPlayer();
        @NotNull BlockFace getBlockFace();

        static PlayerMutation of(@NotNull Block.Getter instance, @NotNull Player player,
                                 @NotNull Point blockPosition, @NotNull Block newBlock,
                                 @NotNull BlockFace blockFace) {

            return new PlayerMutation() {
                private Block currentNewBlock = newBlock;

                @Override
                public @NotNull Block.Getter getInstance() { return instance; }

                @Override
                public @NotNull Player getPlayer() { return player; }

                @Override
                public @NotNull Point getBlockPosition() { return blockPosition; }

                @Override
                public @NotNull Block getBlock() { return currentNewBlock; }

                @Override
                public @NotNull BlockFace getBlockFace() { return blockFace; }

                @Override
                public void setBlock(@NotNull Block newBlock) { this.currentNewBlock = newBlock; }

            };
        }
    }
}