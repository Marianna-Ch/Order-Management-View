package com.mariannach.order;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.boot.CommandLineRunner;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    Date myDefaultDate;

    {
        try {
            myDefaultDate = format.parse("01/01/2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Bean
    CommandLineRunner initDatabase(ClientRepository clientRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {

        return args -> {
            clientRepository.save(new Client("Bilbo", "Baggins", "098746509382"));
            clientRepository.save(new Client("Kabamaru", "Igano", "087636729837"));

            clientRepository.findAll().forEach(client -> log.info("Preloaded " + client));

            orderItemRepository.save(new OrderItem("Macbook Pro", 1, 2, 1500));
            orderItemRepository.save(new OrderItem("PC", 1, 2, 500));
            orderItemRepository.save(new OrderItem("iPhone", 5, 5,800));

            orderItemRepository.findAll().forEach(orderItem -> log.info("Preloaded " + orderItem));


            orderRepository.save(new Order("Computer", Status.COMPLETED, "uhyt56", myDefaultDate, 89));
            orderRepository.save(new Order("Mobile", Status.IN_PROGRESS, "ghf65", myDefaultDate, 76));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });

        };
    }
}