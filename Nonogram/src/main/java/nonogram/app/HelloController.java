package nonogram.app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import model.Board;
import model.Game;
import viewmodel.AbstractViewModel;
import viewmodel.PlayViewModel;
import viewmodel.Clue;
import viewmodel.ViewModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController extends AbstractViewModel implements Initializable {
    Board boardOne = new Board("files/board1");
    int width = boardOne.getWidth();
    int height = boardOne.getHeight();
    Game game = new Game(boardOne, new Board(height,width));
    PlayViewModel model = new PlayViewModel(game);
    List<List<Clue>> listOfColumnClues= model.getColumnClues();
    List<List<Clue>> listOfRowClues = model.getRowClues();

    int maxNumberOfColumnClues = 0;
    @FXML
    private GridPane board;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(List<Clue> list : listOfColumnClues){
            maxNumberOfColumnClues= Math.max(maxNumberOfColumnClues, list.size());
        }


        board.setGridLinesVisible(true);
        int size = 0;
        board.setHgap(size);
        board.setVgap(size);
        for(int i=0;i<width;i++){
            ColumnConstraints col = new ColumnConstraints();
            board.getColumnConstraints().add(col);
        }
        for(int i=0;i<height;i++){
            RowConstraints row = new RowConstraints();
            board.getRowConstraints().add(row);
        }
        /*Text textNode = new Text("dsada");
        Text textNode1 = new Text("dsada");
        Text textNode2 = new Text("dsada");
        //board.add(textNode,0,0);
        board.add(textNode1,0,0);
        board.add(textNode2,0,0);*/


        for(int i=0;i<listOfColumnClues.size();i++){
            Text textNode = new Text(listOfColumnClues.get(i).toString());
            Label label = new Label();
            //label.setStyle("-fx-border-color: black; -fx-padding: 5px;");

            textNode.setFont(Font.getDefault());

            // Wrap text within label
            label.setWrapText(true);
            label.setMaxWidth(Double.MAX_VALUE);
            label.setMaxHeight(Double.MAX_VALUE);
            GridPane.setHalignment(label, HPos.CENTER);

            label.setGraphic(textNode);
            //label.setRotate(90);
            //textNode.getTransforms().add(new Rotate(-90,0,0));
            board.add(label,i+1,0);
        }
        for(int i=0;i<listOfRowClues.size();i++){
            Text textNode = new Text(listOfRowClues.get(i).toString());
            //textNode.getTransforms().add(new Rotate(-90,0,0));
            board.add(textNode,0,i+1);
        }
    }


}