package org.groupId.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import java.net.URL;
import java.util.ResourceBundle;

public class progressbarcontroller implements Initializable {

    @FXML
    public ProgressBar Progressbar;

    @FXML
    private Label prosent;

    class threadtest implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i <100 ; i++) {

                try {
                    Progressbar.setProgress(i/100.0);
                    Thread.sleep(10);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Thread thread = new Thread(new threadtest());
        thread.start();

    }
}
