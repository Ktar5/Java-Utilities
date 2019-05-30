package com.ktar5.utilities.common.undo;

import java.util.ArrayList;
import java.util.List;

public class UndoStack {
    private final List<UndoCommand> undoCommands;
    //Note that cursor is stored in amount backwards from undoCommands.size()
    private int cursor = 0;

    private boolean allUndone, allRedone;

    //TODO limit the list to a certain number of values
    public UndoStack() {
        undoCommands = new ArrayList<>();
    }

    public void push(UndoCommand command) {
        if (!(command instanceof UndoGroup)) {
            command.redo();
        }
        if (cursor != 0) {
            while (cursor > 0) {
                undoCommands.remove(undoCommands.size() - 1);
                cursor--;
            }
            cursor = 0;
        }
        undoCommands.add(command);
        allUndone = false;
        allRedone = true;
//        System.out.println("Added; size: " + undoCommands.size() + " cursor: " + cursor);
    }

    /*
    NOTE: IM NOT ENTIRELY SURE HOW I GOT THIS TO WORK BUT IT DOES SO PLEASE DON'T TOUCH IT
     */

    public void redo() {
//        System.out.println("Redo method start");
        if (allRedone || undoCommands.size() == 0) {
//            System.out.println("Was last redo, don't redo. Cursor: " + cursor + ". Size: " + undoCommands.size());
            return;
        }
//        System.out.println("Pre-Redo. Cursor: " + cursor + ". Index: " + (undoCommands.size() - cursor - 1) + "Size: " + undoCommands.size());
        if (allUndone) {
            //Don't decrement cursor, because of the double 0 problem (index not ever being -1)
        } else if(cursor > 0) {
            cursor -= 1;
        } else {
            //Index still at max value (all redone)
            allRedone = true;
//            System.out.println("All are now redone");
        }
        undoCommands.get(undoCommands.size() - cursor - 1).redo();


        allUndone = false;

//        System.out.println("Post-Redo. Cursor: " + cursor + ". Index: " + (undoCommands.size() - cursor - 1) + "Size: " + undoCommands.size());
    }

    public void undo() {
        allRedone = false;
//        System.out.println("Undo method start");
        if (allUndone || undoCommands.size() == 0) {
//            System.out.println("Was last UNDO, don't UNDO. Cursor: " + cursor + ". Size: " + undoCommands.size());
            return;
        }
        int indexOfCursor = undoCommands.size() - cursor - 1;
//        System.out.println("Pre-UNdo. Cursor: " + cursor + ". Index: " + indexOfCursor + "Size: " + undoCommands.size());
        undoCommands.get(indexOfCursor).undo();
        if (indexOfCursor != 0) {
            cursor += 1;
        } else {
//            System.out.println("All are now undone");
            allUndone = true;
        }
//        System.out.println("Post-UNdo. Cursor: " + cursor + ". Index: " + (undoCommands.size() - cursor - 1) + "Size: " + undoCommands.size());
    }


}
