package net.minestom.demo.block.placement;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.BlockMutation;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * https://gist.github.com/mworzala/0676c28343310458834d70ed29b11a37
 */
public class BedPlacementRule extends BlockPlacementRule {


    private static final String PROP_PART = "part";
    private static final String PROP_FACING = "facing";

    public BedPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @NotNull BlockMutation blockPlace(@NotNull BlockMutation mutation) {
        if( !(mutation instanceof BlockMutation.Player mut)) {
            return mutation; // not a player placement
        }
        var playerPosition = mut.player().getPosition();
        var facing = BlockFace.fromYaw(playerPosition.yaw());

        //todo bad code using instance directly
        if (!(mut.instance() instanceof Instance instance)) return mut;

        var headPosition = mutation.blockPosition().relative(facing);
        if (!instance.getBlock(headPosition, Block.Getter.Condition.TYPE).isAir())
            return mut;

        var headBlock = this.block.withProperty(PROP_PART, "head")
                .withProperty(PROP_FACING, facing.name().toLowerCase());
        instance.setBlock(headPosition, headBlock);

        return mut.setBlock(mut.block().withProperty(PROP_PART, "foot").withProperty(PROP_FACING, facing.name().toLowerCase()));
    }
}
