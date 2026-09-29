 # World Cup Player Effectiveness Ratings

---
<br>
<br>

## Development Team

Business Client: Jose

Lead Developer: Joseph LeBlanc

Quality Control: Kay


<br>



## Description

This application allows users to enter a player from the FIFA 2026 World Cup tournament and their stats. It will take those stats and create a player effectiveness score for each player. The stats that will be tracked are:

Player Name

Country

Position

Minutes Played

Goals

Assists

Defensive Actions (Tackles + Blocks + Interceptions)

Yellow Cards

Red Cards

Player Effectiveness

A weight value must be given for goals, assists, defensive actions, yellow cards and red cards (as shown in the example calculation below). 

The number goals and assists entered by the user will be multiplied by their weighted value then added together. This value is then divided by the minutes played then multiplied by 90 to get a per-90-minute rate stat for offensive contributions. The same will be done for defensive contributions. 

The number of yellow and red cards entered by the user will be multiplied by their weighted value then added together. They will also be divided by the minutes played then multiplied by 90 to get the per-90-minute rate stat for penalties.

The sum of the offensive contributions, defensive contributions and penalties will be the player effectiveness score. 

<br>

## Color

Forest Green

<br>

 ## Required Fields



id			int	Unique identifier for database table

playerName		String	Player’s name

country			String	The country the player plays for

position		String	The position the player plays (DEF, MID, FWD)

minutesPlayed		int	Total minutes the player played in the tournament

goals			int	The number of goals the player scored in the tournament

assists			int	

defensiveActions	int	The sum of the player’s tackles, blocks and interceptions in the tournament

yellowCards		int	The number of yellow cards the player received

redCards		int	The number of red cards the player received

playerEffectiveness	Double	The calculated score of the players overall effectiveness for the tournament

<br>


## Calculation



**GOALS\_WEIGHT = 1.0**

**ASSISTS\_WEIGHT = 0.75**

**DEFENSE\_WEIGHT = 0.5**

**YELLOW\_CARDS\_WEIGHT = 0.5**

**RED\_CARDS\_WEIGHT = 2.0**



**double getOffensiveContribution = ((goals \* 1.0) + (assists \* 0.75) / minutesPlayed) \* 90**

**double getDefensiveContribution = ((defensiveActions)\*0.5) / minutesPlayed) \* 90**

**double getPenalties = ((yellowCards \* 0.5) + (redCards \* 2.0) / minutesPlayed) \* 90**



**playerEffectiveness = offense + defense – penalty**

<br>

## Repository

**https://github.com/Jleblanc10/cis2232\_f26\_project\_leblanc\_joseph\_world\_cup\_ratings/edit/main/README.md**

<br>

## Report Details

To be determined in future sprint.

