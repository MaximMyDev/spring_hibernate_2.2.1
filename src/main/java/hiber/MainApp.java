package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         //System.out.println("Car = "+user.);
         System.out.println();
      }

      //+----------------------------
      CarService carService = context.getBean(CarService.class);
      carService.add(new Car("audi", 123, user1));
      carService.add(new Car("bmw", 234, user2));
      carService.add(new Car("ford", 345, user3));
      carService.add(new Car("mustang", 456, user4));

      User userGet1 = carService.getUserByNameSeries("bmw", 234);
      System.out.println("Id = "+userGet1.getId());
      System.out.println("First Name = "+userGet1.getFirstName());
      System.out.println("Last Name = "+userGet1.getLastName());
      System.out.println("Email = "+userGet1.getEmail());

      User userGet2 = carService.getUserByNameSeries("mustang", 456);
      System.out.println("Id = "+userGet2.getId());
      System.out.println("First Name = "+userGet2.getFirstName());
      System.out.println("Last Name = "+userGet2.getLastName());
      System.out.println("Email = "+userGet2.getEmail());
      //+----------------------------
      context.close();
   }
}
