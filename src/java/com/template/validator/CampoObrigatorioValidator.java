package com.template.validator;

public class CampoObrigatorioValidator implements Validator<String> {

    private final String nomeCampo;
    private final String valor;

    public CampoObrigatorioValidator(String nomeCampo, String valor) {
        this.nomeCampo = nomeCampo;
        this.valor = valor;
    }

    @Override
    public boolean validar(String valorAtual) {
        String texto = (valorAtual != null) ? valorAtual : this.valor;
        return texto != null && !texto.trim().isEmpty();
    }

    @Override
    public String getMensagemErro() {
        return "O campo " + nomeCampo + " está vazio!";
    }

    @Override
    public String getValor() {
        return valor;
    }
}