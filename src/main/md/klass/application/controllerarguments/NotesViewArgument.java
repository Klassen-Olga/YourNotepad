package md.klass.application.controllerarguments;

public class NotesViewArgument implements AbstractControllerArgument {
  private String username;

  public String getUsername() {
    return username;
  }

  public NotesViewArgument(String username) {
    this.username = username;
  }
}
