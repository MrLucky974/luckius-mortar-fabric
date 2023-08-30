# Luckius' Mortar

A mod inspired by CT Mortar (1.12.x) for modern Minecraft versions.

# Usage
This mod is pretty simple and does nothing much by itself. 
It adds four **mortar & pestle** items. 

## Recipe
The recipe is as follows :

|     |     | S   |
|-----|-----|-----|
| #   | F   | #   |
|     | #   |     |

- \# : Wood Planks | Stone | Iron | Diamond
- F : Flint
- S : Stick

## Crafting
Crafting with the **mortar & pestle** is a bit different. To use it,
you can right-click with a desired item on the mortar.
The result will be added to your inventory or dropped on the floor.

**Note** : In creative, excess items (items that cannot fit in the storage part) will disappear.

# Datapacks

Recipes are data-driven which means you can use datapacks.

```json
{
  "type": "luckius_mortar:mortar",
  "ingredient": {
    "item": "minecraft:bone"
  },
  "result": "minecraft:bone_meal",
  "count": 3,
  "damage": 1 // (min:0)
}
```

Two recipes are added by default with this mod :
- Bone to (x3) Bone Meal
- Gravel to Flint
