package smytsyk.final_project.library.entitiy;

import java.time.LocalDate;

/**
 * Book order
 * Contains information about reader, book, return date, and status
 */
public class Order implements Entity {
    private int id;
    private int readerId;
    private int bookId;
    private LocalDate returnDate;
    private int orderStatusId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Order order = new Order();

        public Order build() {
            return order;
        }

        public Builder id(int id) {
            order.id = id;
            return this;
        }

        public Builder readerId(int readerId) {
            order.readerId = readerId;
            return this;
        }

        public Builder bookId(int bookId) {
            order.bookId = bookId;
            return this;
        }

        public Builder returnDate(LocalDate returnDate) {
            order.returnDate = returnDate;
            return this;
        }

        public Builder orderStatusId(int orderStatusId) {
            order.orderStatusId = orderStatusId;
            return this;
        }
    }
}
