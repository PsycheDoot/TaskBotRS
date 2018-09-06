import org.dreambot.api.methods.Calculations;
//import org.dreambot.api.methods.map.Area;
//import org.dreambot.api.methods.map.Tile;
//import org.dreambot.api.methods.walking.impl.Walking;
//import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.ScriptManifest;

import Scripts.Banker;
import Scripts.Miner;
import Scripts.Navagator;
import Scripts.Smelter;
import Task.Task;

//import org.dreambot.api.wrappers.interactive.GameObject;
//import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.api.script.Category;

import java.util.LinkedList;
import java.util.Queue;

@ScriptManifest(author = "Sean Rice", name = "TaskBot", version = 1.0, description = "Run a series of tasks.", category = Category.UTILITY)

public class main extends AbstractScript {

	private enum State {
		none,
		execute
	};
	private State currentState = State.none;
	
	private Queue<Task> taskQueue = new LinkedList<Task>();
	
	private Task currentTask;

	// Global script methods
	public void onStart() {
		taskQueue.add(new Miner(this));
		taskQueue.add(new Smelter(this));
		taskQueue.add(new Banker(this));
		taskQueue.add(new Navagator(this));
	}

	public void onExit() {
		// Destroy GUI elements
		// Clean up
	}

	@Override
	public int onLoop() {
		switch(getState()) {
		case none:
			Task nextTask = taskQueue.poll();
			if (nextTask == null || taskQueue.isEmpty()) {
				log("The task queue is empty, finishing script.");
				stop();
				break;
			}
			currentTask = nextTask;
			break;
		case execute:
			if (currentTask.isComplete()) {
				log("Completed: " + currentTask.getName());
				currentTask = null;
			} else {
				currentTask.execute();
				log("Executing: " +  currentTask.getName());
			}
			break;
		}
		
		return Calculations.random(500, 1000);
	}
	
	public State getState() {
		if (currentTask == null) {
			return State.none;
		}
		return State.execute;
	}
}
