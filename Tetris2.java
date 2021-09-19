import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;

class Tetris2 {

	private static final int SCREEN_SIZE_X = 600;
	private static final int SCREEN_SIZE_Y = 800;

	public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        MyPanel myPanel = new MyPanel(SCREEN_SIZE_X, SCREEN_SIZE_Y);
        jFrame.add(myPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(SCREEN_SIZE_X, SCREEN_SIZE_Y);
    }

}

class MyPanel extends JPanel implements ActionListener, KeyListener {

	private static Graphics2D gfx;

    private final int SCREEN_SIZE_X;
	private final int SCREEN_SIZE_Y;
	private final int FPS = 24;
    private Timer gameLoop = new Timer(1000/FPS, this);

    private static final int SQUARE_SIZE = 20;
    private final int uniqueShapes = 7;
    private ArrayList<GameObject> shapes = new ArrayList<GameObject>();
    private GameObject currentShape;

    public MyPanel (int screenSizeX, int screenSizeY) {

		this.SCREEN_SIZE_X = screenSizeX;
        this.SCREEN_SIZE_Y = screenSizeY;
        this.gameLoop.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.init();
    }

    public static int getSquareSize(){
    	return SQUARE_SIZE; // MyPanel.getSquareSize();
    }

    public static Graphics2D getGraphics2D(){
    	return gfx;
    }

    private void init(){
    	int spawnX = this.SCREEN_SIZE_X/2;
    	int spawnY = SQUARE_SIZE*11;
    	int randomInt = MyMath.randomIntByInterval(1, this.uniqueShapes);
    	switch(randomInt){
    		case 1:
    			currentShape = new TShape(spawnX, spawnY, Color.RED);
    			break;
    		case 2:
    			currentShape = new LShape(spawnX, spawnY, Color.BLUE);
    			break;
    		case 3:
    			currentShape = new JShape(spawnX, spawnY, Color.GREEN);
    			break;
    		case 4:
    			currentShape = new ZShape(spawnX, spawnY, Color.YELLOW);
    			break;
    		case 5:
    			currentShape = new SShape(spawnX, spawnY, Color.MAGENTA);
    			break;
    		case 6:
    			currentShape = new IShape(spawnX, spawnY, Color.ORANGE);
    			break;
    		default:
    			currentShape = new OShape(spawnX, spawnY, Color.PINK);
    			break;
    	}
    	this.shapes.add(currentShape);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gfx = (Graphics2D) g;

        //Background
        gfx.setColor(Color.GRAY);
        gfx.fillRect(0,0, this.SCREEN_SIZE_X, this.SCREEN_SIZE_Y);
        //Playfield
        gfx.setColor(Color.BLACK);
        gfx.fillRect(SQUARE_SIZE*10, SQUARE_SIZE*10, SQUARE_SIZE*10, SQUARE_SIZE*20);
        //Objects
        for(GameObject shape : this.shapes){
    		shape.draw();
    	}

    }

    public void up() {
        this.currentShape.move(0, -SQUARE_SIZE);
    }

    public void down() {
        this.currentShape.move(0, SQUARE_SIZE);
    }

    public void left() {
        this.currentShape.move(-SQUARE_SIZE, 0);
    }

    public void right() {
        this.currentShape.move(SQUARE_SIZE, 0);
    }

    public void rotate() {
        this.currentShape.rotate();
    }

    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){
            up(); //Cannot really move upwards in tetris. This was for testing only.
        }
        if(code == KeyEvent.VK_DOWN){
            down();
        }
        if(code == KeyEvent.VK_LEFT){
            left();
        }
        if(code == KeyEvent.VK_RIGHT){
            right();
        }
        if(code == KeyEvent.VK_SPACE){
            rotate();
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}

/* PARENT CLASS AND CHILD CLASSES */

abstract class GameObject {

	protected int posX;
	protected int posY;

	public GameObject(int _posX, int _posY){
		this.posX = _posX;
		this.posY = _posY;
	}

	public void move(int difX, int difY){
		this.posX += difX;
		this.posY += difY;
	}

	public int getPosX(){
		return this.posX;
	}

	public int getPosY(){
		return this.posY;
	}

	//OVERRIDE THIS
	public void rotate(){}

	//OVERRIDE THIS
	public void draw(){}

}

class Block extends GameObject {

	private Color color;

	public Block(int _posX, int _posY, Color _color){
		super(_posX, _posY);
		this.color = _color;
	}

	public void draw(){
		Graphics2D gfx = MyPanel.getGraphics2D();
		gfx.setColor(this.color);
		gfx.fillRect(this.posX, this.posY, MyPanel.getSquareSize(), MyPanel.getSquareSize());
	}

}

abstract class Shape extends GameObject {

	protected ArrayList<char[][]> forms = new ArrayList<char[][]>();
	protected int currentFormIndex = 0;
	protected final int centerX = 0;
	protected final int centerY = 0;
	protected Color color;

	public Shape(int _posX, int _posY, Color _color){
		super(_posX, _posY);
		this.color = _color;
	}

