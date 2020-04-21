package md.klass.application.controllerarguments;

public class NoteArgument implements AbstractControllerArgument {
    private String username;
    private int id;


    /**
    *Constructor for old note
     */
    public NoteArgument(String username, int id) {
        this.username = username;
        this.id = id;
    }


    /**
     *Constructor for new note
     */
    public NoteArgument(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

}
