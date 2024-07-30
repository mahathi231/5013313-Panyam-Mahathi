Exercise-1:
-----------
package com.example.singleton;
public class Logger {
    private static Logger instance;
    private Logger() {
    }
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                      if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
    public void log(String message) {
        System.out.println(message);
    }
}

package com.example.singleton.test;
import com.example.singleton.Logger;
public class LoggerTest {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        logger1.log("Logging from logger1.");
        logger2.log("Logging from logger2.");
        if (logger1 == logger2) {
            System.out.println("Both logger1 and logger2 are the same instance.");
        } else {
            System.out.println("logger1 and logger2 are different instances.");
        }
    }
}
-----------------------------------------------------------------------------------------------------------
Exercise-2:
-----------------------------------------------------------------------------------------------------------
package com.example.factory;
public interface Document {
    void open();
}
public interface WordDocument extends Document {
}

public interface PdfDocument extends Document {
}

public interface ExcelDocument extends Document {
}
package com.example.factory;

public class WordDocumentImpl implements WordDocument {
    public void open() {
        System.out.println("Opening a Word document.");
    }
}
package com.example.factory;

public class PdfDocumentImpl implements PdfDocument {
    public void open() {
        System.out.println("Opening a PDF document.");
    }
}
package com.example.factory;

public class ExcelDocumentImpl implements ExcelDocument {
    public void open() {
        System.out.println("Opening an Excel document.");
    }
}
package com.example.factory;

public abstract class DocumentFactory {
    public abstract Document createDocument();
}
package com.example.factory;
public class WordDocumentFactory extends DocumentFactory {
    public WordDocument createDocument() {
        return new WordDocumentImpl();
    }
}
package com.example.factory;
public class PdfDocumentFactory extends DocumentFactory {
    public PdfDocument createDocument() {
        return new PdfDocumentImpl();
    }
}
package com.example.factory;

public class ExcelDocumentFactory extends DocumentFactory {
    public ExcelDocument createDocument() {
        return new ExcelDocumentImpl();
    }
}
package com.example.factory;

public class FactoryMethodTest {

    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();  // Output: Opening a Word document.
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();  // Output: Opening a PDF document.
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();  // Output: Opening an Excel document.
    }
}
-------------------------------------------------------------------------------------------------------
Exercise-3:
-------------------------------------------------------------------------------------------------------
package com.example.builder;

public class Computer {
    private final String CPU;
    private final int RAM;
    private final int storage;
    private final boolean hasGraphicsCard;
    private final boolean hasBluetooth;
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.hasGraphicsCard = builder.hasGraphicsCard;
        this.hasBluetooth = builder.hasBluetooth;
    }
    public String toString() {
        return "Computer{" +
                "CPU='" + CPU + '\'' +
                ", RAM=" + RAM +
                ", storage=" + storage +
                ", hasGraphicsCard=" + hasGraphicsCard +
                ", hasBluetooth=" + hasBluetooth +
                '}';
    }

    // Static nested Builder class
    public static class Builder {
        private final String CPU;
        private final int RAM;
        private int storage = 0;
        private boolean hasGraphicsCard = false;
        private boolean hasBluetooth = false;
        public Builder(String CPU, int RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }
        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }
        public Builder hasGraphicsCard(boolean hasGraphicsCard) {
            this.hasGraphicsCard = hasGraphicsCard;
            return this;
        }
        public Builder hasBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }
        public Computer build() {
            return new Computer(this);
        }
    }
}
package com.example.builder;
public class BuilderPatternTest {
    public static void main(String[] args) {
        Computer computer1 = new Computer.Builder("Intel i7", 16)
                .storage(512)
                .hasGraphicsCard(true)
                .build();

        System.out.println(computer1);
        Computer computer2 = new Computer.Builder("AMD Ryzen 9", 32)
                .storage(1024)
                .hasBluetooth(true)
                .build();
        System.out.println(computer2);
    }
}
--------------------------------------------------------------------------------------------------------------
Exercise-4:
--------------------------------------------------------------------------------------------------------------
package com.example.adapter;
public interface PaymentProcessor {
    void processPayment(double amount);
}
package com.example.adapter;
public class PayPalGateway {
    public void makePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " through PayPal.");
    }
}
package com.example.adapter;
public class StripeGateway {
    public void charge(double amount) {
        System.out.println("Charging $" + amount + " using Stripe.");
    }
}
package com.example.adapter;
public class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;
    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }
    public void processPayment(double amount) {
        payPalGateway.makePayment(amount);
    }
}
package com.example.adapter;
public class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;
    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }
    public void processPayment(double amount) {
        stripeGateway.charge(amount);
    }
}
package com.example.adapter;
public class AdapterPatternTest {
    public static void main(String[] args) {
        PaymentProcessor payPalProcessor = new PayPalAdapter(new PayPalGateway());
        payPalProcessor.processPayment(100.0);
        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeGateway());
        stripeProcessor.processPayment(200.0);
    }
}
---------------------------------------------------------------------------------------------------------
Exercise-5:
---------------------------------------------------------------------------------------------------------
package com.example.decorator;

