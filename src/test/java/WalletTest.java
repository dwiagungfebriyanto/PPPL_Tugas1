import org.example.Wallet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {

    @Test
    void testWallet() {
        Wallet myWallet = new Wallet("DAF");
        int moneyTotal = myWallet.getMoneys().keySet().stream().mapToInt(key -> myWallet.getMoneys().get(key)).sum();
        int coinTotal = myWallet.getCoins().keySet().stream().mapToInt(key -> myWallet.getCoins().get(key)).sum();

        assertAll(
                () -> assertEquals("DAF", myWallet.getOwner()),
                () -> assertEquals(3, myWallet.getCoins().size()),
                () -> assertEquals(7, myWallet.getMoneys().size()),
                () -> assertEquals(0, moneyTotal),
                () -> assertEquals(0, coinTotal),
                () -> assertEquals(0, myWallet.getCards().size())
        );
    }

    @Test
    void testAddCard() {
        Wallet myWallet = new Wallet("DAF");
        myWallet.addCard("KTM");
        myWallet.addCard("Credit Card");

        assertAll(
                () -> assertNotEquals(0, myWallet.getCards().size()),
                () -> assertTrue(myWallet.getCards().contains("KTM")),
                () -> assertTrue(myWallet.getCards().contains("Credit Card"))
        );
    }

    @Test
    void testTakeCard() {
        Wallet myWallet = new Wallet("DAF");
        myWallet.addCard("KTM");
        myWallet.takeCard("KTM");

        assertAll(
                () -> assertEquals(0, myWallet.getCards().size()),
                () -> assertFalse(myWallet.getCards().contains("KTM"))
        );
    }

    @Test
    void testAddMoney() {
        Wallet myWallet = new Wallet("DAF");
        myWallet.addMoney(1000);
        myWallet.addMoney(2000);
        myWallet.addMoney(5000);
        myWallet.addMoney(10000);
        myWallet.addMoney(20000);
        myWallet.addMoney(50000);
        myWallet.addMoney(100000);
        myWallet.addMoney(1);

        assertAll(
                () -> assertEquals(1, myWallet.getMoneys().get(1000)),
                () -> assertEquals(1, myWallet.getMoneys().get(2000)),
                () -> assertEquals(1, myWallet.getMoneys().get(5000)),
                () -> assertEquals(1, myWallet.getMoneys().get(10000)),
                () -> assertEquals(1, myWallet.getMoneys().get(20000)),
                () -> assertEquals(1, myWallet.getMoneys().get(50000)),
                () -> assertEquals(1, myWallet.getMoneys().get(100000)),
                () -> assertNull(myWallet.getMoneys().get(1))
        );
    }

    @Test
    void testAddCoin() {
        Wallet myWallet = new Wallet("DAF");
        myWallet.addCoin(100);
        myWallet.addCoin(500);
        myWallet.addCoin(1000);
        myWallet.addCoin(1);

        assertAll(
                () -> assertEquals(1, myWallet.getCoins().get(100)),
                () -> assertEquals(1, myWallet.getCoins().get(500)),
                () -> assertEquals(1, myWallet.getCoins().get(1000)),
                () -> assertNull(myWallet.getCoins().get(1))
        );
    }

    @Test
    void testTakeCoins() {
        Wallet myWallet = new Wallet("DAF");
        myWallet.addCoin(100);
        myWallet.addCoin(1000);
        myWallet.takeCoins(500);
        myWallet.takeCoins(1000);

        assertAll(
                () -> assertEquals(0, myWallet.getCoins().get(500),
                        "Koin tetap berkurang walaupun di dalam wallet jumlahnya 0"),
                () -> assertEquals(1, myWallet.getCoins().get(100)),
                () -> assertEquals(0, myWallet.getCoins().get(1000))
        );
    }

    @Test
    void testTakeMoneys() {
        Wallet myWallet = new Wallet("DAF");
        myWallet.addMoney(1000);
        myWallet.addMoney(2000);
        myWallet.addMoney(5000);
        myWallet.addMoney(10000);
        myWallet.addMoney(20000);
        myWallet.addMoney(50000);
        myWallet.takeMoneys(100000);
        myWallet.takeMoneys(5000);

        assertAll(
                () -> assertEquals(0, myWallet.getMoneys().get(100000),
                        "Uang tetap berkurang walaupun di dalam wallet jumlahnya 0"),
                () -> assertEquals(0, myWallet.getMoneys().get(5000)),
                () -> assertEquals(1, myWallet.getMoneys().get(1000)),
                () -> assertEquals(1, myWallet.getMoneys().get(2000)),
                () -> assertEquals(1, myWallet.getMoneys().get(10000)),
                () -> assertEquals(1, myWallet.getMoneys().get(20000)),
                () -> assertEquals(1, myWallet.getMoneys().get(50000))
        );
    }

    @Test
    void testCalculateCoins() {
        Wallet myWallet = new Wallet("DAF");
        myWallet.addCoin(100);
        myWallet.addCoin(500);
        myWallet.addCoin(1000);

        assertAll(
                () -> assertNotEquals(0, myWallet.calculateCoins()),
                () -> assertEquals(1600, myWallet.calculateCoins())
        );
    }

    @Test
    void testCalculateMoneys() {
        Wallet myWallet = new Wallet("DAF");
        myWallet.addMoney(1000);
        myWallet.addMoney(5000);
        myWallet.addMoney(20000);
        myWallet.addMoney(100000);

        assertAll(
                () -> assertNotEquals(0, myWallet.calculateMoneys()),
                () -> assertEquals(126000, myWallet.calculateMoneys())
        );
    }

    @Test
    void testGetMoneyAvailable() {
        Wallet myWallet = new Wallet("DAF");
        myWallet.addMoney(1000);
        myWallet.addMoney(2000);
        myWallet.addMoney(5000);
        myWallet.addMoney(10000);
        myWallet.addMoney(20000);
        myWallet.addMoney(50000);
        myWallet.addMoney(100000);
        myWallet.addCoin(100);
        myWallet.addCoin(500);
        myWallet.addCoin(1000);

        assertAll(
                () -> assertNotEquals(0, myWallet.getMoneyAvailable()),
                () -> assertEquals(189600, myWallet.getMoneyAvailable())
        );
    }
}