import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

class MenuPanel extends JPanel{

	JButton play = new JButton("");
	JButton help = new JButton("");
	JButton exit = new JButton("");

	Image  menubkg = new ImageIcon("images\\menu.png").getImage(); 
	
	ImageIcon playbtn = new ImageIcon("buttons\\play.png"); 
	ImageIcon helpbtn = new ImageIcon("buttons\\help.png");
	ImageIcon exitbtn = new ImageIcon("buttons\\exit.png");

	JPanel center = new JPanel(); 
	
	MenuPanel(){
		
		center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
		add(center); 
		
		
		play.setIcon(playbtn); 
		help.setIcon(helpbtn);
		exit.setIcon(exitbtn);
		
		
		center.add(play);
		center.add(help);
		center.add(exit);
				
		
		play.addMouseListener(new Click());
		help.addMouseListener(new Click());
		exit.addMouseListener(new Click());
		
	}
	
	class Click extends MouseAdapter{
	
		public void mouseClicked(MouseEvent me){
			if(me.getSource()== play){
				CteGame.cl.show(CteGame.cards, "GamePanel"); 
			}
			if(me.getSource()== help){
				CteGame.cl.show(CteGame.cards, "HelpPanel"); 
			}	
			if(me.getSource()== exit){
				System.exit(0);  
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g; 
		setFocusable(true);		
		
		g2d.drawImage(menubkg, 0,0,this.getWidth(),this.getHeight(),this);
		repaint();
	}
}

///////////////////////////////// Help Panel \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

class HelpPanel extends JPanel{

	Image helpbkg = new ImageIcon("images\\helpbkg.png").getImage(); //help image background
	JButton back = new JButton("");   
	ImageIcon imageIcon = new ImageIcon(new ImageIcon("buttons\\back.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));

	HelpPanel(){
						
		 
		back.setIcon(imageIcon);
		
		setFocusable(true); 
		add(back,BorderLayout.LINE_END);			
		
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
						CteGame.cl.show(CteGame.cards, "MenuPanel"); 
			}	
		  });
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(helpbkg, 0,0, this.getWidth(),this.getHeight(),this);
		repaint();
	}
}


/////////////////////////////////// ENDING PANEL \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

class ending extends JPanel{

	Image end = new ImageIcon("images\\end.png").getImage(); //help image background
	

	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(end, 0,0, this.getWidth(),this.getHeight(),this);
		repaint();
	}
}
		
	
/////////////////////////////////// GAME PANEL \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

class GamePanel extends JPanel{ 
	
	Image gamebkg = new ImageIcon("images\\gamebkg.png").getImage();
	Image gamebkg1 = new ImageIcon("images\\gamebkg1.png").getImage();
	Image gamebkg2 = new ImageIcon("images\\gamebkg2.png").getImage();
	Image basket  = new ImageIcon("images\\basket.png").getImage();
	Image water    = new ImageIcon("images\\water.png").getImage();
	Image seed    = new ImageIcon("images\\seed.png").getImage();
	Image soil    = new ImageIcon("images\\soil.png").getImage();
	Image bottle   = new ImageIcon("images\\bottle.png").getImage();
	Image sun   = new ImageIcon("images\\sun.png").getImage();
	Image chemical   = new ImageIcon("images\\chemical.png").getImage();
	Image ceg   = new ImageIcon("images\\ceg.png").getImage();
	Image gameOverbkg1= new ImageIcon("images\\gameOverbkg1.png").getImage();
	Image gameOverbkg2= new ImageIcon("images\\gameOverbkg2.png").getImage();
	Image gameOverbkg3= new ImageIcon("images\\gameOverbkg3.png").getImage();
	Image gameOverbkg4= new ImageIcon("images\\gameOverbkg4.png").getImage();
	
	Image[] things = {water,seed,soil,bottle,sun,chemical,ceg};
	
	Image tempbkg,z=water; 
	
	int x_basket,y_basket; 
	int x_egg,y_egg; 
	Random rand = new Random(); 
	Dimension screenSize;
	JLabel time;
	JLabel points;
	
	int pointsCount = 0;
	int timeleft = 120;
	int counter  = 0;
	
	boolean gameOver = false;
	
