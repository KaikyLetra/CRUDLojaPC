package com.template.services;

import com.template.model.dao.ComponentesDAO;
import com.template.model.dto.ComponentesDTO;
import com.template.util.DialogUtil;
import com.template.validator.ComponentesValidator;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class ComponentesServices {

    private final ComponentesDAO componentesDAO = new ComponentesDAO();

    // =========================================================================
    // MÉTODOS DO CRUD (REGRAS DE NEGÓCIO)
    // =========================================================================

    public ArrayList<ComponentesDTO> buscarTodos() {
        return componentesDAO.selectComponentes();
    }

    public void cadastrar(ComponentesDTO dto) {
        validarCampos(dto);
        componentesDAO.insertComponente(dto);
    }

    public void editar(ComponentesDTO dto) {
        if (dto.getIdPc() <= 0) {
            throw new IllegalArgumentException("Selecione um registro válido para editar!");
        }
        validarCampos(dto);
        componentesDAO.updateComponente(dto);
    }

    public void deletar(int idPc) {
        if (idPc <= 0) {
            throw new IllegalArgumentException("Selecione um registro para excluir!");
        }

        ComponentesDTO dto = new ComponentesDTO();
        dto.setIdPc(idPc);
        componentesDAO.deleteComponente(dto);
    }

    private void validarCampos(ComponentesDTO dto) {
        if (!ComponentesValidator.campoObrigatorioValidador(dto.getNome(), dto.getCpu(), dto.getGpu())) {
            throw new IllegalArgumentException("Erro: Preencha ao menos Nome, CPU e GPU!");
        }

        if (!ComponentesValidator.armazenamentoValidador(dto.getArmazenamento())) {
            throw new IllegalArgumentException("Erro: Insira o tipo (HD ou SSD) no início e o modelo de dados (GB ou TB) no final!");
        }
    }

    // =========================================================================
    // MÉTODO UTILITÁRIO DE UI (INTEGRADO COM DIALOGUTIL)
    // =========================================================================

    public static void mostrarMensagem(Label lblMensagem, String texto, String corHex) {
        if (lblMensagem != null) {
            lblMensagem.setText(texto);
            lblMensagem.setStyle("-fx-text-fill: " + corHex + ";");
        } else {
            // Se não houver Label para emitir a mensagem, utiliza o DialogUtil
            if ("#dc3545".equalsIgnoreCase(corHex)) {
                DialogUtil.showError(texto);
            } else {
                DialogUtil.showInfo(texto);
            }
        }
    }
}