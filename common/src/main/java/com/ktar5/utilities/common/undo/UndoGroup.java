package com.ktar5.utilities.common.undo;

import java.util.ArrayList;
import java.util.List;

public class UndoGroup extends UndoCommand {
    private final List<UndoCommand> commands;

    public UndoGroup(UndoCommand command) {
        this();
        commands.add(command);
    }

    public UndoGroup() {
        this.commands = new ArrayList<>();
    }

    public void add(UndoCommand command){
        if(command == null){
            return;
        }
        commands.add(command);
        command.redo();
    }

    public int length(){
        return size();
    }

    public int size(){
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
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}
