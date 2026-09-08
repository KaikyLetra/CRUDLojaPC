package com.template.services;

import com.template.model.dao.ComponentesDAO;
import com.template.model.dto.ComponentesDTO;
import com.template.validator.ComponentesValidator;
import com.template.validator.IComponentesValidator;

import java.util.ArrayList;

public class ComponentesServices implements IComponentesServices {

    private final ComponentesDAO componentesDAO = new ComponentesDAO();
    private final IComponentesValidator componentesValidator;

    // Construtor padrão que instancia o validador
    public ComponentesServices() {
        this.componentesValidator = new ComponentesValidator();
    }

    public ArrayList<ComponentesDTO> buscarTodos() {
        return componentesDAO.selectComponentes();
    }

    @Override
    public void cadastrarComponente(ComponentesDTO dto) {
        componentesValidator.validarComponente(dto);
        componentesDAO.insertComponente(dto);
    }

    @Override
    public void editarComponente(ComponentesDTO dto) {
        if (dto.getIdPc() <= 0) {
            throw new IllegalArgumentException("Selecione um registro válido para editar!");
        }
        componentesValidator.validarComponente(dto);
        componentesDAO.updateComponente(dto);
    }

    @Override
    public void deletarComponente(int idPc) {
        if (idPc <= 0) {
            throw new IllegalArgumentException("Selecione um registro para excluir!");
        }

        ComponentesDTO dto = new ComponentesDTO();
        dto.setIdPc(idPc);
        componentesDAO.deleteComponente(dto);
    }
}