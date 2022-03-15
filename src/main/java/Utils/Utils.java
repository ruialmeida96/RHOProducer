package Utils;

import org.apache.kafka.common.protocol.types.Field;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utils {

    private static NumberFormat nf = NumberFormat.getInstance();
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.000");
    private static Random random = new Random();
    private static DateTimeFormatter formato_data = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    private static DateTimeFormatter formato_time = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static Date return_current_date() {
        java.util.Date data = new java.util.Date(System.currentTimeMillis());
        return new Date(data.getTime());
    }

    public static Time return_current_time() {
        LocalTime localTime = LocalTime.now();
        return Time.valueOf(localTime.format(formato_time));
    }

    public static int returnRandomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    /*public static double returnRandomDouble(int min, int max) {
        double valor = min + (max - min) * random.nextDouble();
        String str = decimalFormat.format(valor);
        try {
            return nf.parse(str).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
        //return Double.parseDouble(decimalFormat.format(valor));
        //return Double.parseDouble(String.format("%.3f", valor));
    }*/
    public static double returnRandomDouble(int min, int max) {
        double valor = min + (max - min) * random.nextDouble();
        BigDecimal bd = BigDecimal.valueOf(valor).setScale(3,RoundingMode.HALF_UP);
        //bd = bd.setScale(3);
        return bd.doubleValue();
    }

}
