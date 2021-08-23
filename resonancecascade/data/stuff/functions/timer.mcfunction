#scoreboard players add varTime timer 1
scoreboard players set @a timer 0
scoreboard players add @a dimensionCount 1
execute as @a[scores={dimensionCount=0}] in minecraft:overworld run function stuff:resonate
execute as @a[scores={dimensionCount=1}] in stuff:0 run function stuff:resonate
execute as @a[scores={dimensionCount=2}] in stuff:1 run function stuff:resonate
execute as @a[scores={dimensionCount=3}] in stuff:2 run function stuff:resonate
execute as @a[scores={dimensionCount=4}] in stuff:3 run function stuff:resonate
execute as @a[scores={dimensionCount=5}] in stuff:4 run function stuff:resonate
execute as @a[scores={dimensionCount=6}] in stuff:5 run function stuff:resonate
execute as @a[scores={dimensionCount=6}] in stuff:6 run function stuff:resonate
execute as @a[scores={dimensionCount=7}] in stuff:7 run function stuff:resonate
execute as @a[scores={dimensionCount=9}] in stuff:8 run function stuff:resonate
execute as @a[scores={dimensionCount=10}] in stuff:9 run function stuff:resonate
execute as @a[scores={dimensionCount=11}] in stuff:10 run function stuff:resonate
execute as @a[scores={dimensionCount=12}] in stuff:11 run function stuff:resonate
execute as @a[scores={dimensionCount=13}] in stuff:12 run function stuff:resonate
execute as @a[scores={dimensionCount=14}] in stuff:13 run function stuff:resonate
execute as @a[scores={dimensionCount=15}] in stuff:14 run function stuff:resonate
execute as @a[scores={dimensionCount=16}] in stuff:15 run function stuff:resonate

