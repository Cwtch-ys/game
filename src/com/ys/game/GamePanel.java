package com.ys.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 游戏面板
 * 
 * @author cwtch
 * 
 */
public class GamePanel extends JPanel {
	private int row;// 行
	private int col;// 列
	private int bombCount;// 炸弹数
	private final static int BLOCKWIDTH = 20;// 每个方格宽度
	private final static int BLOCKHEIGHT = 20;// 每个方格长度
	// 小方格
	private MyButton[][] btns;
	// 方格到周围八个方格的移动量二维数组
	private final static int[][] moveSize = { { -1, -1 }, { 0, -1 }, { 1, -1 },
			{ 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 } };

	/**
	 * 初始化游戏面板
	 * 
	 * @param rows
	 * @param cols
	 */
	public GamePanel(int rows, int cols) {
		this.row = rows;
		this.col = cols;
		// 根据列(级别)来指定炸弹数
		switch (cols) {
		case 9:
			this.bombCount = 10;
			break;
		case 16:
			this.bombCount = 40;
			break;
		default:
			this.bombCount = 99;
			break;
		}
		this.btns = new MyButton[rows][cols];
		init();
		this.setLayout(null);

	}

	/**
	 * 初识化小方格
	 */
	private void init() {
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				MyButton btn = new MyButton();
				btn.setRow(i);
				btn.setCol(j);
				// 设置每个小方格的边界
				btn.setBounds(j * BLOCKWIDTH, i * BLOCKHEIGHT, BLOCKWIDTH,
						BLOCKHEIGHT);
				// 绘制方格边框
				btn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				// 设置方格为透明
				btn.setOpaque(true);
				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						open((MyButton) e.getSource());

					}
				});

				// 将方格加入到容器中(即面板JPanel)
				this.add(btn);
				// 将方格存到类变量中,方便公用
				btns[i][j] = btn;
			}
		}
		random();

	}

	/**
	 * 返回窗体的大小
	 * 
	 * @return
	 */
	public int[] returnSize() {
		int[] a = { this.col * BLOCKWIDTH + 20, this.row * BLOCKHEIGHT + 65 };
		return a;
	}

	/**
	 * 随机产生炸弹
	 */
	private void random() {
		for (int i = 1; i <= this.bombCount; i++) {
			// 生成一个随机数表示行坐标
			int rRow = (int) (Math.random() * this.row);
			// 生成一个随机数表示列坐标
			int rCol = (int) (Math.random() * this.col);
			// 根据坐标确定的位置
			if (this.btns[rRow][rCol].isBobm()) {
				i--;
				continue;
			} else {
				this.btns[rRow][rCol].setBobm(true);
			}

		}
	}

	/**
	 * 验证方格是否越界
	 */
	private boolean parse(int row, int col) {
		return row >= 0 && row < this.row && col >= 0 && col < this.col;
	}

	/**
	 * 按钮点击事件
	 */
	private void open(MyButton button) {
		if (button.isOpen()) {
			return;
		}
		button.setOpen(true);

		if (button.isBobm()) {

			button.setIcon(new ImageIcon(System.getProperty("user.dir")
					+ "\\img\\bomb.jpg"));
			JOptionPane.showMessageDialog(null, "bom!!!", "提示",
					JOptionPane.ERROR_MESSAGE);
			removeAll();
			init();
			this.repaint();
			return;
		} else {
			button.setEnabled(false);
			// 记录周围炸弹数
			int count = 0;
			for (int[] size : moveSize) {
				int newRow = button.getRow() + size[0];
				int newCol = button.getCol() + size[1];
				// 验证是否越界和是否是炸弹
				if (parse(newRow, newCol)) {
					MyButton newButton = btns[newRow][newCol];
					if (newButton.isBobm()) {
						count++;
					}
				}
			}
			if (count > 0) {
				button.setText("" + count);
			} else {
				for (int[] size : moveSize) {
					int newRow = button.getRow() + size[0];
					int newCol = button.getCol() + size[1];
					// 验证是否越界和是否是炸弹
					if (parse(newRow, newCol)) {
						MyButton newButton = btns[newRow][newCol];
						if (!newButton.isBobm()) {
							open(newButton);
						}
					}
				}
			}
		}

	}
}
