package model;
import java.util.Stack;

import model.interfaces.IUndoable;

public class CommandHistory {
	public static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	public static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
		dump();
	}

	public static boolean contains (IUndoable cmd) { return undoStack.contains(cmd); }

	public static void dump() {
		System.out.println("\tCurrent UndoStack: "+undoStack);
		System.out.println("\tCurrent RedoStack: "+redoStack);
	}
	
	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();
			redoStack.push(c);
			c.undo();
		}
		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			undoStack.push(c);
			c.redo();
		}
		return result;
	}
}
