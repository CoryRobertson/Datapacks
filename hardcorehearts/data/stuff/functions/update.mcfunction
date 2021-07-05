execute as @a[scores={hh_deaths=..0}] at @s run attribute @s minecraft:generic.max_health base set 20
execute as @a[scores={hh_deaths=1}] at @s run attribute @s minecraft:generic.max_health base set 18
execute as @a[scores={hh_deaths=2}] at @s run attribute @s minecraft:generic.max_health base set 16
execute as @a[scores={hh_deaths=3}] at @s run attribute @s minecraft:generic.max_health base set 14
execute as @a[scores={hh_deaths=4}] at @s run attribute @s minecraft:generic.max_health base set 12
execute as @a[scores={hh_deaths=5}] at @s run attribute @s minecraft:generic.max_health base set 10
execute as @a[scores={hh_deaths=6}] at @s run attribute @s minecraft:generic.max_health base set 8
execute as @a[scores={hh_deaths=7}] at @s run attribute @s minecraft:generic.max_health base set 6
execute as @a[scores={hh_deaths=8}] at @s run attribute @s minecraft:generic.max_health base set 4
execute as @a[scores={hh_deaths=9}] at @s run attribute @s minecraft:generic.max_health base set 2
execute as @a[scores={hh_deaths=10..}] at @s run attribute @s minecraft:generic.max_health base set 0

execute as @a[scores={hh_deaths=1..}] at @s run scoreboard players add @s hh_ticks 1

execute as @a[scores={hh_ticks=24000..}] at @s run function stuff:revive