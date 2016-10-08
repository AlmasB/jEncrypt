package com.almasb.jencrypt;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {

    private AESEncryptor encryptor = new AESEncryptor();

    @FXML
    private TextField fieldPassword;
    @FXML
    private CheckBox checkBox;

    @FXML
    private void onDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles())
            event.acceptTransferModes(TransferMode.COPY);
        else
            event.consume();
    }

    @FXML
    private void onDragDropped(DragEvent event) {
        if (fieldPassword.getText().isEmpty())
            return;

        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            File file = db.getFiles().get(0);
            Path path = file.toPath();

            try {
                if (checkBox.isSelected()) {
                    byte[] data = Files.readAllBytes(path);

                    byte[] decrypted = encryptor.decrypt(data,
                            fieldPassword.getText().toCharArray());

                    Files.write(Paths.get(path.toAbsolutePath().toString().replace(".ef", "")),
                            decrypted);
                } else {
                    byte[] data = Files.readAllBytes(path);

                    byte[] encrypted = encryptor.encrypt(data,
                            fieldPassword.getText().toCharArray());

                    Files.write(Paths.get(path.toAbsolutePath().toString().concat(".ef")),
                            encrypted);
                }
            } catch (IOException e) {
                handleError(e);
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

    private void handleError(Exception e) {
        // TODO: fix
        e.printStackTrace();
    }
}
