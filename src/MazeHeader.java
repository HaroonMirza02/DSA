/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mazegenerator;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MazeHeader extends JLabel {

	public MazeHeader(String title) {
		Font font = new Font(Font.SERIF, Font.BOLD, 32);
		setFont(font);
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);
		setText(title);
	}
}
