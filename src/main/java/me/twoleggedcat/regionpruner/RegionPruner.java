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
        if (args.length < 2) {
            System.err.println("Usage: java -jar RegionPruner.jar <region_folder> <max_ticks>");
            System.exit(1);
        }
        File regionFolder = new File(args[0]);
        if (!regionFolder.isDirectory()) {
            System.err.println(args[0] + " is not a directory; exiting.");
            System.exit(1);
        }
        try {
            minTimeToKeep = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Minimum time is not an integer");
            System.exit(1);
        }
        List<Path> files = Files.list(regionFolder.toPath()).toList();
        for (Path regionFilePath : files) {
            String regionFileName = regionFilePath.getFileName().toString();
            if (!(regionFileName.startsWith("r.") && regionFileName.endsWith(".mca"))) {
                System.err.println("File " + regionFileName + " does not match standard naming format; exiting.");
                System.exit(1);
            }
            MCAFile mcaFile = MCAUtil.read(regionFilePath.toFile());
            boolean shouldDelete = true;
            for (Chunk chunk : mcaFile) {
                if (chunk != null && chunk.getInhabitedTime() > minTimeToKeep) {
                    shouldDelete = false;
                }
            }
            if (shouldDelete) {
                if (regionFilePath.toFile().delete()) {
                    System.out.println("Deleted file " + regionFileName);
                    deleted++;
                }
                else {
                    System.out.println("Error deleting file " + regionFileName + "; exiting.");
                    System.exit(1);
                }
            } else {
                System.out.println("Skipping region file " + regionFileName);
            }
        }
        System.out.println("Done in " + (System.currentTimeMillis() - startTime) / 1000 + "s! Deleted " + deleted + " out of " + files.size() + "region files.");
    }
}
