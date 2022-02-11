public class DoubleDead {
private void doTweedledee() {
doTweedledumb();
}
private void doTweedledumb() {
doTweedledee();
}
public static void main(String[] args) {
System.out.println("running DoubleDead");
}
}
