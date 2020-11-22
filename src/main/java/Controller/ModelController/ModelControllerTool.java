package Controller.ModelController;

import Controller.ClientController.ClientControllerTool;

public class ModelControllerTool {

	private ClientControllerTool clientControllerTool;

	public ModelControllerTool() {
		clientControllerTool = new ClientControllerTool();
	}

	public ClientControllerTool getClientControllerTool() {
		return clientControllerTool;
	}

	public void setClientControllerTool(ClientControllerTool clientControllerTool) {
		this.clientControllerTool = clientControllerTool;
	}

}
