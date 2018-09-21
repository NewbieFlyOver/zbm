package wmq.fly.genericType;

public class BookColor extends Book {
	
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "BookColor [color=" + color + "]";
	}
	
	

}
