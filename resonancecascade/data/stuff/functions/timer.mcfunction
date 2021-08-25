#scoreboard players add varTime timer 1
scoreboard players set @a timer 0
scoreboard players add @a timerEnd 1
scoreboard players add @a dimensionCount 0

#save platform before we do anything to the player just in case
execute at @a run setblock -5 69 -5 minecraft:redstone_block replace
execute as @a run function stuff:dimtele
execute at @a run setblock -5 69 -5 minecraft:grass_block replace


