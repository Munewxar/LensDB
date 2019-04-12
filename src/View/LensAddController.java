package View;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LensAddController {
    @FXML
    private TextField lensNameTextField;

    @FXML
    private TextField lensPriceTextField;

    @FXML
    private TextField lensFocusDistanceTextField;

    @FXML
    private TextField lensSuitableForCanonTextField;

    @FXML
    private TextField lensSuitableForNikonTextField;

    private Stage dialogStage;
    private LensOverviewController lensOverviewController;

    public void setDialogStageAndController(Stage dialogStage, LensOverviewController lensOverviewController){
        this.dialogStage = dialogStage;
        this.lensOverviewController = lensOverviewController;
    }

    @FXML
    private void handleOk(){
        if(isInputValid()){
            lensOverviewController.getsQlDataManagement().insertItem(lensNameTextField.getText(),
                    Integer.parseInt(lensPriceTextField.getText()), Integer.parseInt(lensFocusDistanceTextField.getText()),
                    lensSuitableForCanonTextField.getText(), lensSuitableForNikonTextField.getText());

            lensOverviewController.getLensTableView().getItems().add(
                    lensOverviewController.getsQlDataManagement().getItem(lensNameTextField.getText()));

            dialogStage.close();
        }
    }

    private boolean isInputValid(){
        String errorMessage = "";

        if (lensNameTextField.getText() == null || lensNameTextField.getText().length() == 0)
            errorMessage += "No valid name!\n";

        for (int i = 0; i < lensOverviewController.getLensTableView().getItems().size(); i++){
            if (lensNameTextField.getText().equals(lensOverviewController.getLensTableView().getItems().get(i))) {
                errorMessage += "No valid name!";
                break;
            }
        }

        if (lensPriceTextField.getText() == null || lensPriceTextField.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        } else {
            try {
                Integer.parseInt(lensPriceTextField.getText());
            } catch (NumberFormatException e){
                errorMessage += "Price must be integer!\n";
            }
        }

        if (lensFocusDistanceTextField.getText() == null || lensFocusDistanceTextField.getText().length() == 0) {
            errorMessage += "No valid focus distance!\n";
        } else {
            try {
                Integer.parseInt(lensFocusDistanceTextField.getText());
            } catch (NumberFormatException e){
                errorMessage += "Focus distance must be integer!\n";
            }
        }

        if (lensSuitableForCanonTextField.getText() == null || lensSuitableForCanonTextField.getText().length() == 0 ||
                (!lensSuitableForCanonTextField.getText().equals("yes") && !lensSuitableForCanonTextField.getText().equals("no")))
            errorMessage += "No valid suitable for Canon mean! Must be either yes or no!\n";

        if (lensSuitableForNikonTextField.getText() == null || lensSuitableForNikonTextField.getText().length() == 0 ||
                (!lensSuitableForNikonTextField.getText().equals("yes") && !lensSuitableForNikonTextField.getText().equals("no")))
            errorMessage += "No valid suitable for Nikon mean! Must be either yes or no!\n";

        if (errorMessage.length() != 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }

        return true;
    }
}