public interface Notifier {
    void send(String message);
}
package com.example.decorator;
public class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}
package com.example.decorator;
public abstract class NotifierDecorator implements Notifier {
    protected Notifier decoratedNotifier;
    public NotifierDecorator(Notifier decoratedNotifier) {
        this.decoratedNotifier = decoratedNotifier;
    }
    public void send(String message) {
        decoratedNotifier.send(message);
    }
}
package com.example.decorator;
public class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier decoratedNotifier) {
        super(decoratedNotifier);
    }
    public void send(String message) {
        super.send(message); // Send the message using the base notifier
        sendSMS(message);   // Add SMS functionality
    }
    private void sendSMS(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
package com.example.decorator;
public class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier decoratedNotifier) {
        super(decoratedNotifier);
    }
    public void send(String message) {
        super.send(message); 
        sendSlack(message); 
    }
    private void sendSlack(String message) {
        System.out.println("Sending Slack message: " + message);
    }
}
package com.example.decorator;

public class DecoratorPatternTest {

    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        emailNotifier.send("Hello via Email!");
        Notifier smsNotifier = new SMSNotifierDecorator(emailNotifier);
        smsNotifier.send("Hello via Email and SMS!");
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);
        slackNotifier.send("Hello via Email, SMS, and Slack!");
    }
}
----------------------------------------------------------------------------------------------------------
Exercise-6:
----------------------------------------------------------------------------------------------------------
package com.example.proxy;
public interface Image {
    void display();
}
package com.example.proxy;
public class RealImage implements Image {
    private String fileName;
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadImageFromServer();
    }
    private void loadImageFromServer() {
        System.out.println("Loading " + fileName + " from the server...");
    }
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}
package com.example.proxy;
public class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;
    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);         }
        realImage.display();     }
}
package com.example.proxy;
public class ProxyPatternTest {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");
        image1.display();
        image1.display();
        image2.display();
        image2.display();
    }
}
-------------------------------------------------------------------------------------------------------
Exercise-7:
-------------------------------------------------------------------------------------------------------
package com.example.observer;

import java.util.List;

public interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}
package com.example.observer;
import java.util.ArrayList;
import java.util.List;
public class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private double stockPrice;
    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers(); 
    }
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockPrice);
        }
    }
}
package com.example.observer;

public interface Observer {
    void update(double stockPrice);
}
package com.example.observer;
public class MobileApp implements Observer {
    public void update(double stockPrice) {
        System.out.println("MobileApp: Stock price updated to $" + stockPrice);
    }
}
package com.example.observer;
public class WebApp implements Observer {
    public void update(double stockPrice) {
        System.out.println("WebApp: Stock price updated to $" + stockPrice);
    }
}
package com.example.observer;
public class ObserverPatternTest {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();
        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);
        stockMarket.setStockPrice(100.0);
        stockMarket.setStockPrice(105.5);
        stockMarket.deregisterObserver(mobileApp);
        stockMarket.setStockPrice(110.0);
    }
}
---------------------------------------------------------------------------------------------------------
Exercise-8:
---------------------------------------------------------------------------------------------------------
package com.example.strategy;

