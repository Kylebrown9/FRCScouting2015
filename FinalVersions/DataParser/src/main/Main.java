package main;

public class Main {

	MainFrame mF;
	Runnable compile;
	
	
	public Main() {
		compile = new Runnable() {
			public void run() {
				compile();
			}
		};
		mF  = MainFrame.create("DataParser", compile);
	}
	
	public static void main(String[] argss) {
		@SuppressWarnings("unused")
		Main m = new Main();
	}
	
	public void compile() {
		SummaryBuilder.makeSummary(mF.getTeam());
	}
}