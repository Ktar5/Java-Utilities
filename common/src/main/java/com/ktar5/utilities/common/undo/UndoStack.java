package com.ktar5.utilities.common.undo;

import java.util.ArrayList;
import java.util.List;

public class UndoStack {
    private final List<UndoCommand> undoCommands;
    //Note that cursor is stored in amount backwards from undoCommands.size()
    private int cursor = 0;

    private boolean lastUndo, lastRedo;

    //TODO limit the list to a certain number of values
    public UndoStack() {
        undoCommands = new ArrayList<>();
    }

    public void push(UndoCommand command) {
        undoCommands.add(command);
        command.redo();
        if (cursor != 0) {
            for (int i = cursor; i > 0; i--) {
                undoCommands.remove(undoCommands.size());
            }
            cursor = 0;
        }
    }

    public void redo() {
        lastUndo = false;
        if (lastRedo) {
            return;
        }
        undoCommands.get(undoCommands.size() - cursor - 1).redo();
        if (cursor > 0) {
            cursor -= 1;
        } else {
            lastRedo = true;
        }
    }

    public void undo() {
        lastRedo = false;
        if (lastUndo) {
            return;
        }
        int indexOfCursor = undoCommands.size() - cursor - 1;
        undoCommands.get(indexOfCursor).undo();
        if (indexOfCursor != 0) {
            cursor += 1;
        } else {
            lastUndo = true;
        }
    }


}