public interface PaymentStrategy {
    void pay(double amount);
}
package com.example.strategy;

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " using Credit Card: " + cardNumber);
    }
}
package com.example.strategy;

public class PayPalPayment implements PaymentStrategy {
    private String email;
    public PayPalPayment(String email) {
        this.email = email;
    }
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " using PayPal account: " + email);
    }
}
package com.example.strategy;
public class PaymentContext {
    private PaymentStrategy paymentStrategy;
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    public void executePayment(double amount) {
        paymentStrategy.pay(amount);
    }
}
package com.example.strategy;
public class StrategyPatternTest {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();
        PaymentStrategy creditCardPayment = new CreditCardPayment("1234-5678-9876-5432");
        paymentContext.setPaymentStrategy(creditCardPayment);
        paymentContext.executePayment(150.0);
        PaymentStrategy payPalPayment = new PayPalPayment("user@example.com");
        paymentContext.setPaymentStrategy(payPalPayment);
        paymentContext.executePayment(200.0);
    }
}
------------------------------------------------------------------------------------------------------------
Exercise-9:
------------------------------------------------------------------------------------------------------------
package com.example.command;

public interface Command {
    void execute();
}
package com.example.command;

public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}
package com.example.command;

public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}
package com.example.command;

public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
package com.example.command;

public class Light {
    public void turnOn() {
        System.out.println("The light is on");
    }

    public void turnOff() {
        System.out.println("The light is off");
    }
}
package com.example.command;

public class CommandPatternTest {
    public static void main(String[] args) {
        Light light = new Light();
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(lightOn);
        remote.pressButton();

        remote.setCommand(lightOff);
        remote.pressButton();
    }
}
--------------------------------------------------------------------------------------------------------------
Exercise-10:
--------------------------------------------------------------------------------------------------------------
package com.example.mvc;

public class Student {
    private String name;
    private String id;
    private String grade;

    // Constructors
    public Student(String name, String id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
package com.example.mvc;

public class StudentView {
    public void displayStudentDetails(String studentName, String studentId, String studentGrade) {
        System.out.println("Student: ");
        System.out.println("Name: " + studentName);
        System.out.println("ID: " + studentId);
        System.out.println("Grade: " + studentGrade);
    }
}
package com.example.mvc;

public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public String getStudentName() {
        return model.getName();
    }

    public void setStudentId(String id) {
        model.setId(id);
    }

    public String getStudentId() {
        return model.getId();
    }

    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }

    public String getStudentGrade() {
        return model.getGrade();
    }

    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}
package com.example.mvc;
public class MVCPatternTest {
    public static void main(String[] args) {
        Student student = new Student("John Doe", "12345", "A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);
        controller.updateView();
        controller.setStudentName("Jane Doe");
        controller.setStudentId("67890");
        controller.setStudentGrade("B");
        controller.updateView();
    }
}
----------------------------------------------------------------------------------------------------------
Exercise-11:
----------------------------------------------------------------------------------------------------------
package com.example.di;

public interface CustomerRepository {
    String findCustomerById(String id);
}
package com.example.di;

public class CustomerRepositoryImpl implements CustomerRepository {
    public String findCustomerById(String id) {
              return "Customer with ID: " + id;
    }
}
package com.example.di;

public class CustomerService {
    private CustomerRepository repository;
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }
    public String getCustomer(String id) {
        return repository.findCustomerById(id);
    }
}
package com.example.di;

public class DependencyInjectionTest {
    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);
        String customer = service.getCustomer("12345");
        System.out.println(customer);
    }
}
