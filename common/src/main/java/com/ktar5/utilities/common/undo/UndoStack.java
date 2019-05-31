package com.ktar5.utilities.common.undo;

public class UndoStack {
    private boolean lastUndo;

    //Note on cursor:
    // The cursor will ALWAYS BE VISIBLE
    // **UNLESS** IT IS THE TAIL
    private UndoCommand latest, oldest, cursor;

    //TODO limit the list to a certain number of values

    public void push(UndoCommand command) {
        if (!(command instanceof UndoGroup)) {
            command.redo();
        }
        if (oldest == null || lastUndo) {
            oldest = latest = cursor = command;
            lastUndo = false;
            return;
        }

        if (cursor != latest) {
            cursor.setNext(null);
            latest = cursor;
        }

        latest.setNext(command);
        command.setPrevious(latest);

        latest = command;

        cursor = command;
    }

    public void redo() {
        if (lastUndo) {
            cursor.redo();
            lastUndo = false;
            return;
        }

        if (cursor.getNext() != null) { //Cursor is not the latest (should never have to redo the last one) (because cursor is always visible)
            cursor = cursor.getNext();
            cursor.redo();
        }
    }

    public void undo() {
        if (lastUndo) {
            return;
        }

        cursor.undo();
        if (cursor == oldest) {
            lastUndo = true;
        } else {
            cursor = cursor.getPrevious();
        }
    }


}
