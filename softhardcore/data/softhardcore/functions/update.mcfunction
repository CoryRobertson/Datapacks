
execute as @a[scores={deaths=3..}] at @s run function softhardcore:died

execute as @a[scores={deaths=3..}] at @s if entity @e[type=minecraft:item,distance=..1,nbt={Item:{id:"minecraft:diamond_block"}}] run function softhardcore:revive

##execute as @a[scores={deaths=3..}] at @s if entity @e[type=minecraft:item,distance=..5] run function stuff:revive
##execute as @e[type=minecraft:item] at @s if entity @e[type=minecraft:player,distance=..5,scores=[deaths=3..]] run function stuff:revive