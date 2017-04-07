import javax.swing.JFrame;
public class Main extends JFrame{
	public Main(){
	    super("My First Window"); //Заголовок окна
	    setBounds(0, 0, 1200, 600); 
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	  }

	  public static void main(String[] args) { 
	    Main app = new Main(); 
	    app.setVisible(true); //С этого момента приложение запущено!
	  }
}
