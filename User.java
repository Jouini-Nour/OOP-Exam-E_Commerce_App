import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private Role role;
    private double balance;
    private ArrayList<Order> orders = new ArrayList<>();
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = 0;   
        
    }
    public void addOrder(Order order){
        orders.add(order);
    }
    public ArrayList<Order> getOrders(){
        return orders;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
}