package com.template.services;

import com.template.model.dto.ComponentesDTO;

public interface IComponentesServices   {
    void cadastrarComponente(ComponentesDTO componente);
    void editarComponente(ComponentesDTO componente);
    void deletarComponente(int id);
}
