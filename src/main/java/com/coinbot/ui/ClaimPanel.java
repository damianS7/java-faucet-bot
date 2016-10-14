/*
 * Copyright (C) 2013 by danjian <josepwnz@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.coinbot.ui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import net.miginfocom.swing.MigLayout;

public class ClaimPanel extends JPanel {
	private JProgressBar progressBar;
	private JLabel lblName;

	public ClaimPanel(String faucet) {
        setMaximumSize(new Dimension((int) getMaximumSize().getWidth(), 35));
		setLayout(new MigLayout("", "[150px:120px:150px,left][grow,left][200px:200px:200px,center]", "[20px]"));
		
		lblName = new JLabel(faucet);
		add(lblName, "cell 0 0,growx");
		
		progressBar = new JProgressBar();
		add(progressBar, "cell 2 0,growx,aligny top");
		progressBar.setStringPainted(true);
	}
	
	public void nextStep(String step) {
		progressBar.setValue(progressBar.getValue()+1);
		progressBar.setString(step);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void ready() {
		progressBar.setValue(progressBar.getMaximum());
		progressBar.setString("Ready!");
	}
	
	public void done() {
		progressBar.setValue(progressBar.getMaximum());
		progressBar.setString("Done!");
	}
	
	public void reset() {
		progressBar.setValue(0);
		progressBar.setString("");
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
}
