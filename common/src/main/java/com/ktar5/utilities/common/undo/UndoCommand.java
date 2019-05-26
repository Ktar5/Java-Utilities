package com.ktar5.utilities.common.undo;

public abstract class UndoCommand {

    protected abstract void undo();

    /**
     * This also is do. This is called when added to the stack.
     */
    protected abstract void redo();
}
