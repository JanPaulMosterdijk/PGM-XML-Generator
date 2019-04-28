# PGM XML Generator

## Commands
## /map
**/map mapname** *Sets the name of the map.*

**/map reset** *Resets all attributes of the map to null.*
 
**/map generate** *Generates the map.xml and saves it to XMLGenerator/uuid/mapname/map.xml*
 
 
## /region
**/region type filterType team/all** *Add a region specified by the 2 positions set with the stick. Region types are: "cuboid", "rectangle", and "cyllinder". Possible filters are: "block": for disallowing the specified team of placing blocks in the region, "void": for adding a void filter, allowing the players to bridge over void areas, and "enter": to stop the specified team of entering the specified region.*
 
## /objective
Not implemented yet.
 
## /team
**/team add/remove name** *Add or remove a team, a must have if you want to apply kits and regions to teams.*
 
## /spawn
**/spawn team** *adds a spawn element of the position the player is currently standing. Does not support custom specified regions, it just sets the spawn to a cyllinder with a radius of 2.*

## /kit
**/kit team** *Sets the kit of the specified team to the players inventory. Potion effects are not supported yet.*
	
	

	
