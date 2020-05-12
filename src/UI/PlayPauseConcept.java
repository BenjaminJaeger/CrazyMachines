package UI;

import Simulation.SimulationControler;
import Simulation.Objects.GameObject;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PlayPauseConcept {
    public static HBox createControls () {
        Button playpause = new Button("Play");
        playpause.setOnAction(e->{
            if(SimulationControler.isPlaying()) {
                playpause.setText("Play");
                SimulationControler.pause();
            }else {
                playpause.setText("Pause");
                SimulationControler.play();
            }

        });

        Button stop = new Button("Restart");
        stop.setOnAction(e->{
            if (SimulationControler.isPlaying()) {
                SimulationControler.pause();
                playpause.setText("Play");
            }

            SimulationControler.restart();
        });

        Button clear = new Button("Clear");
        clear.setOnAction(e->{
            if (!SimulationControler.isPlaying()) {
                GameObject.allObjects.clear();
            }
        });

        HBox controls = new HBox(10);
        controls.setAlignment(Pos.CENTER);
        controls.getChildren().addAll(playpause,stop,clear);

        return controls;
    }
}
