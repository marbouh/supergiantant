package Algorithme;

/**
 *  Abstract class Algorithme : 
 */
public abstract class Algorithme {
	private long timeStart;
	private long timeStop;
	private boolean isRunning;

	public Algorithme() {
		timeStart = 0;
		timeStop = 0;
		isRunning = false;
	}

	public void start() {
		if (isRunning) {
			throw new IllegalStateException("L'algorithme est déjà en fonctionement");
		}
		timeStop = 0;
		isRunning = true;
		timeStart = System.currentTimeMillis();
	}

	public void stop() {
		if (!isRunning) {
			throw new IllegalStateException("L'algorightme n'est pas en court de fonctionement");
		}
		timeStop = System.currentTimeMillis();
		isRunning = false;
	}
	public boolean getState() {
		return isRunning;
	}

	public double getTime() {
		double time;
		if (isRunning)
			time = System.currentTimeMillis() - timeStart;
		else
			time = timeStop - timeStart;
		return time;
	}

	public abstract Graphe getResultant();
}