package com.template.validator;

import com.template.model.dto.ComponentesDTO;

import java.util.ArrayList;
import java.util.List;

public class ComponentesValidator implements IComponentesValidator {

    public boolean validarComponente(ComponentesDTO dto) {
        List<Validator<String>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidator("Nome", dto.getNome()));
        validadores.add(new CampoObrigatorioValidator("CPU", dto.getCpu()));
        validadores.add(new CampoObrigatorioValidator("GPU", dto.getGpu()));
        validadores.add(new ArmazenamentoValidador(dto.getArmazenamento()));

        for (Validator<String> validador : validadores) {
            if (!validador.validar(validador.getValor())) {
                throw new IllegalArgumentException(validador.getMensagemErro());
            }
        }
        return true;
    }


}