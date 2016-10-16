package com.almasb.jencrypt;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {

    private AESEncryptor encryptor = new AESEncryptor();

    @FXML
    private TextField fieldPassword;
    @FXML
    private CheckBox isDecrypt;

    @FXML
    private void onDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles())
            event.acceptTransferModes(TransferMode.COPY);
        else
            event.consume();
    }

    @FXML
    private void onDragDropped(DragEvent event) {
        if (fieldPassword.getText().isEmpty()) {
            alert("No password entered");
            return;
        }

        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;

            Path file = db.getFiles().get(0).toPath();
            char[] password = fieldPassword.getText().toCharArray();

            // TODO: do this as a task
            if (isDecrypt.isSelected()) {
                decryptAndWrite(file, password);
            } else {
                encryptAndWrite(file, password);
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

    private void encryptAndWrite(Path file, char[] password) {
        try {
            byte[] data = Files.readAllBytes(file);

            byte[] encrypted = encryptor.encrypt(data, password);

            Files.write(Paths.get(file.toAbsolutePath().toString().concat(".ef")),
                    encrypted);
        } catch (Exception e) {
            alert(e.toString());
        }
    }

    private void decryptAndWrite(Path file, char[] password) {
        try {
            byte[] data = Files.readAllBytes(file);

            byte[] decrypted = encryptor.decrypt(data, password);

            Files.write(Paths.get(file.toAbsolutePath().toString().replace(".ef", "")),
                    decrypted);
        } catch (AESException e) {
            // we do not want to give any info about why decryption failed
            alert("Wrong password");
        } catch (Exception e) {
            alert(e.toString());
        }
    }

    private void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
    }
}
