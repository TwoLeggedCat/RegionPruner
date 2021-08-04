# RegionPruner
Out-of-game tool to delete region files from Minecraft worlds in which all chunks have been inhabited for under a certain time.

# Why not just delete chunks?
Two reasons:
1. Deleting entire region files gives you a nice big buffer to ensure you don't make goofy borders around where a player builds something. While this is still a possibility, for this to happen with this tool, a player would have had to build something right on the border into another region *and* each chunk in that region would have to have been inhabited for less than the the time specified.
2. More importantly, and the reason for this tool's existence, I personally have had bad experiences with removing individual chunks from region files, and deleting an entire file is much safer, and, in many cases, enough to reduce your world size by a good bit.

If, however, you do want to remove all chunks inhabited for under a certain amount of time, which is the better approach in many cases, I recommend [mcaselector](https://github.com/Querz/mcaselector)'s headless mode, which is built on the same library as this tool.

# Usage
`java -jar file.jar <region_folder> <time in ticks to use as cutoff>`

You may prepend `-DRegionPruner.pruneEntityFiles=false` before `-jar` to skip pruning entity files.

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
