package gof;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GuiManager extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private GameOfLife game;
	
	private JPanel optionsPanel;
	private JPanel optionsStartStopPanel;
	private JPanel gamePanel;
	
	private JButton startButton;
	private JButton stopButton;
	private JButton blackCellsButton;
	private JButton colorCellsButton;
	private JButton structuresButton;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem[] fileMenuItems;
	
	private JSlider gameSpeedSlider;

	// For choosing structures
	private JInternalFrame structuresFrame;
	private JSplitPane splitPane;
	private JButton selectStructureButton;
	private JButton cancelStructureSelectionButton;

	
	
	
	// === Main Constructor === //
	
	GuiManager(GameOfLife g) {
		super("Game of Life");
		
		game = g;
		
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initPanels();
		initStructures();
		initButtons();
		initGameSpeedSlider();
		initMenuBar();
		
	}
	
	
	
	
	private void initStructures() {
		structuresFrame = new JInternalFrame("Game of Life - structures");
		structuresFrame.setSize(400, 200);
		this.add(structuresFrame);
		
		splitPane = new JSplitPane();
		selectStructureButton = new JButton("Select");
		cancelStructureSelectionButton = new JButton("Cancel");
		JPanel bottomStructuresPanel = new JPanel();
		bottomStructuresPanel.add(selectStructureButton);
		bottomStructuresPanel.add(cancelStructureSelectionButton);
		
		cancelStructureSelectionButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				structuresFrame.setVisible(false);
			}
		});
		
		structuresFrame.add(splitPane, BorderLayout.CENTER);
		structuresFrame.add(bottomStructuresPanel, BorderLayout.SOUTH);
	}

	
	
	
	private void initMenuBar() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		fileMenuItems = new JMenuItem[5];
		
		fileMenuItems[0] = new JMenuItem("Save as...");
		fileMenuItems[1] = new JMenuItem("Open file...");
		fileMenuItems[2] = new JMenuItem("Make quick backup");
		fileMenuItems[3] = new JMenuItem("Load quick backup");
		fileMenuItems[4] = new JMenuItem("Exit");
		
		fileMenuItems[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showDialog(GuiManager.this.getContentPane(), "Save");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					game.getGameMgr().saveToFile(file);
				}
				else if (returnVal == JFileChooser.ERROR_OPTION) {
					System.out.println("Failed opening a file");
				}
			}
		});
		
		fileMenuItems[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showDialog(GuiManager.this.getContentPane(), "Open");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					game.getGameMgr().saveToFile(file);
				}
				else if (returnVal == JFileChooser.ERROR_OPTION) {
					System.out.println("Failed opening a file");
				}
			}
		});
		
		fileMenuItems[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getGameMgr().makeQuickBackup();
			}
		});
		
		fileMenuItems[3].addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getGameMgr().loadQuickBackup();
			}
		});
		
		fileMenuItems[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiManager.this.dispose();	
			}
		});
		
		for (int i = 0; i != 5; ++i) {
			fileMenu.add(fileMenuItems[i]);
		}
		
		menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);
	}
	
	
	
	
	private void initPanels() {
		optionsPanel = new JPanel(new BorderLayout());
		optionsStartStopPanel = new JPanel(new GridLayout(1, 2));
		gamePanel = new JPanel();
		
		optionsPanel.add(optionsStartStopPanel, BorderLayout.NORTH);
		
		this.add(optionsPanel, BorderLayout.EAST);
		this.add(gamePanel);
	}
	
	
	
	private void initButtons() {
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		blackCellsButton = new JButton("Black");
		colorCellsButton = new JButton("Color");
		structuresButton = new JButton("Structures");
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				structuresButton.setEnabled(false);
				gameSpeedSlider.setEnabled(false);
				fileMenu.setEnabled(false);
				game.getGameMgr().setRunning(true);
			}
		});
		
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameSpeedSlider.setEnabled(true);
				structuresButton.setEnabled(true);
				fileMenu.setEnabled(true);
				game.getGameMgr().setRunning(false);
			}
		});
		
		structuresButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				structuresFrame.setVisible(true);
			}
		});
		
		optionsStartStopPanel.add(startButton);
		optionsStartStopPanel.add(stopButton);
		optionsPanel.add(blackCellsButton);
		optionsPanel.add(colorCellsButton);
		optionsPanel.add(structuresButton, BorderLayout.SOUTH);
	}
	
	
	private void initGameSpeedSlider() {
		gameSpeedSlider = new JSlider(JSlider.HORIZONTAL, 1, 4, 2);
		gameSpeedSlider.setMajorTickSpacing(1);
		gameSpeedSlider.setPaintLabels(true);
		gameSpeedSlider.setPaintLabels(true);
		gameSpeedSlider.setPaintTrack(true);
		
		gameSpeedSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				game.getGameMgr().setGameSpeedOption(gameSpeedSlider.getValue());
			}
		});
		
		optionsPanel.add(gameSpeedSlider);
	}
}
