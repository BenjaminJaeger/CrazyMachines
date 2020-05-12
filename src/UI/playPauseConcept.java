package UI;

import Simulation.Objects.GameObject;
import Simulation.Simulation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class playPauseConcept {
    public static HBox createControls (ArrayList<GameObject> allObjects) {
        Button playpause = new Button("Play");
        playpause.setOnAction(e->{
            if(Simulation.isPlaying()) {
                playpause.setText("Play");
                Simulation.pause();
            }else {
                playpause.setText("Pause");
                Simulation.play();
            }

        });

        Button stop = new Button("Restart");
        stop.setOnAction(e->{
            if (Simulation.isPlaying()) {
                Simulation.pause();
                playpause.setText("Play");
            }

            Simulation.restart();
        });

        Button clear = new Button("Clear");
        clear.setOnAction(e->{
            if (!Simulation.isPlaying()) {
                allObjects.clear();
                GameObject.allObjects.clear();
            }
        });

        HBox controls = new HBox(10);
        controls.setAlignment(Pos.CENTER);
        controls.getChildren().addAll(playpause,stop,clear);

        return controls;
    }
}
