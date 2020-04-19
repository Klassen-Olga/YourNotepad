package md.klass.application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import md.klass.application.controllerarguments.AbstractControllerArgument;
import md.klass.application.navigation.Navigator;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractController<T extends AbstractControllerArgument> {

  /**
   * Will be called in Navigator method if we want to switch between controllers with certain data
   * each controller has own data that should be transmitted therefore we implement a class with
   * data for each controller
   */
  public abstract void setInput(T argument);

  public abstract void beforeBegin();

  @FXML Label errorMessage;

  public void printErrors(List<String> errors) {
    errorMessage.setText("");
    if (errors.size() < 2) {
      printError(errors.get(0));
      return;
    }
    errors
        .subList(0, 2)
        .forEach(
            error -> {
              if (errorMessage.getText().length() == 0 || errorMessage.getText().equals("")) {
                printError(error);
              } else {
                errorMessage.setText(errorMessage.getText() + "\n" + error);
              }
            });
  }

  public void printError(String error) {
    errorMessage.setText(error);
  }

  public void logout() {
    navigateTo("login");
  }

  public void navigateTo(String view, AbstractControllerArgument argument) {
    try {
      Navigator.navigateTo(view, argument);
    } catch (IOException e) {
      printError(e.getMessage());
    }
  }

  public void navigateTo(String view) {
    try {
      Navigator.navigateTo(view);
    } catch (IOException e) {
      printError(e.getMessage());
    }
  }

  /** @return true if user pressed ok, otherwise false */
  public boolean showInfoDialogWithOkAndCancelButton(String heading, String text, StackPane pane) {
    JFXDialogLayout content = new JFXDialogLayout();
    AtomicBoolean result = new AtomicBoolean();
    content.setHeading(new Text(heading));
    content.setBody(new Text(text));
    JFXDialog dialog = new JFXDialog(pane, content, JFXDialog.DialogTransition.CENTER);
    JFXButton okButton = new JFXButton("Okay");
    JFXButton cancelButton = new JFXButton("Cancel");
    okButton.setOnAction(
        event -> {
          dialog.close();
          result.set(true);
        });
    cancelButton.setOnAction(
        event -> {
          dialog.close();
          result.set(false);
        });
    content.setActions(okButton, cancelButton);
    dialog.show();
    return result.get();
  }

  public String getTextFieldFromUserWithDialog(String heading, StackPane pane) {
    JFXDialogLayout content = new JFXDialogLayout();
    TextField textField = new TextField();
    content.setHeading(new Text(heading));
    content.setBody(textField);
    JFXDialog dialog = new JFXDialog(pane, content, JFXDialog.DialogTransition.CENTER);
    JFXButton button = new JFXButton("Okay");
    button.setOnAction(event -> dialog.close());
    content.setActions(button);
    dialog.show();
    return textField.getText();
  }
}
