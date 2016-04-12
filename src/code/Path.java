package code;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JPanel;

public class Path {

	private MasterLabyrinthGUI GUI;
	
	public Path(MasterLabyrinthGUI GUI){
		this.GUI = GUI;
		updatePaths();
		
	}
	
	public void updatePaths(){
		JPanel boardPanel = new JPanel();
		boardPanel = GUI.getBoardPanel();
		String fileName = "paths.txt";
		PrintWriter outputStream = null;
		try{
			 outputStream = new PrintWriter(fileName);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		
		for(int i=0;i<49;i++){
			JPanel tile = new JPanel();
			tile = (JPanel) boardPanel.getComponent(i);
			
			for(int j=0;j<9;j++){
				JPanel componentTiles = new JPanel();
				componentTiles = (JPanel) tile.getComponent(j);
				Color tileColor;
				tileColor = componentTiles.getBackground();
				
				if(tileColor.equals(Color.black))
					outputStream.append("B");
				else
					outputStream.append("O");
			}
			outputStream.println();
		}
		outputStream.flush();
		outputStream.close();
		
		
	}

}