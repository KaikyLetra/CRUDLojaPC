package com.template.controller;

import com.template.model.dto.ComponentesDTO;
import com.template.services.ComponentesServices;
import com.template.util.DialogUtil;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import static com.template.services.ComponentesServices.mostrarMensagem;

public class MainController {

    @FXML private Button btnLimpar;
    @FXML private Button btnDeletar;
    @FXML private Button btnCadastrar;
    @FXML private Button btnEditar;

    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtGabinete;
    @FXML private TextField txtCPU;
    @FXML private TextField txtGPU;
    @FXML private TextField txtArmaz;

    @FXML private Spinner<Integer> txtRAM;
    @FXML private CheckBox txtDChannel;
    @FXML private CheckBox txtBluetooth;

    @FXML private Label lblMensagem;

    @FXML private TableView<ComponentesDTO> tblComponentes;
    @FXML private TableColumn<ComponentesDTO, Integer> colId;
    @FXML private TableColumn<ComponentesDTO, String> colNome;
    @FXML private TableColumn<ComponentesDTO, String> colGabinete;
    @FXML private TableColumn<ComponentesDTO, String> colCPU;
    @FXML private TableColumn<ComponentesDTO, String> colGPU;
    @FXML private TableColumn<ComponentesDTO, String> colRAM;
    @FXML private TableColumn<ComponentesDTO, Boolean> colDChannel;
    @FXML private TableColumn<ComponentesDTO, String> colArmaz;
    @FXML private TableColumn<ComponentesDTO, Boolean> colBluetooth;

    private final ComponentesServices componentesServices = new ComponentesServices();

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idPc"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colGabinete.setCellValueFactory(new PropertyValueFactory<>("gabinete"));
        colCPU.setCellValueFactory(new PropertyValueFactory<>("cpu"));
        colGPU.setCellValueFactory(new PropertyValueFactory<>("gpu"));
        colRAM.setCellValueFactory(new PropertyValueFactory<>("ram"));
        colDChannel.setCellValueFactory(new PropertyValueFactory<>("dualchannel"));
        colArmaz.setCellValueFactory(new PropertyValueFactory<>("armazenamento"));
        colBluetooth.setCellValueFactory(new PropertyValueFactory<>("bluetooth"));

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 128, 8, 4);
        txtRAM.setValueFactory(valueFactory);

        limparCampos();
        carregarComponente();
    }

    @FXML
    private void carregarComponente() {
        try {
            ArrayList<ComponentesDTO> listaComponentes = componentesServices.buscarTodos();
            tblComponentes.setItems(FXCollections.observableArrayList(listaComponentes));
        } catch (Exception e) {
            DialogUtil.showError("Erro ao carregar dados da tabela: " + e.getMessage());
        }
    }

    @FXML
    void btnLimparAction() {
        limparCampos();
        mostrarMensagem(lblMensagem, "Campos limpos. Pronto para um novo cadastro.", "#00adb5");
    }

    @FXML
    private void btnCadastrarAction(ActionEvent event) {
        try {
            ComponentesDTO dto = montarDTOFormulario();
            componentesServices.cadastrar(dto);

            limparCampos();
            carregarComponente();
            mostrarMensagem(lblMensagem, "PC Setup cadastrado com sucesso!", "#28a745");

        } catch (IllegalArgumentException e) {
            mostrarMensagem(lblMensagem, e.getMessage(), "#dc3545");
        } catch (Exception e) {
            DialogUtil.showError("Erro inesperado ao cadastrar: " + e.getMessage());
        }
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        try {
            if (txtId.getText().isEmpty()) {
                mostrarMensagem(lblMensagem, "Selecione um registro na tabela para editar!", "#dc3545");
                return;
            }

            ComponentesDTO dto = montarDTOFormulario();
            dto.setIdPc(Integer.parseInt(txtId.getText()));

            componentesServices.editar(dto);

            limparCampos();
            carregarComponente();
            mostrarMensagem(lblMensagem, "Registro atualizado com sucesso!", "#28a745");

        } catch (IllegalArgumentException e) {
            mostrarMensagem(lblMensagem, e.getMessage(), "#dc3545");
        } catch (Exception e) {
            DialogUtil.showError("Erro inesperado ao editar: " + e.getMessage());
        }
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        try {
            if (txtId.getText().isEmpty()) {
                mostrarMensagem(lblMensagem, "Selecione um registro na tabela para excluir!", "#dc3545");
                return;
            }

            int idPc = Integer.parseInt(txtId.getText());
            componentesServices.deletar(idPc);

            limparCampos();
            carregarComponente();
            mostrarMensagem(lblMensagem, "Registro excluído com sucesso!", "#dc3545");

        } catch (IllegalArgumentException e) {
            mostrarMensagem(lblMensagem, e.getMessage(), "#dc3545");
        } catch (Exception e) {
            DialogUtil.showError("Erro inesperado ao excluir: " + e.getMessage());
        }
    }

    @FXML
    void carregarCampos() {
        ComponentesDTO objComponenteDTO = tblComponentes.getSelectionModel().getSelectedItem();

        if (objComponenteDTO != null) {
            txtId.setText(String.valueOf(objComponenteDTO.getIdPc()));
            txtNome.setText(objComponenteDTO.getNome());
            txtGabinete.setText(objComponenteDTO.getGabinete());
            txtCPU.setText(objComponenteDTO.getCpu());
            txtGPU.setText(objComponenteDTO.getGpu());

            try {
                String ramLimpa = objComponenteDTO.getRam().replaceAll("[^0-9]", "");
                txtRAM.getValueFactory().setValue(Integer.parseInt(ramLimpa));
            } catch (Exception e) {
                txtRAM.getValueFactory().setValue(8);
            }

            txtDChannel.setSelected(objComponenteDTO.isDualchannel());
            txtArmaz.setText(objComponenteDTO.getArmazenamento());
            txtBluetooth.setSelected(objComponenteDTO.isBluetooth());

            btnEditar.setDisable(false);
            btnDeletar.setDisable(false);
            mostrarMensagem(lblMensagem, "Registro ID " + objComponenteDTO.getIdPc() + " selecionado para edição.", "#ffc107");
        }
    }

    private void limparCampos() {
        txtId.clear();
        txtNome.clear();
        txtGabinete.clear();
        txtCPU.clear();
        txtGPU.clear();
        txtArmaz.clear();

        if (txtRAM != null && txtRAM.getValueFactory() != null) {
            txtRAM.getValueFactory().setValue(8);
        }

        if (txtDChannel != null) txtDChannel.setSelected(false);
        if (txtBluetooth != null) txtBluetooth.setSelected(false);

        if (btnEditar != null) btnEditar.setDisable(true);
        if (btnDeletar != null) btnDeletar.setDisable(true);
    }

    private ComponentesDTO montarDTOFormulario() {
        ComponentesDTO dto = new ComponentesDTO();
        dto.setNome(txtNome.getText());
        dto.setGabinete(txtGabinete.getText());
        dto.setCpu(txtCPU.getText());
        dto.setGpu(txtGPU.getText());
        dto.setRam(txtRAM.getValue() + " GB");
        dto.setDualchannel(txtDChannel.isSelected());
        dto.setArmazenamento(txtArmaz.getText());
        dto.setBluetooth(txtBluetooth.isSelected());
        return dto;
    }
}