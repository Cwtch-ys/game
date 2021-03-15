package com.ys.game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	private GamePanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setTitle("扫雷");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		MyMenuBar menuBar = new MyMenuBar();
		menuBar.init();
		setJMenuBar(menuBar);
		contentPane = new GamePanel(9, 9);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		int[] size = contentPane.returnSize();
		setSize(size[0], size[1]);
	}

	/**
	 * 切换游戏面板
	 * 
	 * @param panel
	 */
	public void setPanel(GamePanel panel) {
		remove(contentPane);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		setContentPane(panel);
		int[] size = panel.returnSize();
		setSize(size[0], size[1]);
		repaint();
	}
}
