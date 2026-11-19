/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.mariana.dukemarket.javafx.controller;
import br.dev.mariana.dukemarket.javafx.DAO.ClientesDAO;
import br.dev.mariana.dukemarket.javafx.model.Clientes;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;
import javafx.fxml.Initializable;

import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ScrClientesController implements Initializable {
    
    @FXML
    TableView<Clientes> tblClientes;

    @FXML
    TableColumn<Clientes, Integer> tcoId;

    @FXML
    TableColumn<Clientes, String> tcoNome;

    @FXML
    TableColumn<Clientes, String> tcoCPF;

    @FXML
    TableColumn<Clientes, String> tcoEndereco;

    @FXML
    TableColumn<Clientes, Integer> tcoCidade;

    @FXML
    TableColumn<Clientes, Integer> tcoUF;

    @FXML
    TableColumn<Clientes, Calendar> tcoDataCadastro;

    @FXML
    TextField txtId;

    @FXML
    TextField txtNome;

    @FXML
    TextField txtCPF;

    @FXML
    TextField txtEndereco;

    @FXML
    TextField txtCidade;

    @FXML
    TextField txtUF;

    @FXML
    DatePicker dtpDataCadastro;

    ClientesDAO pDAO;
    Clientes pClicked;

    boolean flagNovo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindColumns();

        carregaDados();
    }

    private void bindColumns() {
        tcoId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tcoId.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tcoNome.setStyle("fx-alignment: CENTER_RIGHT;");

        tcoCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));

        tcoEndereco.setCellValueFactory(new PropertyValueFactory<>("Endereco"));
        tcoEndereco.setStyle("fx-alignment: CENTER_RIGHT;");

        tcoCidade.setCellValueFactory(new PropertyValueFactory<>("Cidade"));
        tcoCidade.setStyle("fx-alignment: CENTER_RIGHT;");
        
        tcoUF.setCellValueFactory(new PropertyValueFactory<>("UF"));
        tcoUF.setStyle("fx-alignment: CENTER_RIGHT;");

        tcoDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        tcoDataCadastro.setStyle("fx-alignment: CENTER_RIGHT;");

    }

    public void carregaDados() {
        
        this.pDAO = new ClientesDAO();


        this.tblClientes.getItems().setAll(pDAO.getResults());
        
        
    }

    @FXML
    private void btnNovoClick() {
        txtId.setText("auto");
        txtId.setEditable(false);

        txtNome.setText("");
        txtNome.requestFocus();

        txtCPF.setText("");
        txtEndereco.setText("");
        txtCidade.setText("");
        txtUF.setText("");
        dtpDataCadastro.setValue(null);
    }

    @FXML
    private void tblProdutoOnMouseClicked() {
        this.pClicked = tblClientes.getSelectionModel().getSelectedItem();
        if (pClicked != null) {
            txtId.setText(String.valueOf(pClicked.getId()));
            txtId.setEditable(false);

            txtNome.setText(pClicked.getNome());
            txtCPF.setText(pClicked.getCPF());

            txtEndereco.setText(String.valueOf(pClicked.getEndereco()));
            txtCidade.setText(String.valueOf(pClicked.getCidade()));
            txtUF.setText(String.valueOf(pClicked.getUF()));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            formatter = formatter.withLocale(Locale.US);
            LocalDate date = LocalDate.parse(pClicked.getDataCadastro(), formatter);

            dtpDataCadastro.setValue(date);
            dtpDataCadastro.setEditable(false);

            this.flagNovo = false;

        }
    }

    @FXML
    public void btnSalvarClick(){
        Clientes p= new Clientes();
        p.setNome(txtNome.getText());
        p.setCPF(txtCPF.getText());
       p.setEndereco(txtEndereco.getText());
        p.setCidade(txtCidade.getText());
        p.setUF(txtUF.getText());
        
        if (this.flagNovo){
            this.pDAO.create(p);
        }else{
            p.setId(Integer.parseInt(txtId.getText()));
            this.pDAO.update(p);
        }
        this.carregaDados();
    }
    
    @FXML
    public void btnExcluirClick() {
        if (this.pClicked != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirma Exclusão do Cadastro?");
            alert.setHeaderText(pClicked.getNome());
            alert.setContentText("Tem certeza que deseja excluir?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.pDAO.delete(this.pClicked.getId());
                this.tblClientes.getItems().remove(this.pClicked);
            }
            this.tblClientes.getSelectionModel().clearSelection();
            pClicked=null;
        }else{
            Alert alert=new Alert(AlertType.WARNING);
            alert.setTitle("Ops!");
            alert.setHeaderText("Atenção");
            alert.setContentText("Você deve selecionar um cadastro antes de excluí-lo!");
            
            alert.showAndWait();
        }
    }
}
    

