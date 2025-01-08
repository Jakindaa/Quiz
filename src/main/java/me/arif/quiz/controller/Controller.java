package me.arif.quiz.controller;

import javafx.fxml.FXML;
import me.arif.quiz.manager.SceneManager;

public class Controller {
    /*
    Mostly I need a custom controller class for the init method.
    Because the init method provided by JavaFX's controller is called when the scene is loaded.
    But I want to call the init method when SceneManager switches to this scene.
    Also, returnToMenu is common in most scenes, so I added it for that.
     */

    @FXML
    private void returnToMenu() {
        SceneManager.switchTo("main_menu");
    }

    public void init() {}
}
