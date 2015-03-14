# Introduction #

Pokkare is a J2EE program that utilizes Apache Struts 2 as the MVC web framework & Hibernate as the database framework. The database is MySQL and the program is run on an Apache Tomcat server.

Pokkare is meant to be used in maintaining and viewing scores from home poker games. Scores are assigned per tournament according to the players rank in tournament. Players, games, and scores (ranks) must be saved and viewable in a comprehensive form.

# Requirements #

## Add new games ##
  1. New games shall be addable.
  1. A game has a host (one of the players), a date and a description.
  1. To keep score of games on account of multiple games on a single date, a running number starting from 1 shall be automatically affixed to each game.
  1. First game entered for a given date shall be number 1, second game number 2 etc.

## Add scores to game ##
  1. Scores shall be addable to games.
  1. Only ranking of players shall be recorded.
  1. Points for each position are to be gotten from the database.
  1. Ranking must be attached to a game.

## Add and delete players ##
  1. Players shall be addable and deletable from the database.
  1. Players are distinguished by their (unique) name.
  1. The maximum length of a player's name is 100 characters long.
  1. Two players with the same name shall not be addable, regardless of state (see 3.6)
  1. Players shall be deletable from the database by using their name.
  1. When a player is deleted, it shall not be removed from the database. Instead, the database shall have a state field for this player indicating the deletion.
  1. Score related information for a deleted player is discarded while calculating ranking and scores.
  1. Other data for a deleted player, such as game host data, is left intact. Functionality shall be the same as for an not-delete player.

## Reactivate player ##
  1. Deleted players shall be reactivateable.
  1. When a player is reactivated, their state field is set to normal state. They shall function similarly to other normal players.
  1. Data other than state for a reactivated player is left intact. The player shall be at the state it was before deletion occurred.

## Show ranking ##
  1. Ranking of players in the statistics shall be presented for each player graphically and numerically as per 4.2) and 4.3).
  1. Totals shall be presented numerically
  1. Score per game shall be presented in a graph.

## Environment requirements ##
  1. Pokkare is required to be fully useable on the following environments: Windows XP Service pack 3, Ubuntu Linux 8.10, Mac OS X Tiger (10.4)
    * Fully useable means all functionality listed in this document under requirements are fully operational.
  1. The following browsers are supported: Opera 9.64, Firefox 3.0.7 .
    * Functioning of pokkare on later or earlier releases of the above listed operating systems and/or browsers is likely, but can not be guaranteed in the scope of this project.

# Structure #

## General ##
![http://www.cs.helsinki.fi/u/jmurtola/pokkare/struts-hibernate.jpg](http://www.cs.helsinki.fi/u/jmurtola/pokkare/struts-hibernate.jpg)

A general diagram of the program's structure

## Class structure ##
![http://www.cs.helsinki.fi/u/jmurtola/pokkare/class_diagram.jpg](http://www.cs.helsinki.fi/u/jmurtola/pokkare/class_diagram.jpg)

Class diagram

## Data model ##

![http://www.cs.helsinki.fi/u/jmurtola/pokkare/db_model.jpg](http://www.cs.helsinki.fi/u/jmurtola/pokkare/db_model.jpg)

Data model

# Development tools & environments #

## Tools ##
  * [Eclipse Europa](http://www.eclipse.org/downloads/moreinfo/jee.php)
  * [MySQL 5.1](http://dev.mysql.com/downloads/mysql/5.1.html)
  * [Tomcat 6.x](http://tomcat.apache.org/download-60.cgi)
  * All jar's from pokkare/WebContent/WEB-INF/lib

### Suggested ###

  * [Subclipse](http://subclipse.tigris.org/install.html) (Eclipse svn plugin)
    1. Requires Mylyn plugin, is automatically installable the same way as Subclipse
  * [EclEmma](http://eclemma.org/installation.html) (Eclipse code coverage plugin)

## Continuous integration on Alkokrunni ##

### Get access to alkokrunni for first time ###

  * Get public key from one of the other developers (id\_tkt\_agil\_db.cs.helsinki.fi)
  * Copy this public key to your department home folder, under the .ssh-folder. If the folder doesen't exist, create it.
  * Direct access to alkokrunni must be via melkki/melkinpaasi/etc. department server. The public key file cannot exist in other machines/servers. **Always ssh first to department server, _then_ to alkokrunni**
  * Use this command to ssh to alkokrunni:
> > `~$ ssh -i ~/.ssh/id_tkt_agil_db.cs.helsinki.fi tkt_agil@alkokrunni`
  * To setup handy shortcut, do:
> > `~$ echo "ssh -i ~/.ssh/id_tkt_agil_db.cs.helsinki.fi tkt_agil@alkokrunni" > tkt_agil`
> > `~$chmod a+x tkt_agil`
    1. First ssh to melkki, then use this command to continue to alkokrunni:
> > > `~$ ./tkt_agil`
