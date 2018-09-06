package Task;
import org.dreambot.api.script.AbstractScript;

public abstract class Task {
	private String Name;
	public AbstractScript Main;
	
	public Task(AbstractScript context, String name) {
		Main = context;
		Name = name;
	}
	
	/**
	 * <p>This method is called if the task is not complete.</p>
	 * @return <h3>int</h3>
	 * 	<p>
	 * 	   O : normal operation<br>
	 * 	  -1 : error			
	 * </p>
	 */
	public abstract int execute();
	
	public abstract boolean isComplete();
	
	public String getName() { return Name; }
}
