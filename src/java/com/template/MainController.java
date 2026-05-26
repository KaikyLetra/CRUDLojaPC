package com.template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController
{
    @FXML private Button btnSalvar;
    @FXML private Button btnDeletar;
    @FXML private Button btnCadastrar;

    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtGabinete;
    @FXML private TextField txtCPU;
    @FXML private TextField txtGPU;
    @FXML private TextField txtRAM;
    @FXML private TextField txtDChannel;
    @FXML private TextField txtArmaz;
    @FXML private TextField txtBluetooth;

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

    @FXML private void btnCadastrarAction(ActionEvent event) {
        String nome = txtNome.getText();
        String gabinete = txtGabinete.getText();
        String cpu = txtCPU.getText();
        String gpu = txtGPU.getText();
        String ram = txtRAM.getText();
        Boolean dChannel = Boolean.parseBoolean(txtDChannel.getText());
        String armaz = txtArmaz.getText();
        Boolean bluetooth = Boolean.parseBoolean(txtBluetooth.getText());

        ComponentesDTO objComponenteDTO = new ComponentesDTO();
        objComponenteDTO.setNome(nome);
        objComponenteDTO.setGabinete(gabinete);
        objComponenteDTO.setCpu(cpu);
        objComponenteDTO.setGpu(gpu);
        objComponenteDTO.setRam(ram);
        objComponenteDTO.setDualchannel(dChannel);
        objComponenteDTO.setArmazenamento(armaz);
        objComponenteDTO.setBluetooth(bluetooth);

        ComponentesDAO objComponentesDAO = new ComponentesDAO();
        objComponentesDAO.insertComponente(objComponenteDTO);

        carregarComponente();

    }

    @FXML
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
}
