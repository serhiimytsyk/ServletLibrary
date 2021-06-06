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
    public void testParseDate() {
        OrderService os = new OrderService();
        Assert.assertEquals(LocalDate.of(2031, 1, 1), os.parseDate("01.01.2031"));
    }
}
