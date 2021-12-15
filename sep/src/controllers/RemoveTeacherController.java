package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import model.*;
import view.ViewHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

public class RemoveTeacherController
{

  private view.ViewHandler viewHandler;
  private Region root;
  private model.Model model;
  @FXML private TextField idField;
  @FXML private Label errorLabel;


  public RemoveTeacherController(){}

  public void init(ViewHandler viewHandler, model.Model model, Region root )
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  @FXML void removeTeacherButtonPressed() throws Exception
  {

    String id= idField.getText();

    try
    {
      model.validateRemoveTeacher(idField.getText());
      errorLabel.setText("");
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
    }
    if (errorLabel.getText().equals(""))
    {
      if (booleanconfirmation())
      {
        model.removeTeacherFromSystemByID(id);
        model.writeFiles();
        viewHandler.openView("ManageStudentsAndTeachers");
        errorLabel.setText("");
      }

    }
    reset();
  }

  @FXML void goBackButtonPressed()
  {
    viewHandler.openView("ManageStudentsAndTeachers");
  }

  private boolean booleanconfirmation()
  {
    Teacher teacher = model.getTeacherByID(idField.getText());

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Removing teacher: "+ teacher);
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  public void reset()
  {
    idField.setText("");

  }

  public Region getRoot()
  {
    return root;
  }
}
