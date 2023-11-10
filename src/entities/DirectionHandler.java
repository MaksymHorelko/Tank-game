package entities;

public class DirectionHandler {
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	public DirectionHandler() {
		this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;
	}

	public void changeDirection(String direction) {
		this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;

		switch (direction.toLowerCase()) {
		case "up":
			this.up = true;
			break;
		case "down":
			this.down = true;
			break;
		case "left":
			this.left = true;
			break;
		case "right":
			this.right = true;
			break;
		}
	}
	
	public String getDirection() {
		return up ? "up" : down ? "down" : left ? "left" : right ? "right" : "up";
	}
}
