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
	pass,
	passForkOne,
	passForkTwo,
	end,
	hallway,	// The in between 
	limbo		// Not on the map
}
