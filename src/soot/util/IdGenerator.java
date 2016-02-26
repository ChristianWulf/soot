package soot.util;

public final class IdGenerator {

	private static final IdGenerator INSTANCE = new IdGenerator();

	private int id;

	private IdGenerator() {
	}

	/**
	 * Returns the only instance of this class.
	 */
	public static IdGenerator getInstance() {
		return INSTANCE;
	}

	public synchronized long generateId() {
		return id++;
	}

}
