package theredchessboard;

public class App {
    public String testExample() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        // System.out.println(new App().testExample());
        Board board = new Board(0, 0, "among_us");
    }
}
