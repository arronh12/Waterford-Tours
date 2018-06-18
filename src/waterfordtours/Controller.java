package waterfordtours;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller implements Initializable {

    ArrayList<Attractions> TouristAttraction;
    Data dataObject;

    Scene addScene;
    Stage window;

    @FXML
    TextField name, address, COE, OT, desc, delText, updateSearchText;

    @FXML
    TextField updateName, updateAddress, updateCOE, updateOT, updateDesc;

    @FXML
    Label addInputMessage, delLabel;

    @FXML
    VBox delCheckBox, displayBox;

    @FXML
    Pane delCheck;

    @FXML
    Button delYes, delNo;

    @FXML
    ScrollPane displayScroll;

    @FXML
    GridPane displayGrid, updateGrid;

    int detailChange;

    /**
     * ********************************************************************************
     * Methods for switchings scenes from home scene to options
     * *******************************************************************************
     */
    @FXML
    public void add(ActionEvent event) throws IOException {

        Parent addParent = FXMLLoader.load(getClass().getResource("addGUI.fxml"));
        Scene addScene = new Scene(addParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }

    @FXML
    public void home(ActionEvent event) throws IOException {
        Parent addParent = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        addScene = new Scene(addParent);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }

    public void delete(ActionEvent event) throws IOException {
        System.out.println(TouristAttraction.size());
        Parent addParent = FXMLLoader.load(getClass().getResource("DeleteGUI.fxml"));
        addScene = new Scene(addParent);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }

    public void update(ActionEvent event) throws IOException {
        Parent addParent = FXMLLoader.load(getClass().getResource("UpdateGUI.fxml"));
        addScene = new Scene(addParent);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();

    }

    /**
     * *************************************************************************
     * Methods for adding attractions to system
     * ************************************************************************
     */
    @FXML
    public void addAttraction() throws IOException {
        Double cost = 0.0;

        if (name.getText().trim().equals("") || address.getText().trim().equals("")
                || COE.getText().trim().equals("") || OT.getText().trim().equals("")
                || desc.getText().trim().equals("")) {
            inputMessage();
        } else {
            addInputMessage.setText(null);

            try {
                cost = Double.parseDouble(COE.getText());
            } catch (IllegalArgumentException e) {
                clearAdd();
                addInputMessage.setText("Error: cost must be a number");
                return;
            }
            TouristAttraction.add(new Attractions(name.getText(), address.getText(), cost,
                    OT.getText(), desc.getText()));

            clearAdd();
            addInputMessage.setText("Attraction added...");
            dataObject.addData(TouristAttraction);
        }
    }

    @FXML
    public void clearAdd() {
        name.clear();
        address.clear();
        COE.clear();
        OT.clear();
        desc.clear();
        addInputMessage.setText(null);
    }

    /**
     * **************************************************************************
     * methods for delete scene
     * *************************************************************************
     */
    public void searchDel() throws IOException {

        for (int i = 0; i < TouristAttraction.size(); i++) {
            if (delText.getText() == null ? TouristAttraction.get(i).getName() == null : delText.getText().equals(TouristAttraction.get(i).getName())) {
                TouristAttraction.remove(i);
                delLabel.setText("Attraction removed...");
            } else {
                delLabel.setText("Error: not in database...");
            }

        }
        dataObject.addData(TouristAttraction);
    }

    public void deleteClear() {
        delText.clear();
        delLabel.setText(null);
    }

    /**
     * **********************************************************************
     * Methods for update
     **********************************************************************
     */
    @FXML
    public void updateSearch() {
        updateGrid.setDisable(true);
        for (int i = 0; i < TouristAttraction.size(); i++) {
            if (TouristAttraction.get(i).getName() == null ? updateSearchText.getText() == null : TouristAttraction.get(i).getName().equals(updateSearchText.getText())) {
                updateGrid.setOpacity(1);
                updateGrid.setDisable(false);
                addInputMessage.setText("input details you want to change...");
                detailChange = i;
            } else {
                addInputMessage.setText("Name Not Found...");
            }

        }
    }

    @FXML
    public void updateDetails() throws IOException {
        if (!(updateName.getText().trim().equals(""))) {
            TouristAttraction.get(detailChange).setName(updateName.getText());
        }
        if (!(updateAddress.getText().trim().equals(""))) {
            TouristAttraction.get(detailChange).setAddress(updateAddress.getText());
        }
        if (!(updateCOE.getText().trim().equals(""))) {
            try {
                Double cost = Double.parseDouble(updateCOE.getText());
                TouristAttraction.get(detailChange).setCOE(cost);
            } catch (IllegalArgumentException e) {
                clearUpdate();
                addInputMessage.setText("Error: cost must be a number");
                return;
            }
        }
        if (!(updateDesc.getText().trim().equals(""))) {
            TouristAttraction.get(detailChange).setDesc(updateDesc.getText());
        }
        if (!(updateOT.getText().trim().equals(""))) {
            TouristAttraction.get(detailChange).setOT(updateOT.getText());
        }

        dataObject.addData(TouristAttraction);

    }

    public void clearUpdate() {
        updateName.clear();
        updateAddress.clear();
        updateCOE.clear();
        updateOT.clear();
        updateDesc.clear();
        updateSearchText.clear();

        updateGrid.setOpacity(0);
        updateGrid.setDisable(true);

    }

    /**
     * **********************************************************************
     * methods for display all
     * *********************************************************************
     */
    @FXML
    public void displayAll(ActionEvent event) throws IOException {
        Parent addParent = FXMLLoader.load(getClass().getResource("DisplayGUI.fxml"));
        addScene = new Scene(addParent);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }

    @FXML
    public void displayData(ActionEvent e) {
        Button origin = (Button) e.getSource();
        String d = "displayAZButton";

        if (origin.getId() == null ? d == null : origin.getId().equals(d)) {
            Collections.sort(TouristAttraction, Comparator.comparing(Attractions::getName));
        } else {
            Collections.sort(TouristAttraction, Comparator.comparing(Attractions::getCOE));
        }

        displayBox.getChildren().clear();

        for (int i = 0; i < TouristAttraction.size(); i++) {
            displayGrid = new GridPane();

            Label nameLabel = new Label("name:           ");
            Label nameOut = new Label(TouristAttraction.get(i).getName());
            displayGrid.add(nameLabel, 0, 0);
            displayGrid.add(nameOut, 1, 0);

            Label addressLabel = new Label("Address:           ");
            Label addressOut = new Label(TouristAttraction.get(i).getAddress());
            displayGrid.add(addressLabel, 0, 1);
            displayGrid.add(addressOut, 1, 1);

            String cost = Double.toString(TouristAttraction.get(i).getCOE());
            Label costLabel = new Label("Cost:           ");
            Label costOut = new Label(cost);
            displayGrid.add(costLabel, 0, 2);
            displayGrid.add(costOut, 1, 2);

            Label otLabel = new Label("Opening Times:           ");
            Label otOut = new Label(TouristAttraction.get(i).getOT());
            displayGrid.add(otLabel, 0, 3);
            displayGrid.add(otOut, 1, 3);

            Label descLabel = new Label("Description:           ");
            Label descOut = new Label(TouristAttraction.get(i).getDesc());
            displayGrid.add(descLabel, 0, 4);
            displayGrid.add(descOut, 1, 4);

            displayBox.getChildren().add(displayGrid);
        }

    }

    @FXML
    public void inputMessage() {
        addInputMessage.setText("Error: Please fill in the empty fields...");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataObject = new Data();
        TouristAttraction = new ArrayList();
        dataObject.buildFile();
        try {
            TouristAttraction = dataObject.loadData();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
