package exercise;

import java.util.Random;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;

public class Data {
    private static final int COMPANIES_COUNT = 100;

    public static List<String> getCompanies() {
        Faker faker = new Faker(new Locale("en"), new Random(123));
        List<String> companies = new ArrayList<>();

        for (int i = 0; i < COMPANIES_COUNT; i++) {
            String company = faker.company().name() + " " + faker.company().suffix();
            companies.add(company);
        }

        return companies;
    }

    public static void main(String[] args) {
//        for (var company: getCompanies()) {
//            if (company.toLowerCase().contains("dicken")) {
//                System.out.println(company);
//            }
//        }
        String output = getCompanies().stream()
                .filter(symbol -> symbol.toLowerCase().contains("llc"))
                .collect(Collectors.joining("\n"));
        System.out.println(output);
    }
}
