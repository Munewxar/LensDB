package start;

import View.LensAddController;
import View.LensOverviewController;
import dataManagment.LensListInitializer;
import dataManagment.SQLConnectionInitializer;
import dataManagment.SQlDataManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    private Stage stage;
    private FXMLLoader lensOverviewLoader;
    private LensOverviewController lensOverviewController;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Lens");

        this.stage.getIcons().add(new Image("/resources/img/iconfinder_12_4124852.png"));

        showLensOverview();

        SQLConnectionInitializer sqlConnectionInitializer = new SQLConnectionInitializer();
        sqlConnectionInitializer.initialize();

        LensListInitializer lensListInitializer = new LensListInitializer();
        lensListInitializer.initialize(sqlConnectionInitializer.getConnection());

        SQlDataManagement sQlDataManagement = new SQlDataManagement(sqlConnectionInitializer.getConnection());

        lensOverviewController = lensOverviewLoader.getController();
        lensOverviewController.init(lensListInitializer, sQlDataManagement, this);
    }

    public void showAddDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/View/LensInsertDialog.fxml"));
            AnchorPane pane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add lens");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            LensAddController lensAddController = loader.getController();
            lensAddController.setDialogStageAndController(dialogStage, lensOverviewController);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showLensOverview(){
        try {
            lensOverviewLoader = new FXMLLoader();
            lensOverviewLoader.setLocation(Main.class.getResource("/View/LensOverview.fxml"));
            AnchorPane lensOverview = lensOverviewLoader.load();
            Scene scene = new Scene(lensOverview);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
