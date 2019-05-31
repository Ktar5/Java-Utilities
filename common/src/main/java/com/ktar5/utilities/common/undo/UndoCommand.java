package com.ktar5.utilities.common.undo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class UndoCommand {
    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PACKAGE)
    private UndoCommand next, previous;

    /**
     * This also is do. This is called when added to the stack.
     */
    protected abstract void redo();

    protected abstract void undo();
}
