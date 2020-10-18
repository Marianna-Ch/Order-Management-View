package com.mariannach.order;

        import java.util.Date;
        import java.util.Objects;

        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.Id;
        import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ITEM")
class OrderItem {

    private @Id
    @GeneratedValue
    Long id;

    private String productName;
    private Integer orderId;
    private Integer quantity;
    private Integer price;

    OrderItem() {}

    OrderItem(String productName, Integer orderId, Integer quantity, Integer price) {

        this.productName = productName;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return productName;
    }

    public void setProduct_name(String product_name) {
        this.productName = product_name;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof OrderItem))
            return false;
        OrderItem order = (OrderItem) o;
        return Objects.equals(this.id, order.id) && Objects.equals(this.orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.orderId);
    }

    @Override
    public String toString() {
        return "OrderItem{" + "id=" + this.id + ", orderId='" + this.orderId + '\'' + ", productName=" + this.productName + ", quantity=" + this.quantity + ", price" + this.price + '}';
    }
}