	public void rotate(){
		//Increment index
		this.currentFormIndex++;
		//Set index to 0 if higher than last index-position
		if(this.currentFormIndex >= this.forms.size()){
			this.currentFormIndex = 0;
		}
	}

	public char[][] getCurrentForm(){
		//Returns the current form
		return this.forms.get(currentFormIndex);
	}

	public void draw(){
		char[][] form = this.getCurrentForm();
		char letter = form[this.centerY][this.centerX];
		ArrayList<GameObject> blocks = new ArrayList<GameObject>();
		for(int y = 0; y < form.length; y++){
			for(int x = 0; x < form.length; x++){
				if(form[y][x] != ' '){
					blocks.add(new Block(this.posX+(x*MyPanel.getSquareSize()), this.posY+(y*MyPanel.getSquareSize()), this.color));
				}
			}
		}
		for(GameObject block : blocks){
			block.draw();
		}
	}

}

class TShape extends Shape {

	public TShape(int _posX, int _posY, Color _color){
		super(_posX, _posY, _color);
		this.addForms();
	}

	private void addForms(){
		char[][] form1 = {
			{' ','T',' ',' '},
			{'T','T','T',' '},
			{' ',' ',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form1);
		char[][] form2 = {
			{' ','T',' ',' '},
			{' ','T','T',' '},
			{' ','T',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form2);
		char[][] form3 = {
			{' ',' ',' ',' '},
			{'T','T','T',' '},
			{' ','T',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form3);
		char[][] form4 = {
			{' ','T',' ',' '},
			{'T','T',' ',' '},
			{' ','T',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form4);
	}
}

class LShape extends Shape {

	public LShape(int _posX, int _posY, Color _color){
		super(_posX, _posY, _color);
		this.addForms();
	}

	private void addForms(){
		char[][] form1 = {
			{' ','L',' ',' '},
			{' ','L',' ',' '},
			{' ','L','L',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form1);
		char[][] form2 = {
			{' ',' ',' ',' '},
			{'L','L','L',' '},
			{'L',' ',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form2);
		char[][] form3 = {
			{'L','L',' ',' '},
			{' ','L',' ',' '},
			{' ','L',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form3);
		char[][] form4 = {
			{' ',' ','L',' '},
			{'L','L','L',' '},
			{' ',' ',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form4);
	}
}

class JShape extends Shape {

	public JShape(int _posX, int _posY, Color _color){
		super(_posX, _posY, _color);
		this.addForms();
	}

	private void addForms(){
		char[][] form1 = {
			{' ','J',' ',' '},
			{' ','J',' ',' '},
			{'J','J',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form1);
		char[][] form2 = {
			{'J',' ',' ',' '},
			{'J','J','J',' '},
			{' ',' ',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form2);
		char[][] form3 = {
			{' ','J','J',' '},
			{' ','J',' ',' '},
			{' ','J',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form3);
		char[][] form4 = {
			{' ',' ',' ',' '},
			{'J','J','J',' '},
			{' ',' ','J',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form4);
	}
}

class ZShape extends Shape {

	public ZShape(int _posX, int _posY, Color _color){
		super(_posX, _posY, _color);
		this.addForms();
	}

	private void addForms(){
		char[][] form1 = {
			{' ',' ',' ',' '},
			{'Z','Z',' ',' '},
			{' ','Z','Z',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form1);
		char[][] form2 = {
			{' ','Z',' ',' '},
			{'Z','Z',' ',' '},
			{'Z',' ',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form2);
	}
}

class SShape extends Shape {

	public SShape(int _posX, int _posY, Color _color){
		super(_posX, _posY, _color);
		this.addForms();
	}

	private void addForms(){
		char[][] form1 = {
			{' ',' ',' ',' '},
			{' ','S','S',' '},
			{'S','S',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form1);
		char[][] form2 = {
			{'S',' ',' ',' '},
			{'S','S',' ',' '},
			{' ','S',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form2);
	}
}

class IShape extends Shape {

	public IShape(int _posX, int _posY, Color _color){
		super(_posX, _posY, _color);
		this.addForms();
	}

	private void addForms(){
		char[][] form1 = {
			{' ','I',' ',' '},
			{' ','I',' ',' '},
			{' ','I',' ',' '},
			{' ','I',' ',' '}
		};
		this.forms.add(form1);
		char[][] form2 = {
			{' ',' ',' ',' '},
			{'I','I','I','I'},
			{' ',' ',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form2);
	}
}

class OShape extends Shape {

	public OShape(int _posX, int _posY, Color _color){
		super(_posX, _posY, _color);
		this.addForms();
	}

	private void addForms(){
		char[][] form1 = {
			{' ',' ',' ',' '},
			{' ','O','O',' '},
			{' ','O','O',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form1);
	}
}

class MyMath {

    private static Random random = new Random();

    public static int randomIntByInterval(int min, int max) {
        return random.nextInt(max+1 - min) + min;
    }

}
