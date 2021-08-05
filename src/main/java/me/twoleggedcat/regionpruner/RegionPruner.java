package me.twoleggedcat.regionpruner;

import net.querz.mca.Chunk;
import net.querz.mca.MCAFile;
import net.querz.mca.MCAUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RegionPruner {
    public static void main(String[] args) throws IOException {
        int minTimeToKeep = (20 * 60 * 5); // Cuz we need this initialized
        int deleted = 0;
        long startTime = System.currentTimeMillis();

        if (args.length < 2)
            fatal("Usage: java -jar RegionPruner.jar <world_folder> <max_ticks>");

        File worldFolder = new File(args[0]);

        if (!worldFolder.isDirectory())
            fatal(args[0] + " is not a directory; exiting.");

        boolean pruneEntities =
                System.getProperty("RegionPruner.pruneEntities", "true").equalsIgnoreCase("true")
                && worldFolder.toPath().resolve("entities").toFile().isDirectory(); // Worlds <1.17 will not have this folder

        try {
            minTimeToKeep = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            fatal("Minimum time is not an integer");
        }

        List<Path> regionFiles = Files.list(worldFolder.toPath().resolve("region")).toList();
        for (Path regionFilePath : regionFiles) {
            String regionFileName = regionFilePath.getFileName().toString();
            if (!(regionFileName.startsWith("r.") && regionFileName.endsWith(".mca")))
                fatal("File " + regionFileName + " does not match standard naming format; exiting.");

            MCAFile mcaFile = MCAUtil.read(regionFilePath.toFile());

            boolean shouldDelete = true;
            for (Chunk chunk : mcaFile)
                if (chunk != null && chunk.getInhabitedTime() > minTimeToKeep)
                    shouldDelete = false;

            if (shouldDelete) {
                if (regionFilePath.toFile().delete()) {
                    System.out.println("Deleted region file " + regionFileName);
                    deleted++;
                } else {
                    fatal("Error deleting region file " + regionFileName + "; exiting.");
                }

                if (pruneEntities) {
                    File entityFile = worldFolder.toPath().resolve("entities/" + regionFileName).toFile();
                    if (entityFile.exists()) {
                        if (entityFile.delete())
                            System.out.println("Deleted entity file " + regionFileName);
                        else
                            fatal("Error deleting entity file " + regionFileName + "; exiting.");
                    }
                } else {
                    System.out.println("Skipping entity file for " + regionFileName);
                }
            }
        }
        System.out.println("Done in " + (System.currentTimeMillis() - startTime) / 1000 + "s! Deleted " + deleted + " out of " + regionFiles.size() + " region files.");
    }

    private static void fatal(String error) {
        System.err.println(error);
        System.exit(1);
    }
}
