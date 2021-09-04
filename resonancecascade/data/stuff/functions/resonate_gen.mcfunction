##This file generates the platform that the player starts on in each dimension

#generate platform to stand on
execute at @a run fill -5 70 -5 5 79 5 minecraft:air
execute at @a run fill -5 69 -5 5 69 5 minecraft:grass_block replace

#generate the save block
execute at @a run setblock -5 70 -5 minecraft:structure_block{rotation:"NONE",mode:"SAVE",posY:-1,sizeX:11,posZ:0,name:"minecraft:base",sizeY:10,sizeZ:11}

#generate the load block and trigger it, thus removing it
execute at @a run setblock -5 71 -5 minecraft:structure_block{rotation:"NONE",mode:"LOAD",posY:-2,sizeX:11,posZ:0,posX:0,name:"minecraft:base"}
execute at @a run setblock -5 72 -5 minecraft:redstone_block replace

execute at @a run tp @a 0 70 0
execute at @a run spawnpoint @s 0 70 0


say Resonated
