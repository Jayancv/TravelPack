package org.jcv.cart.util;

import org.jcv.cart.model.Traveller;
import org.jcv.common.PaxType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TravellerUtils {


    public static List<Traveller> generateDummyTravellers(int adults, int teen, int children, int infants) {
        final AtomicInteger ID_GENERATOR = new AtomicInteger(1);
        List<Traveller> travellers = new ArrayList<>();
        for (int i = 1; i <= adults; i++) {
            travellers.add(createTraveller("TBD", PaxType.ADULT, 30, ID_GENERATOR));
        }

        for (int i = 1; i <= teen; i++) {
            travellers.add(createTraveller("TBD", PaxType.CHILD, 12, ID_GENERATOR));
        }

        for (int i = 1; i <= children; i++) {
            travellers.add(createTraveller("TBD", PaxType.CHILD, 5, ID_GENERATOR));
        }

        for (int i = 1; i <= infants; i++) {
            travellers.add(createTraveller("TBD", PaxType.INFANT, 1, ID_GENERATOR));
        }

        return travellers;
    }

    private static Traveller createTraveller(String name, PaxType type, int age, AtomicInteger ID_GENERATOR) {
        Traveller t = new Traveller();
        int id = ID_GENERATOR.getAndIncrement();
        t.setId((long) id);
        t.setPaxNo(id);
        t.setFirstName(name);
        t.setLastName(name);
        t.setType(type);
        t.setGender("M");
        t.setDateOfBirth(getDateYearsAgo(age));

        return t;
    }

    private static Date getDateYearsAgo(int years) {
        LocalDate date = LocalDate.now().minusYears(years);
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static Date getDateYearsAhead(int years) {
        LocalDate date = LocalDate.now().plusYears(years);
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
