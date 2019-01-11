import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui implements ActionListener{
	
	ArrayList <JButton> buttons = new ArrayList<JButton>();
	Icon blankIcon = new ImageIcon("imgs/square.png");
	Icon whitePc = new ImageIcon("imgs/white pc.png");
	Icon blackPc = new ImageIcon("imgs/black pc.png");
	
	public Gui() {
		JPanel p = new JPanel(new GridLayout(9,8,2,2));
		//p.add()
		JFrame f = new JFrame("Othello");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//ImageIcon square = new ImageIcon("square.png");	
		
		
		
		for(int i = 0; i < 72; i++)
			buttons.add(new JButton(blankIcon));
		
		
		
		for(JButton button:buttons) {
			//button.setBackground(Color.gray);
			//button.setBorder(BorderFactory.createEmptyBorder());
			button.addActionListener(this);
			button.setOpaque(true);
			p.add(button);
		}
		
		p.setSize(2500,2500);
		
		f.setPreferredSize(new Dimension(700,700));
		p.setVisible(true);
		f.add(p);
		f.pack();
		f.setVisible(true);		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(JButton button:buttons) {
			if(e.getSource() == button)
				button.setIcon(blackPc);
		}
	}

}
