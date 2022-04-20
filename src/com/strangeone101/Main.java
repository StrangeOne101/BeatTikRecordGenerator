package com.strangeone101;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        final String outputFolder = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\My Games\\Idea Factory International, Inc\\Capture";

        final int[] characters = {1, 2, 3, 4, 11, 12, 13, 14}; //Me, You, Vice, Licht, Neptune, Noire, Blanc, Vert

        final int maxTimeOffset = 1000 * 60 * 60 * 15;

        int attempts = -1;

        String attemptsString = (String) JOptionPane.showInputDialog(null, "How many BeatTik recordings should be generated?", "BeatTik Record Generator", JOptionPane.QUESTION_MESSAGE, null, null, 8);

        if (attemptsString == null) {
            return;
        }

        try {
            attempts = Integer.parseInt(attemptsString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cannot parse \"" + attemptsString + "\"!");
            return;
        }

        if (attempts < 1) {
            JOptionPane.showMessageDialog(null, "Number must be more than 1!");
            return;
        }

        attempts = Math.min(attempts, 40);

        Random random = new Random();

        File outputFolderFile = new File(outputFolder);

        if (!outputFolderFile.exists()) {
            JOptionPane.showMessageDialog(null, "Failed to find capture directory at: " + outputFolderFile.getAbsolutePath());
            System.out.println("Failed to locate output folder at " + outputFolderFile.getAbsolutePath());
            return;
        }

        int progress = JOptionPane.showOptionDialog(null, "How many BeatTik tracks do you own?", "BeatTik Recording Generator", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Almost None", "Around 10", "Around 20"}, "Almost None");

        for (int i = 0; i < attempts; i++) {
            int char1 = characters[random.nextInt(characters.length)];
            int char2 = characters[random.nextInt(characters.length)];

            if (char1 == char2) char2 = 0;

            int score = random.nextInt(1_600_000) + 400_000;

            int floor = 5001;
            int track = 1001;

            if (progress > 0) {
                floor += random.nextInt(progress == 1 ? 3 : 6);
                track += random.nextInt(progress == 1 ? 10 : 20);
            }

            int kawaiiPoints = random.nextInt(3200) + 40;
            int kawaiiPoints2 = char2 == 0 ? 0 : random.nextInt(3200) + 400;

            int unknownValue1 = random.nextInt(7) + 19;
            int funPoints = random.nextInt(4400) + 1200;

            long time = (System.currentTimeMillis() / 1000) - random.nextInt(maxTimeOffset);

            String name = String.format("BeatTik_%d_%d_%d_%d_%d_%d_%d_%d_%d_%d.avi",
                char1, char2, score, track, floor, funPoints, unknownValue1, kawaiiPoints, kawaiiPoints2, time);

            System.out.println("Generated name: " + name);

            try {
                File newFile = new File(outputFolderFile, name);
                newFile.createNewFile();
                System.out.println("Created video: " + name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JOptionPane.showMessageDialog(null, "Generated " + attempts + " recordings for BeatTik!\n\n" +
                "You don't need to reload your save to see them.\n(Exiting and re-entering BeatTik is enough)", "BeatTik Recording Generator", JOptionPane.INFORMATION_MESSAGE);
    }
}
