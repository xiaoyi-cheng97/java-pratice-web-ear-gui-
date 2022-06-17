package presentation.fx.inputcontroller;

import facade.Interface.IProcessRequestServiceRemote;
import facade.exceptions.ApplicationException;
import facade.handlers.IInstalacaoServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.fx.model.PedidoPendente;
import presentation.fx.model.ProcessaPedidoModel;
import presentation.fx.model.RequestModel;

public class ProcessRequestController extends BaseController {

	@FXML private TitledPane p1;

	@FXML private ComboBox<String> sportBox;

	@FXML private ComboBox<String> RequestModel;

	@FXML private TitledPane p2;
	
	@FXML private TableView<RequestModel> tablePedidos;

	@FXML private TableColumn<RequestModel, Number> code;

    @FXML private TableColumn<RequestModel, String> date;

    @FXML private TableColumn<RequestModel, String> begin;

    @FXML private TableColumn<RequestModel, String> end;

    @FXML private TableColumn<RequestModel, String> description;

	@FXML private ComboBox<Integer> codeBox;

	@FXML private ComboBox<String> spaceBox;

	private ProcessRequestModel model;

	private IProcessRequestServiceRemote processService;

	public void setInstalacaoService(IProcessRequestServiceRemote processService) {
		this.processService = processService;
	}

	public void setModel(RequestModel model) {
		this.model = model;
		sportBox.setItems(model.getSport());
		spaceBox.setValue(model.getSelectedSport());
		RequestTypeBox.setItems(model.getTiposReserva());
		tipoReservaComboBox.setValue(model.getSelectedTipoReserva());
		codeBox.setItems(model.getCode());
		codeBox.setValue(model.getSelectedCode());
		spaceBox.setItems(model.getSpace());
		spaceBox.setValue(model.getSelectedSpace());
	}

	@FXML
	void initialize() {
		code.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
		date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		begin.setCellValueFactory(cellData -> cellData.getValue().beginProperty());
		end.setCellValueFactory(cellData -> cellData.getValue().endProperty());
		description.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
	}

	@FXML
	void sportSelected(ActionEvent event) {
		model.setSelectedSport(sportBox.getValue());
	}

	@FXML
	void RequestTypeSelected(ActionEvent event) {
		model.setSelectedRequestType(RequestTypeBox.getValue());
	}

	@FXML
	void codigoSelected(ActionEvent event) {
		if(codeBox.getValue() != null)
			model.setSelectedCodigo(codeBox.getValue());
	}

	@FXML
	void instalacaoSelected(ActionEvent event) {
		model.setSelectedInstalacao(spaceBox.getValue());
	}

	@FXML
	void pedidosPendentes(ActionEvent event) {
		String errorMessages = validateInput1();

		if (errorMessages.length() == 0) {
			try {
				model.clearProperties();
				model.setPedidos(.indicaModalidadeTipo(model.getSelectedModalidade(), model.getSelectedTipoReserva()));
				tablePedidos.setItems(model.getPedidos());
				openPane(p2);
				closePane(p1);
			} catch (ApplicationException e) {
				showError(e.getMessage());
			}
		} else
			showError(i18nBundle.getString("processa.pedido.error.validating") + ":\n" + errorMessages);
	}

	@FXML
	void processaPedido(ActionEvent event) {
		String errorMessages = validateInput2();

		if (errorMessages.length() == 0) {
			try {
				instalacaoService.indicaNumNome(model.getSelectedCodigo(), model.getSelectedInstalacao());
				model.clearProperties();
				clearProperties();
				//indicaModalidadeTipo(model.getSelectedModalidade(), model.getSelectedTipoReserva()
				model.setPedidos(processService.filterRequests(model.getSelectedSport(), model.getSelectedRequestType()));
				tablePedidos.setItems(model.getPedidos());
				showInfo(i18nBundle.getString("processa.pedido.success"));
			} catch (ApplicationException e) {
				showError(i18nBundle.getString("processa.pedido.error") + ":\n" + e.getMessage());
			}
		} else
			showError(i18nBundle.getString("processa.pedido.error.validating") + ":\n" + errorMessages);
	}

	private void openPane(TitledPane p) {
		p.setCollapsible(true);
		p.setExpanded(true);
		p.setCollapsible(false);
	}

	private void closePane(TitledPane p) {
		p.setCollapsible(true);
		p.setExpanded(false);
		p.setCollapsible(true);
	}

	private void clearProperties() {
		codeBox.setValue(null);
		spaceBox.setValue(null);
	}

	private String validateInput1() {
		StringBuilder sb = new StringBuilder();
		if (model.getSelectedModalidade() == null) {
			if (sb.length() > 0)
				sb.append("\n");
			sb.append(i18nBundle.getString("processa.pedido.invalid.modalidade"));
		}
		if (model.getSelectedTipoReserva() == null) {
			if (sb.length() > 0)
				sb.append("\n");
			sb.append(i18nBundle.getString("processa.pedido.invalid.tipoReserva"));
		}
		return sb.toString();
	}

	private String validateInput2() {
		StringBuilder sb = new StringBuilder();
		if (model.getSelectedCodigo() == null) {
			if (sb.length() > 0)
				sb.append("\n");
			sb.append(i18nBundle.getString("processa.pedido.invalid.codigoPedido"));
		}
		if (model.getSelectedInstalacao() == null) {
			if (sb.length() > 0)
				sb.append("\n");
			sb.append(i18nBundle.getString("processa.pedido.invalid.instalacao"));
		}
		return sb.toString();
	}
}
