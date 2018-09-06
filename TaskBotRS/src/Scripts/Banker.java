package Scripts;

import org.dreambot.api.script.AbstractScript;

import Task.Task;

public class Banker extends Task{

	public Banker(AbstractScript context) {
		super(context, "Banker");
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
