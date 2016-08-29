
public class Main {
	
	int line = 1;
	
	private void printStat(String text) {
		System.out.println(line + " "+text);
		line++;
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.printStat("Hello There!!!");
		m.printStat("How are you?");
	}

}