	GamePanel(){
		
		setLayout(null);
		setFocusable(true);
		int u=rand.nextInt(3);
		if(u==0)
		tempbkg = gamebkg;
		else if(u==1)
			tempbkg= gamebkg1;
		else
			tempbkg= gamebkg2;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     
		x_basket = screenSize.width-600; 
		y_basket = screenSize.height-140;
		x_egg = (int)rand.nextInt(screenSize.width-70);
		y_egg = 0;
	    time = new JLabel("<html>"+"TIME: 120"+"</html>");
		time.setBounds(20, 10, 100, 20); 
	    time.setFont(new Font("Serif", Font.BOLD, 14));
        time.setForeground(Color.GREEN);
	    
	    points = new JLabel("<html>"+"POINTS: 0"+"</html>");
		points.setBounds(150,10,100,20);
		points.setFont(new Font("Serif", Font.BOLD, 14));
        points.setForeground(Color.BLUE);
		
		add(time);
		add(points);
		
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				
				if(ke.getKeyCode() == ke.VK_LEFT & x_basket>10){
					x_basket-=33;
					repaint(); 
				}
				if(ke.getKeyCode() == ke.VK_RIGHT & x_basket<screenSize.width-100){
					x_basket+=33; 
					repaint();
				}
			}
		});	
	}
	
	void fallEgg(){
		 screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		if(y_egg >=screenSize.height){
			y_egg = 0;
			x_egg = rand.nextInt(screenSize.width-70);
			z=things[rand.nextInt(7)];
		}
		else
			y_egg+=5;
	}
	
	void updateTime(){
		counter++;
		if(counter == 100) 
		{
		   timeleft--;  
		   counter = 0; 
		}
		time.setText("<html>"+"TIME:"+timeleft+"</html>");
	}
	
	void detectCollision(){
		 screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle basketRect = new Rectangle(x_basket,y_basket,100,65); 
		Rectangle eggRect    = new Rectangle(x_egg,y_egg,45,67); 

		
		if(eggRect.intersects(basketRect)){
			if(z==water || z==sun || z==soil || z==seed)
			pointsCount+=5;
		else 
			pointsCount-=5;
			points.setText("<html>"+"POINTS:"+pointsCount+"</html>"); 
			y_egg = 0;
			x_egg = rand.nextInt( screenSize.width-70);
			z=things[rand.nextInt(7)];
			
					
			
		}

	}
	
	void checkGameOver(){
		if(timeleft <= 0)
		{
			String g="YOUR SCORE : "+pointsCount;
			JLabel yourScore = new JLabel("<html>"+g+"</html>",SwingConstants.CENTER);
			String g1="YOU PLANT "+pointsCount/20+ " TREE";
			JLabel tree = new JLabel("<html>"+g1+"</html>",SwingConstants.CENTER);
			Button b1=new Button("Next");
			
			b1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
						CteGame.cl.show(CteGame.cards, "ending"); 
			}
			});			
			if(pointsCount>=150)
			tempbkg = gameOverbkg1;
			else if(pointsCount>=80)
			tempbkg = gameOverbkg2;	
			else if(pointsCount>=55)
			tempbkg = gameOverbkg3;
			else 
			tempbkg = gameOverbkg4;
			tree.setBounds(600, 300, 300, 200);
			yourScore.setBounds(600, 250, 300, 200);
			yourScore.setFont(new Font("Serif", Font.PLAIN, 25));
			tree.setFont(new Font("Serif", Font.PLAIN, 25));
			gameOver = true;
			yourScore.setForeground(Color.RED);
			tree.setForeground(Color.MAGENTA);
			b1.setBounds(740,10,50,20);
			b1.setForeground(Color.RED);
			add(b1);
			add(yourScore);
			add(tree);
			
			
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(tempbkg,0,0,this.getWidth(),this.getHeight(),this); 
		
		
		
		
		checkGameOver();
		
		if(gameOver == false){
			setFocusable(true);
			grabFocus();
			updateTime();
			
			fallEgg();
			detectCollision();
		
			 
		
			g2d.drawImage(z, x_egg, y_egg,null); 
			g2d.drawImage(basket, x_basket, y_basket, null);
		}
		
		repaint();	
	}
}

/////////////////////////// Saviour of the Earth Game Panel \\\\\\\\\\\\\\\\\\\\\\\\\

public class CteGame extends JFrame{
	
	static MenuPanel mp = new MenuPanel();
	static HelpPanel hp = new HelpPanel();
	static GamePanel gp = new GamePanel();
	static ending e1 = new ending();
	static CardLayout cl = new CardLayout();
	
	static JPanel cards = new JPanel(); 
	
	CteGame(){
		
		cards.setLayout(cl);
		cards.add(mp, "MenuPanel");
		cards.add(hp, "HelpPanel");
		cards.add(gp, "GamePanel");
		cards.add(e1, "ending");
		cl.show(cards, "MenuPanel");
		add(cards); 
		
		setTitle("Saviour of the Earth");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setSize(screenSize.width, screenSize.height);
		
		setVisible(true);   	
	}
	
	public static void main(String args[]){
		new CteGame();
	}
}

