package org.example;

public class Main {
    public static void main(String[] args) {
        // Contoh penggunaan kelas Wallet
        Wallet myWallet = new Wallet("John Doe");
        myWallet.addMoney(10000);
        myWallet.addMoney(5000);
        myWallet.takeMoneys(20000);
        myWallet.addCoin(500);
        myWallet.addCard("Credit Card");
        myWallet.addCard("KTM");
        myWallet.takeCard("Credit Card");
        myWallet.takeCoins(500);
        myWallet.takeMoneys(10000);
        System.out.println("Jumlah uang di dompet: " + myWallet.getMoneys());
        System.out.println("Jumlah coin di dompet: " + myWallet.getCoins());
        System.out.println("Kartu: " + myWallet.getCards());
        System.out.println("Total uang di dompet: " + myWallet.getMoneyAvailable());

    }
}