#scoreboard players add varTime timer 1
scoreboard players set @a timer 0
scoreboard players add @a timerEnd 1
scoreboard players add @a dimensionCount 0

#save platform before we do anything to the player just incase
execute at @a run setblock -5 69 -5 minecraft:redstone_block replace
execute as @a run function stuff:dimtele
execute at @a run setblock -5 69 -5 minecraft:grass_block replace
#execute as @a[scores={dimensionCount=0,timerEnd=..1}] in minecraft:overworld run function stuff:resonate
#execute as @a[scores={dimensionCount=1,timerEnd=3..}] in stuff:0 run function stuff:resonate
#execute as @a[scores={dimensionCount=2,timerEnd=10..}] in stuff:1 run function stuff:resonate
#execute as @a[scores={dimensionCount=3,timerEnd=15..}] in stuff:2 run function stuff:resonate
#execute as @a[scores={dimensionCount=4,timerEnd=17..}] in stuff:3 run function stuff:resonate
#execute as @a[scores={dimensionCount=5,timerEnd=20..}] in stuff:4 run function stuff:resonate
#execute as @a[scores={dimensionCount=6,timerEnd=22..}] in stuff:5 run function stuff:resonate
#execute as @a[scores={dimensionCount=6,timerEnd=23..}] in stuff:6 run function stuff:resonate
#execute as @a[scores={dimensionCount=7,timerEnd=30..}] in stuff:7 run function stuff:resonate
#execute as @a[scores={dimensionCount=9,timerEnd=34..}] in stuff:8 run function stuff:resonate


