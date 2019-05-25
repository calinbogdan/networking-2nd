package com.calin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static LinkedList<String> ips = new LinkedList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./ips.txt");
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            ips.add(fileScanner.next());
        }

        Scanner consoleScanner = new Scanner(System.in);

        System.out.print("Start IP: ");
        String startAddress = consoleScanner.nextLine();

        System.out.print("Target IP: ");
        String targetAddress = consoleScanner.nextLine();

        System.out.print("Message: ");
        String message = consoleScanner.nextLine();

        Token token = new Token();
        token.source = startAddress;
        token.destination = targetAddress;
        token.message = message;
        token.messageHasArrived = false;
        token.isEmpty = false;

        token.addToHistory(startAddress);
        sendMessageTo(ipAddressAfter(startAddress), startAddress, token);
    }

    public static void sendMessageTo(String currentAddress, String targetAddress, Token token) {
        token.addToHistory(currentAddress);
        if (currentAddress.equals(targetAddress)) {
            token.message = null;
            token.isEmpty = true;

            System.out.println(token.getHistory());
        } else {
            if (currentAddress.equals(token.destination)) {
                System.out.println(String.format(token.message, "Message: %s"));
                token.messageHasArrived = true;
            }
            sendMessageTo(ipAddressAfter(currentAddress), targetAddress, token);
        }
    }

    public static String ipAddressAfter(String address) {
        int addressIndex = ips.indexOf(address);

        if (addressIndex == ips.size() - 1) {
            return ips.get(0);
        }
        return ips.get(addressIndex + 1);
    }
}
