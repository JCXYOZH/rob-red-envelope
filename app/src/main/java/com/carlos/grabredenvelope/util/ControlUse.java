public class ControlUse {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ControlUse instance;

    public static ControlUse getInstance(Context context) {
        if (instance == null) {
            instance = new ControlUse(context);
        }
        return instance;
    }

    public boolean stopUse() {
        try {
            Date stopDate = sdf.parse("2030-12-31 00:00:00");
            return new Date().after(stopDate);
        } catch (ParseException e) {
            return false;
        }
    }
}