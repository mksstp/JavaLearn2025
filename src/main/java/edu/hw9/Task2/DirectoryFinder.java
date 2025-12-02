package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectoryFinder extends RecursiveTask<List<File>> {
    private final File root;
    private final int numberOfFiles;

    public DirectoryFinder(File root, int numberOfFiles) {
        this.root = root;
        this.numberOfFiles = numberOfFiles;
    }

    @Override
    protected List<File> compute() {
        File[] files = root.listFiles();
        if (files == null) {
            return Collections.emptyList();
        }
        List<DirectoryFinder> finderList = new ArrayList<>();
        List<File> answer = new ArrayList<>();
        int count = 0;
        for (File file : files) {
            if (file.isDirectory()) {
                DirectoryFinder finder = new DirectoryFinder(file, numberOfFiles);
                finderList.add(finder);
                finder.fork();
            } else {
                count++;
            }
        }
        for (DirectoryFinder finder : finderList) {
            answer.addAll(finder.join());
        }
        if (count > numberOfFiles) {
            answer.add(root);
        }
        return answer;
    }
}
