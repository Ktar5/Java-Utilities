package com.ktar5.utilities.common.undo;

import java.util.LinkedHashSet;

public class UniqueUndoGroup extends UndoCommand {
    private final LinkedHashSet<UndoCommand> commands;

    public UniqueUndoGroup(UndoCommand command) {
        this();
        commands.add(command);
    }

    public UniqueUndoGroup() {
        this.commands = new LinkedHashSet<>();
    }

    public void add(UndoCommand command) {
        if (command == null) {
            return;
        }
        commands.add(command);
        command.redo();
    }

    public int length() {
        return size();
    }

    public int size() {
        return commands.size();
    }

    @Override
    protected void redo() {
        for (UndoCommand command : commands) {
            command.redo();
        }
    }

    @Override
    protected void undo() {
        UndoCommand[] array = commands.toArray(new UndoCommand[]{});
        for (int i = array.length - 1; i >= 0; i--) {
            array[i].undo();
        }
    }
}
