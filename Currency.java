package com.programs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Currency {
        private static final Map<String, Double> exchangeRates = new HashMap<>();
        private static final List<String> availableCurrencies = new ArrayList<>();

        static {
            availableCurrencies.add("INR");
            availableCurrencies.add("USD");
            availableCurrencies.add("EUR");
            availableCurrencies.add("GBP");

            exchangeRates.put("INR", 74.52);
            exchangeRates.put("USD", 1.0);
            exchangeRates.put("EUR", 0.85);
            exchangeRates.put("GBP", 0.73);
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            boolean continueConversion = true;

            while (continueConversion) {
                System.out.println("Available currencies: USD, EUR, GBP, INR");

                System.out.print("Enter base currency: ");
                String baseCurrency = scanner.nextLine().toUpperCase();

                System.out.print("Enter target currency: ");
                String targetCurrency = scanner.nextLine().toUpperCase();

                if (!availableCurrencies.contains(baseCurrency) || !availableCurrencies.contains(targetCurrency)) {
                    System.out.println("Invalid currency selection.");
                    continue;
                }

                System.out.print("Enter amount to convert: ");
                double amountToConvert = getValidAmount(scanner);

                double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);
                if (exchangeRate == -1.0) {
                    System.out.println("Failed to fetch exchange rate data. Please try again later.");
                    continue;
                }

                double convertedAmount = amountToConvert * exchangeRate;

                System.out.printf("%.2f %s is equivalent to %.2f %s\n",
                        amountToConvert, baseCurrency, convertedAmount, targetCurrency);

                scanner.nextLine();
            }
        }

        private static double getValidAmount(Scanner scanner) {
            double amount = -1.0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    amount = scanner.nextDouble();
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid numeric amount.");
                    scanner.next();
                }
            }

            return amount;
        }

        private static double fetchExchangeRate(String baseCurrency, String targetCurrency) {
            if (baseCurrency.equals(targetCurrency)) {
                return 1.0;
            } else if (exchangeRates.containsKey(baseCurrency) && exchangeRates.containsKey(targetCurrency)) {
                return exchangeRates.get(targetCurrency) / exchangeRates.get(baseCurrency);
            }
            return -1.0;
        }
    }
