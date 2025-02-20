public class ControlValidator {
    public static boolean validateStopTime(String jsonStopTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date stopDate = sdf.parse(jsonStopTime);
            return new Date().before(stopDate);
        } catch (ParseException e) {
            return false;
        }
    }
}