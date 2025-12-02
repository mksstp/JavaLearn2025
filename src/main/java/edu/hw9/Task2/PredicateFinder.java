package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class PredicateFinder extends RecursiveTask<List<File>> {
    private final File root;
    private final long fileSize;
    private final String fileExtension;

    public PredicateFinder(File root, long fileSize, String fileExtension) {
        this.root = root;
        this.fileSize = fileSize;
        this.fileExtension = fileExtension;
    }

    @Override
    protected List<File> compute() {
        File[] files = root.listFiles();
        if (files == null) {
            return Collections.emptyList();
        }
        List<PredicateFinder> finderList = new ArrayList<>();
        List<File> answer = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                PredicateFinder finder = new PredicateFinder(file, fileSize, fileExtension);
                finderList.add(finder);
                finder.fork();
            }
        }
        for (PredicateFinder finder : finderList) {
            answer.addAll(finder.join());
        }
        for (File file : files) {
            int i = file.getName().lastIndexOf('.');
            if (i == -1) {
                continue;
            }
            String extension = file.getName().substring(i);
            if (file.length() == fileSize && extension.equals(fileExtension)) {
                answer.add(file);
            }
        }
        return answer;
    }
}
