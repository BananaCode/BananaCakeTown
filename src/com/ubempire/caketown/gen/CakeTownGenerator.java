package com.ubempire.caketown.gen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import com.ubempire.caketown.pops.MetaPopulator;

/**
 * @author codename_B
 */

public class CakeTownGenerator extends ChunkGenerator{
	@Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.<BlockPopulator>asList(new MetaPopulator());
    }
	
	public int xyzToByte(int x, int y, int z) {
	    return (x * 16 + z) * 128 + y;
	    }
	@Override
	public byte[] generate(World world, Random random, int chunkX, int chunkZ) {
		//System.out.println(chunkX+","+chunkZ);
		
		int height = 128;
		int base = 32;
		byte stone = (byte) Material.STONE.getId();
		byte glass = (byte) Material.GLASS.getId();
		byte grass = (byte) Material.GRASS.getId();
		byte bedrock = (byte) Material.BEDROCK.getId();
		byte[] b = new byte[272 * height];
		int top = 64+base;
		Location center = new Location(world, 0, base, 0);
		for(int x=0; x<16; x++){
			for(int z=0; z<16; z++){
				for(int y=base; y<=top; y++){
					int realX = chunkX*16+x;
					int realZ = chunkZ*16+z;
					Location here = new Location(world, realX, y, realZ);
					double dist = here.distance(center);
					if(dist<100)
					{
					if(y <= base+1 && y>=base)
					b[xyzToByte(x,y,z)]=bedrock;
					else if(y==top)
					b[xyzToByte(x,y,z)]=grass;
					else
					b[xyzToByte(x,y,z)]=stone;
					}
					else if(dist<102)
					{
						if(y <= base+1 && y>=base)
						b[xyzToByte(x,y,z)]=bedrock;
						else
						b[xyzToByte(x,y,z)]=grass;
				
					}
					
				}
			}
		}
		
			
		
		return b;
	}

	@Override
	public boolean canSpawn(World world, int x, int z) {
		return true;
	}

	@Override
	public Location getFixedSpawnLocation(World world, Random random) {
		while (true) {
			int x = random.nextInt(128) - 64;
			int y = 128 * 3 / 4;
			int z = random.nextInt(128) - 64;

			if (world.getBlockAt(x, y, z).isEmpty()) {
				while (world.getBlockAt(x, y - 1, z).isEmpty() && y > 0) {
					y--;
				}
				return new Location(world, x, y, z);
			}
		}
	}
}
