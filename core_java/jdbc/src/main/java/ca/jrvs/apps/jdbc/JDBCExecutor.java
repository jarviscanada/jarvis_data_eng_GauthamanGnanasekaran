package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCExecutor {

  public static void main(String[] args) {
//    System.out.println("Hello Learning JDBC");
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport","postgres","password");
    try{
      Connection connection = dcm.getConnection();
//      Statement statement = connection.createStatement();
//      ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM CUSTOMER");
//      while(resultSet.next()){
//        System.out.println(resultSet.getInt(1));
//      }
      CustomerDAO customerDAO = new CustomerDAO(connection);
//      Customer customer = new Customer();
//        Customer customer = customerDAO.findById(1000);
//        Customer customer = customerDAO.findById(10000);
//        System.out.println(customer.getFirstName() + "" + customer.getLastName() + "" + customer.getEmail());
//        customer.setEmail("updatedemail@jrvis.ca");
//        System.out.println(customer.getFirstName() + "" + customer.getLastName() + "" + customer.getEmail());
//      customer.setFirstName("Gauthaman");
//      customer.setLastName("Karan");
//      customer.setEmail("gg@jrvis.ca");
//      customer.setAddress("1234 Main St");
//      customer.setCity("Arlington");
//      customer.setState("VA");
//      customer.setPhone("(555) 555-9845");
//      customer.setZipCode("22121");
//      customerDAO.create(customer);

      Customer customer = new Customer();
      Customer dbCustomer = customerDAO.create(customer);
      System.out.println(dbCustomer);
      dbCustomer = customerDAO.findById(dbCustomer.getID());
      dbCustomer.setEmail("whwew@gmail.com");
      dbCustomer = customerDAO.update(dbCustomer);
      System.out.println(dbCustomer);
      customerDAO.delete(dbCustomer.getID());

    }catch(SQLException e){
      e.printStackTrace();
    }

  }
}
