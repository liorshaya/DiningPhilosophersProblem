public class Utils {

    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
