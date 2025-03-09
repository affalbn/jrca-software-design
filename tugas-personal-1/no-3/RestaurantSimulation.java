// Interface untuk Pelanggan
interface ICustomer {
    void makeOrder(String[] dishes);
    void receiveFood(String[] dishes);
    void payBill(double amount);
    String[] getOrder();
}

// Interface untuk Server
interface IServer {
    String[] takeOrder(ICustomer customer);
    void sendOrderToKitchen(String[] order, ICook cook);
    void serveFood(String[] dishes, ICustomer customer);
    double generateBill(String[] order);
    void receivePayment(double amount);
}

// Interface untuk Juru Masak
interface ICook {
    void prepareFood(String[] order);
    String[] getOrder();
}

//Implementasi interface
class Customer implements ICustomer {
    private String[] order;

    @Override
    public void makeOrder(String[] dishes) {
        this.order = dishes;
        System.out.println("Customer make order: " + String.join(",", dishes));
    }

    @Override
    public String[] getOrder() {
        return this.order;
    }

    @Override
    public void receiveFood(String[] dishes) {
        System.out.println("Customer receive food: " + String.join(",", dishes));
    }

    @Override
    public void payBill(double amount) {
        System.out.println("Customer pay bill, amount: " + amount);
    }
}

class Server implements IServer {
    private String[] order;
    private double billAmount;

    @Override
    public String[] takeOrder(ICustomer customer) {
        System.out.println("Server take order from customer");
        this.order = customer.getOrder();
        return this.order;
    }

    @Override
    public void sendOrderToKitchen(String[] order, ICook cook) {
        this.order = order;
        System.out.println("Server send order to kitchen");
        cook.prepareFood(order);
    }

    @Override
    public void serveFood(String[] dishes, ICustomer customer) {
        System.out.println("Server serve food to customer");
        customer.receiveFood(dishes);
    }

    @Override
    public double generateBill(String[] order) {
        double totalPrice = 0;
        for (String item : order) {
            if (item.equals("Nasi Goreng")) {
                totalPrice += 15000;
            } else if (item.equals("Ayam Bakar")) {
                totalPrice += 20000;
            }
        }
        System.out.println("Server generate bill, total price " + totalPrice);
        return totalPrice;
    }

    @Override
    public void receivePayment(double amount) {
        System.out.println("Server receive payment, amount: " + amount);
    }
}

class Cook implements ICook {
    private String[] order;

    @Override
    public void prepareFood(String[] order) {
        this.order = order;
        System.out.println("Cook prepare food: " + String.join(",", order));
    }

    @Override
    public String[] getOrder() {
        return this.order;
    }
}

public class RestaurantSimulation {

    public static void simulateOrderProcess(ICustomer customer, IServer server, ICook cook) {
        //1. Pelanggan membuat pesanan
        String[] order = {"Nasi Goreng", "Ayam Bakar"};
        customer.makeOrder(order);

        //2. Pelayan mengambil pesanan.
        String[] customerOrder = server.takeOrder(customer);

        //3. Pelayan mengirim pesanan ke dapur.
        server.sendOrderToKitchen(customerOrder, cook);

        //4. Pelayan menyajikan makanan setelah dimasak
        server.serveFood(customerOrder, customer);

        //5. Pelayan membuat tagihan
        double billAmount = server.generateBill(customerOrder);

        //6. Pelanggan membayar tagihan.
        customer.payBill(billAmount);
        server.receivePayment(billAmount);

        System.out.println("Order finish.");
    }

    public static void main(String[] args) {
        //Instantiate
        ICustomer customer = new Customer();
        IServer server = new Server();
        ICook cook = new Cook();

        simulateOrderProcess(customer, server, cook);
    }
}
