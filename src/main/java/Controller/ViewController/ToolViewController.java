package Controller.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ClientView.ToolView;
import Controller.ModelController.ModelControllerTool;

public class ToolViewController {
	
	private ToolView toolView;
	private ModelControllerTool modelControllerTool;
	
	public ToolViewController(ModelControllerTool modelControllerTool) {
		toolView = new ToolView();
		toolView.setVisible(true);
		toolView.pack();
		toolView.addToolListener(new ToolListener());
		this.modelControllerTool = modelControllerTool;
	}
	
	class ToolListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == toolView.getSearch())
				System.out.println("got search button");
			
		}
		
	}

	public ToolView getToolView() {
		return toolView;
	}

	public void setToolView(ToolView toolView) {
		this.toolView = toolView;
	}
	
	

}
