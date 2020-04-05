import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

    public static void main(String[] args) {



        Gigasecond gigasecond = new Gigasecond(LocalDateTime.of(2015, Month.JANUARY, 24, 23, 59, 59));


        System.out.println(gigasecond.getDateTime()); ;


    }
}
