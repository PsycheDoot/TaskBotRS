package Scripts;

import org.dreambot.api.script.AbstractScript;

import Task.Task;

public class Smelter extends Task{

	public Smelter(AbstractScript context) {
		super(context, "Smelter");
	}

	@Override
	public int execute() {
		return 0;
	}

	@Override
	public boolean isComplete() {
		return true;
	}

}
