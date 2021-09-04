#scoreboard players add varTime timer 1
scoreboard players set @s timer 0
scoreboard players add @s timerEnd 1
scoreboard players add @s dimensionCount 0

#save platform before we do anything to the player just in case
execute at @s run setblock -5 69 -5 minecraft:redstone_block replace
execute as @s run function stuff:dimtele
execute at @s run setblock -5 69 -5 minecraft:grass_block replace
