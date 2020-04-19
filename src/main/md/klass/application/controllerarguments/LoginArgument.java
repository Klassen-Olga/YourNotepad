package md.klass.application.controllerarguments;

public class LoginArgument implements AbstractControllerArgument {
  private String username;

  public LoginArgument(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }
}
