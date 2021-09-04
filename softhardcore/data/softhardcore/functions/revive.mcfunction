kill @s

execute as @e[type=minecraft:item,nbt={Item:{id:"minecraft:diamond_block"}}] at @s run summon minecraft:lightning_bolt
kill @e[type=minecraft:item,nbt={Item:{id:"minecraft:diamond_block"}}]

scoreboard players set @s deaths 0
gamemode survival