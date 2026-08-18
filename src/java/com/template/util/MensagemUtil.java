package com.template.util;

import javafx.scene.control.Label;

public class MensagemUtil {

    public static void mostrarMensagem(Label lblMensagem, String texto, String corHex) {
        if (lblMensagem != null) {
            lblMensagem.setText(texto);
            lblMensagem.setStyle("-fx-text-fill: " + corHex + ";");
        } else {
            if ("#dc3545".equalsIgnoreCase(corHex)) {
                DialogUtil.showError(texto);
            } else {
                DialogUtil.showInfo(texto);
            }
        }
    }
}