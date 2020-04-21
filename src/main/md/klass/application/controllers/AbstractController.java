package md.klass.application.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;
import md.klass.application.controllerarguments.AbstractControllerArgument;
import md.klass.application.navigation.Navigator;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

  /**
   * @return true if user pressed ok, otherwise false
   */
  public boolean showInfoDialogWithOkAndCancelButton(String heading) {
    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText(heading);
    alert.setTitle("Please confirm");
    alert.setContentText(null);
    alert.initStyle(StageStyle.DECORATED);
    Optional<ButtonType> result=alert.showAndWait();
    if(result.get()==ButtonType.OK){
      return true;
    }
    else{
      return false;
    }
  }

  public String getTextFieldFromUserWithDialog(String heading) {
    String[] title= new String[1];
    TextInputDialog dialog=new TextInputDialog();
    dialog.setHeaderText(heading);
    dialog.setTitle("Title");
    Optional<String> result=dialog.showAndWait();
    result.ifPresent(name ->title[0]= name);
    return title[0];
  }
}
