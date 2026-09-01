package com.template.validator;

import java.util.regex.Pattern;

public class ArmazenamentoValidador implements Validator<String> {

    private static final String ARMAZENAMENTO_REGEX = "(SSD|HD).*(GB|TB)";
    private final Pattern pattern = Pattern.compile(ARMAZENAMENTO_REGEX);
    private final String armazenamento;

    public ArmazenamentoValidador(String armazenamento) {
        this.armazenamento = armazenamento;
    }

    @Override
    public boolean validar(String valorAtual) {
        String texto = (valorAtual != null) ? valorAtual : this.armazenamento;
        if (texto == null) return false;
        return pattern.matcher(texto.trim().toUpperCase()).matches();
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