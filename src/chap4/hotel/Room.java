package chap4.hotel;

public class Room {
    private String id;
    private String customerName;

    public void setId(int floor, int number) {
        id = (floor <= 9 ? "0" + floor : "" + floor) + (number <= 9 ? "0" + number : "" + number);
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Room() {
    }

    public boolean in(String customerName) {
        if (this.customerName == null) {
            this.customerName = customerName;
            return true;
        }
        return false;
    }

    public boolean out() {
        if (customerName != null) {
            customerName = null;
            return true;
        }
        return false;
    }
}
