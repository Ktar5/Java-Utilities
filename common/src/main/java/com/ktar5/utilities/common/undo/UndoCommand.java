package com.ktar5.utilities.common.undo;

public abstract class UndoCommand {

    /**
     * This also is do. This is called when added to the stack.
     */
    protected abstract void redo();

    protected abstract void undo();
}
