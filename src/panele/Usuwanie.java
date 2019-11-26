package panele;

import Elementy.Element;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import mainPackage.Main;

public class Usuwanie extends Pane {
    static Label lUsuwanie;

    static Button bRysuj;

    public static ChoiceBox<Integer> cbElementy;

    public Usuwanie(){
        super();
        setLayoutX(900);
        setLayoutY(80);
        setPrefSize(400,500);

        lUsuwanie = new Label();
        lUsuwanie.setLayoutX(150);
        lUsuwanie.setLayoutY(0);
        lUsuwanie.setPrefSize(200,50);
        lUsuwanie.setText("usuwanie");
        getChildren().add(lUsuwanie);

        cbElementy = new ChoiceBox();
        cbElementy.setLayoutX(150);
        cbElementy.setLayoutY(50);
        cbElementy.setPrefSize(100,50);
        cbElementy.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Element element = Main.listaElementow.get(newValue);
            new Thread(() -> {
                Main.graphicsContext.setFill(new Color(Tlo.sR.getValue(),Tlo.sG.getValue(),Tlo.sB.getValue(),1));
                Main.graphicsContext.fillRect(0,0,Main.obraz.getWidth(),Main.obraz.getHeight());
                element.rysuj();
            }).start();
        });
        getChildren().add(cbElementy);

        bRysuj = new Button();
        bRysuj.setLayoutX(0);
        bRysuj.setLayoutY(350);
        bRysuj.setPrefSize(100,30);
        bRysuj.setText("usun element");
        bRysuj.setOnAction(event -> {

            try {
                int wartoscDoUsuniecia = cbElementy.getValue();
                cbElementy.getItems().remove(cbElementy.getItems().size() - 1);
                Main.listaElementow.remove(wartoscDoUsuniecia);
                Dodawanie.warstwa = 0;
                Dodawanie.lWarstwaWartosc.setText("0/" + Main.listaElementow.size());
            } catch (Exception e) {}

            Main.rysujKrajobraz(Main.graphicsContext,Main.obraz);
        });
        getChildren().add(bRysuj);


    }
}
