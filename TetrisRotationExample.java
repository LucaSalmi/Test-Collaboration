import java.util.ArrayList;

/*
  Istället för att beräkna rotationen så kan man skapa färdiga rotations-mönster.
  Inte lika snyggt kod-mässigt men betydligt enklare =)
  /Danne
*/

/* Requires parent class Shape */
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
			{ '',' ',' ',' '}
		}
		this.forms.add(form1);
		char[][] form2 = {
			{' ','T',' ',' '},
			{' ','T','T',' '},
			{' ','T',' ',' '},
			{ '',' ',' ',' '}
		}
		this.forms.add(form2);
		char[][] form3 = {
			{' ',' ',' ',' '},
			{'T','T','T',' '},
			{' ','T',' ',' '},
			{ '',' ',' ',' '}
		}
		this.forms.add(form3);
		char[][] form4 = {
			{' ','T',' ',' '},
			{'T','T',' ',' '},
			{' ','T',' ',' '},
			{ '',' ',' ',' '}
		}
		this.forms.add(form4);
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
