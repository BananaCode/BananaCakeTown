package com.ubempire.caketown.pops;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

/**
 * BlockPopulator that generates stone quarries.
 */
public class QuarryPopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (random.nextInt(100) <= 95) {
            return;
        }
        if(world.getHighestBlockYAt(chunk.getX() * 16 + 8, chunk.getZ() * 16 + 8) <5)
        return;
        Block block = chunk.getBlock(8, world.getHighestBlockYAt(chunk.getX() * 16 + 8, chunk.getZ() * 16 + 8)-3, 8);
        int sizeX = 5 + random.nextInt(6);
        int sizeY = 5 + random.nextInt(6);
        int sizeZ = 3 + random.nextInt(7);

        for (int y = 0; y <= sizeZ + 1; y++) {
            sizeX = sizeX - y;
            sizeY = sizeY - y;
            for (int x = -sizeX; x <= sizeX; x++) {
                for (int z = -sizeY; z <= sizeY; z++) {
                    Block block2 = block.getRelative(x, -y - 1, z);
                    if (block2.getTypeId() != 0 && (block2.getTypeId() < 8 || block2.getTypeId() > 11)) {
                        if (random.nextBoolean()) {
                            block2.setType(Material.COBBLESTONE);
                        } else {
                            block2.setType(Material.GRAVEL);
                        }
                    }
                    
                    if (random.nextBoolean()) {
                    	if (!block.getRelative(x, -y, z).isLiquid())
                    		block.getRelative(x, -y, z).setType(Material.AIR);
                    }
                }
            }
        }
    }

}
