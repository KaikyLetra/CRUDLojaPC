package com.template.validator;

import static com.template.util.DialogUtil.showError;

public class ComponentesValidator {
    public static boolean campoObrigatorioValidador(String nome, String CPU, String GPU)
    {
        return !nome.isEmpty() && !CPU.isEmpty() && !GPU.isEmpty();
    }

    public static boolean armazenamentoValidador(String armazenamento)
    {
        return armazenamento.trim().toUpperCase().matches("(SSD|HD).*(GB|TB)");
    }
}
