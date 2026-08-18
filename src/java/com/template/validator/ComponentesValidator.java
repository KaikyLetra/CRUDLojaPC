package com.template.validator;

import com.template.model.dto.ComponentesDTO;

public class ComponentesValidator {

    public static void validar(ComponentesDTO dto) {
        if (!campoObrigatorioValidador(dto.getNome(), dto.getCpu(), dto.getGpu())) {
            throw new IllegalArgumentException("Erro: Preencha ao menos Nome, CPU e GPU!");
        }

        if (!armazenamentoValidador(dto.getArmazenamento())) {
            throw new IllegalArgumentException("Erro: Insira o tipo (HD ou SSD) no início e a capacidade (GB ou TB) no final!");
        }
    }

    private static boolean campoObrigatorioValidador(String nome, String cpu, String gpu) {
        return nome != null && !nome.trim().isEmpty()
                && cpu != null && !cpu.trim().isEmpty()
                && gpu != null && !gpu.trim().isEmpty();
    }

    private static boolean armazenamentoValidador(String armazenamento) {
        if (armazenamento == null) return false;
        return armazenamento.trim().toUpperCase().matches("(SSD|HD).*(GB|TB)");
    }
}