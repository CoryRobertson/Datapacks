##This file prepares the player and teleports the player to the next dimension
scoreboard players add @s dimensionCount 1
tp @s 0 70 0

#give the player effects incase they spawn in lava, or take fall damage, we also heal them just incase :)
effect give @a regeneration 20 10 false
effect give @a fire_resistance 20 10 false
effect give @a resistance 20 10 false

#schedule the command to place the blocks so the game has 5 seconds to catch up before placing blocks (setblock fails when the area isnt fully loaded as far as I have tested)
execute at @s run schedule function stuff:resonate_gen 5s

# {command:"function stuff:resonate"}
# {rotation:"NONE",mode:"SAVE",posY:-1,sizeX:11,posZ:0,name:"minecraft:base",sizeY:6,sizeZ:11}
# {rotation:"NONE",mode:"LOAD",posY:-2,sizeX:11,posZ:0,posX:0,name:"minecraft:base",powered:1b}