import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Gigasecond {

    private static long SECONDS_IN_GIGASECOND = 1_000_000_000L;

    private LocalDateTime moment;




    Gigasecond(LocalDate moment) {
        this(moment.atStartOfDay());
    }

    Gigasecond(LocalDateTime moment) {
        this.moment = moment.plusSeconds(SECONDS_IN_GIGASECOND);
    }

    LocalDateTime getDateTime() {
        return moment;
    }


}
