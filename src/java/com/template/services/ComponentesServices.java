package com.template.services;

import com.template.model.dao.ComponentesDAO;
import com.template.model.dto.ComponentesDTO;
import com.template.validator.ComponentesValidator;

import java.util.ArrayList;

public class ComponentesServices {

    private final ComponentesDAO componentesDAO = new ComponentesDAO();

    public ArrayList<ComponentesDTO> buscarTodos() {
        return componentesDAO.selectComponentes();
    }

    public void cadastrar(ComponentesDTO dto) {
        ComponentesValidator.validar(dto);
        componentesDAO.insertComponente(dto);
    }

    public void editar(ComponentesDTO dto) {
        if (dto.getIdPc() <= 0) {
            throw new IllegalArgumentException("Selecione um registro válido para editar!");
        }
        ComponentesValidator.validar(dto);
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
}