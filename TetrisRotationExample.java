import java.util.ArrayList;

class TetrisShapes{
	public static void main(String[] args){
		// MAIN
	}
}

/* PARENT CLASS AND CHILD CLASSES */

abstract class Shape {

	protected int posX;
	protected int posY;
	protected ArrayList<char[][]> forms = new ArrayList<char[][]>();
	protected int currentFormIndex = 0;

	public Shape(int _posX, int _posY){
		this.posX = _posX;
		this.posY = _posY;
	}

	protected void rotate(){
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

}

class TShape extends Shape {

	public TShape(int _posX, int _posY){
		super(_posX, _posY);
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

	public LShape(int _posX, int _posY){
		super(_posX, _posY);
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

	public JShape(int _posX, int _posY){
		super(_posX, _posY);
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

	public ZShape(int _posX, int _posY){
		super(_posX, _posY);
		this.addForms();
	}

	private void addForms(){
		char[][] form1 = {
			{'Z','Z',' ',' '},
			{' ','Z','Z',' '},
			{' ',' ',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form1);
		char[][] form2 = {
			{' ',' ','Z',' '},
			{' ','Z','Z',' '},
			{' ','Z',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form2);
	}
}

class SShape extends Shape {

	public SShape(int _posX, int _posY){
		super(_posX, _posY);
		this.addForms();
	}

	private void addForms(){
		char[][] form1 = {
			{' ','S','S',' '},
			{'S','S',' ',' '},
			{' ',' ',' ',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form1);
		char[][] form2 = {
			{' ','S',' ',' '},
			{' ','S','S',' '},
			{' ',' ','S',' '},
			{' ',' ',' ',' '}
		};
		this.forms.add(form2);
	}
}

class IShape extends Shape {

	public IShape(int _posX, int _posY){
		super(_posX, _posY);
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

	public OShape(int _posX, int _posY){
		super(_posX, _posY);
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
