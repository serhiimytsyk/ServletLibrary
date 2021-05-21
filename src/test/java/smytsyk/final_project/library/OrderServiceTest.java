package smytsyk.final_project.library;

import org.junit.Assert;
import org.junit.Test;
import smytsyk.final_project.library.service.OrderService;

import java.time.LocalDate;

public class OrderServiceTest {
    @Test
    public void testFine() {
        LocalDate now = LocalDate.now();
        Assert.assertEquals("10", OrderService.countFine(now.minusDays(1), now));
        Assert.assertEquals("20", OrderService.countFine(now.minusDays(2), now));
        Assert.assertEquals("100", OrderService.countFine(now.minusDays(10), now));
        Assert.assertEquals("310", OrderService.countFine(now.minusDays(31), now));
        Assert.assertEquals("4000", OrderService.countFine(now.minusDays(400), now));
        Assert.assertEquals("10000000", OrderService.countFine(now.minusDays(1000000), now));
    }

    @Test
    public void testValidDate() {
        OrderService os = new OrderService();
        Assert.assertFalse(os.isValidDate(null));
        Assert.assertFalse(os.isValidDate(""));
        Assert.assertFalse(os.isValidDate("1.1.2031"));
        Assert.assertFalse(os.isValidDate("01.1.2031"));
        Assert.assertFalse(os.isValidDate("1.01.2031"));
        Assert.assertTrue(os.isValidDate("01.01.2031"));
        Assert.assertFalse(os.isValidDate("01.01.2021"));
        Assert.assertTrue(os.isValidDate("11.12.2031"));
        Assert.assertFalse(os.isValidDate("01.00.2031"));
        Assert.assertFalse(os.isValidDate("01.13.2031"));
        Assert.assertFalse(os.isValidDate("00.01.2031"));
        Assert.assertFalse(os.isValidDate("32.01.2031"));
    }

    @Test
    public void testParseDate() {
        OrderService os = new OrderService();
        Assert.assertEquals(LocalDate.of(2031, 1, 1), os.parseDate("01.01.2031"));
    }
}
