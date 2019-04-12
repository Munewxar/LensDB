package View;

import dataManagment.LensListInitializer;
import dataManagment.Lens;
import dataManagment.SQlDataManagement;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import start.Main;

import java.util.Optional;

public class LensOverviewController {
    @FXML
    private TableView<Lens> lensTableView;

    @FXML
    private TableColumn<Lens, Integer> idColumn;

    @FXML
    private TableColumn<Lens, String> lensNameColumn;

    @FXML
    private TableColumn<Lens, Integer> priceColumn;

    @FXML
    private TableColumn<Lens, Integer> focusDistanceColumn;

    @FXML
    private TableColumn<Lens, String> suitableForCanonColumn;

    @FXML
    private TableColumn<Lens, String> suitableForNikonColumn;

    private LensListInitializer lensListInitializer;
    private SQlDataManagement sQlDataManagement;
    private Main main;

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private Button deleteButton;

    @FXML
    private Button addButton;

    public LensOverviewController(){

    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        lensNameColumn.setCellValueFactory(cellData -> cellData.getValue().lensNameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        focusDistanceColumn.setCellValueFactory(cellData -> cellData.getValue().focusDistanceProperty().asObject());
        suitableForCanonColumn.setCellValueFactory(cellData -> cellData.getValue().suitableForCanonProperty());
        suitableForNikonColumn.setCellValueFactory(cellData -> cellData.getValue().suitableForNikonProperty());
    }

    public void init(LensListInitializer lensListInitializer, SQlDataManagement sQlDataManagement, Main main) {
        this.lensListInitializer = lensListInitializer;
        lensTableView.setItems(lensListInitializer.getLensList());

        this.sQlDataManagement = sQlDataManagement;
        this.main = main;

        deleteButton.graphicProperty().setValue(new ImageView(new Image("/resources/img/trash.png",
                15, 15, false, false)));
        addButton.graphicProperty().setValue(new ImageView(new Image("/resources/img/add.png",
                15, 15, false, false)));
    }

    @FXML
    private void handleHyperlink(){
        main.getHostServices().showDocument("https://vk.com/sasi__zhopy");
    }

    @FXML
    private void handleDeleteLens(){
        int selectedIndex = lensTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete object");
            alert.setHeaderText("Are you sure want to remove this object?");
            alert.setContentText(lensListInitializer.getLensList().get(
                    lensTableView.getSelectionModel().getSelectedIndex()).getLensName());

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                sQlDataManagement.deleteItem(lensTableView.getItems().get(selectedIndex).getId());
                lensTableView.getItems().remove(selectedIndex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No lens Selected");
            alert.setContentText("Please select a lens in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewLens() {
        main.showAddDialog();
    }

    public TableView<Lens> getLensTableView() {
        return lensTableView;
    }

    public SQlDataManagement getsQlDataManagement() {
        return sQlDataManagement;
    }
}
