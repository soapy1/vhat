package game.vhat;

/*
 * MAP:
 *                   +---------------+----------------------+---------+
 * 	+-------------+  |                   pass                         |
 * 	|			  |__|   challenge   +--------+  +----------+ pf two  |
 * 	| entrance     __      one       |        |  |          |         |
 *  |             |  |               |     +--+  +--+       +--|  |---+ 
 *  +-------------+  +---------------+     | pf one |           END  
 *                                         +--------+
 *                                         
 *  
 */

public enum location {
	entrance,
	challengeOne,
	hallway,
	pass,
	passForkOne,
	passForkTwo,
	end, 
	limbo		// Not on the map
}
