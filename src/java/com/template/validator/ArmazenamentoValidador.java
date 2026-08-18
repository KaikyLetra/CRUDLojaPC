package com.template.validator;

import java.util.regex.Pattern;

class ArmazenamentoValidador implements Validator<String> {
    private static final String ARMAZENAMENTO_REGEX = "(SSD|HD).*(GB|TB)";
    private final Pattern pattern = Pattern.compile(ARMAZENAMENTO_REGEX);
    private final String armazenamento; // Armazena o armazenamento a ser validado

    public ArmazenamentoValidador(String armazenamento) {
        this.armazenamento = armazenamento;
    }

    @Override
    public boolean validar(String valorAtual) { // O valor do parâmetro é o que será validado neste ciclo
        if (this.armazenamento == null) return false;
        return pattern.matcher(this.armazenamento.trim().toUpperCase()).matches();
    }

    @Override
    public String getMensagemErro() {
        return "Digite um armazenamento válido (exemplo: SSD 512GB ou HD 1TB)!";
    }

    @Override
    public String getValor() {
        return armazenamento;
    }
}