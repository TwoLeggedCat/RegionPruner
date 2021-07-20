# RegionPruner
Out-of-game tool to delete region files from Minecraft worlds in which all chunks have been inhabited for under a certain time.

# Usage
`java -jar file.jar <region_folder> <time in ticks to use as cutoff>`

# Downloading
Releases section on the right side, or from Actions at the top (requires a GitHub account).

# Building
`./gradlew shadowJar`, use `build/libs/RegionPruner-1.0-SNAPSHOT-all.jar`

# Attribution
All the heavy lifting done by this plugin is from the amazing library https://github.com/Querz/NBT

# Disclaimer

THERE IS NO WARRANTY FOR THE PROGRAM, TO THE EXTENT PERMITTED BY
APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING THE COPYRIGHT
HOLDERS AND/OR OTHER PARTIES PROVIDE THE PROGRAM "AS IS" WITHOUT WARRANTY
OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
PURPOSE.  THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE PROGRAM
IS WITH YOU.  SHOULD THE PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF
ALL NECESSARY SERVICING, REPAIR OR CORRECTION.

Backup your world first.
