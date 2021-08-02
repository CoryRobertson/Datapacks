scoreboard objectives add kills minecraft.custom:minecraft.mob_kills

scoreboard objectives add testFirst dummy
execute unless score isFirst testFirst matches 1 run scoreboard players set isFirst testFirst 0
execute if score isFirst testFirst matches 0 run function stuff:firstload
scoreboard players set isFirst testFirst 1
worldborder damage amount 1

scoreboard objectives add magmacubes minecraft.killed:minecraft.magma_cube
scoreboard objectives add phantoms minecraft.killed:minecraft.phantom
scoreboard objectives add piglins minecraft.killed:minecraft.piglin
scoreboard objectives add piglinbrutes minecraft.killed:minecraft.piglin_brute
scoreboard objectives add pillagers minecraft.killed:minecraft.pillager
scoreboard objectives add ravagers minecraft.killed:minecraft.ravager
scoreboard objectives add shulkers minecraft.killed:minecraft.shulker
scoreboard objectives add silverfishes minecraft.killed:minecraft.silverfish
scoreboard objectives add skeletons minecraft.killed:minecraft.skeleton
scoreboard objectives add spiders minecraft.killed:minecraft.spider
scoreboard objectives add strays minecraft.killed:minecraft.stray
scoreboard objectives add vexes minecraft.killed:minecraft.vex
scoreboard objectives add vindicators minecraft.killed:minecraft.vindicator
scoreboard objectives add witches minecraft.killed:minecraft.witch
scoreboard objectives add withers minecraft.killed:minecraft.wither
scoreboard objectives add zoglins minecraft.killed:minecraft.zoglin
scoreboard objectives add zombies minecraft.killed:minecraft.zombie
scoreboard objectives add zombievillagers minecraft.killed:minecraft.zombie_villager
scoreboard objectives add zombifiedpiglins minecraft.killed:minecraft.zombified_piglin