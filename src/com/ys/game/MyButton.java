package com.ys.game;

import javax.swing.JButton;

public class MyButton extends JButton {
	private int row;//所在行
	private int col;//所在列

	private boolean isBobm = false;// 是否是炸弹
	private boolean isOpen = false;// 是否打开

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isBobm() {
		return isBobm;
	}

	public void setBobm(boolean isBobm) {
		this.isBobm = isBobm;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

}
