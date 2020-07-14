package day15;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new C().pX();
	}

}

interface A{
	int x = 0;
}

class B{
	int x = 1;
}

class C extends B implements A{
	
	public void pX() {
		//System.out.println(x);出错，因为接口和继承是并列的
		System.out.println(A.x);
		//System.out.println(this.x);也出错
	}
	
}


interface Playable{
	void play();
}

interface Bounceable{
	void play();
}

interface Rollable extends Playable, Bounceable{
	Ball ball = new Ball("Pingpang");
}

class Ball implements Rollable{
	private String name;
	
	public Ball(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void play() {
		//ball = new Ball("test");
		System.out.println(ball.getName());
	}
}