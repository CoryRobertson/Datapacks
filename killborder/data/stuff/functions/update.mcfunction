
#execute as @a[scores={kills=10..}] at @s run function stuff:gotkills
execute as @a[scores={kills=10..}] if score @s kills > $killborder kills run function stuff:gotkills