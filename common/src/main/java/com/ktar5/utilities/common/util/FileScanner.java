package com.ktar5.utilities.common.util;

import com.ktar5.utilities.common.constants.ConsoleColors;
import org.tinylog.Logger;

import java.io.File;
import java.util.function.Function;

public class FileScanner {

    public Function<File, Boolean> fileConsumer;
    private StringBuilder builder = new StringBuilder();
    private boolean print = false;

    public FileScanner(File root, Function<File, Boolean> fileConsumer) {
        this.fileConsumer = fileConsumer;
        searchDir(root, 0);
    }


    public FileScanner(File root, Function<File, Boolean> fileConsumer, boolean print) {
        this.fileConsumer = fileConsumer;
        searchDir(root, 0);
        this.print = print;
    }

    public void searchDir(){

    }

    public void searchDir(File root, int indent) {
        if (indent > 6) {
            return;
        }
        if (root == null) return;
        if (!root.isDirectory()) {
            throw new IllegalArgumentException(root.getPath() + " folder is not a Directory");
        }
        builder.append(getIndentString(indent));
        builder.append("+--");
        builder.append(root.getName());
        builder.append("/");
        Logger.debug(builder.toString());
        builder.setLength(0);
        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                searchDir(file, indent + 1);
            } else{
                Boolean apply = fileConsumer.apply(file);
                if (print) {
                    printFile(file, indent + 1, apply);
                }
            }
        }
    }

    private void printFile(File file, int indent, boolean special) {
        if (special) {
            builder.append(ConsoleColors.GREEN_BOLD);
            builder.append(getSpecialIndentString(indent));
        } else {
            builder.append(getIndentString(indent));
        }
        builder.append("+--");
        builder.append(file.getName());
        if (special) {
            builder.append(ConsoleColors.RESET);
        }
        Logger.debug(builder.toString());
        builder.setLength(0);
    }

    private String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("|  ");
        }
        return sb.toString();
    }

    private String getSpecialIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("#--");
        }
        return sb.toString();
    }

}
