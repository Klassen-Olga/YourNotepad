package md.klass.application.navigation;

public class ControllerArgument<T> {
	private T argument;

	public ControllerArgument(T argument) {
		this.argument = argument;
	}

	public <T> T getArgument() {
		return (T) argument;
	}
}
