package com.ys.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 菜单栏
 * 
 * @author cwtch
 * 
 */
public class MyMenuBar extends JMenuBar {
	private JMenu menu;
	private JMenuItem lowItem;// 9x9
	private JMenuItem midItem;// 16x16
	private JMenuItem highItem;// 16x30
	private JMenuItem escItem;

	/**
	 * 菜单栏初始化
	 */
	public void init() {
		menu = new JMenu("游戏");
		lowItem = new JMenuItem("初级");
		midItem = new JMenuItem("中级");
		highItem = new JMenuItem("高级");
		escItem = new JMenuItem("退出");
		addListen();
		menu.add(lowItem);
		menu.add(midItem);
		menu.add(highItem);
		menu.addSeparator();// 分割线
		menu.add(escItem);
		this.add(menu);

	}

	/**
	 * 添加监听事件
	 */
	public void addListen() {
		escItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		lowItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Main main = (Main) getParent().getParent().getParent();
				main.setPanel(new GamePanel(9, 9));

			}
		});
		midItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Main main = (Main) getParent().getParent().getParent();
				main.setPanel(new GamePanel(16, 16));

			}
		});
		highItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Main main = (Main) getParent().getParent().getParent();
				main.setPanel(new GamePanel(16, 30));

			}
		});
	}
}
