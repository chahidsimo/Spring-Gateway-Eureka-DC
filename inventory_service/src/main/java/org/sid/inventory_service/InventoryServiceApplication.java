package org.sid.inventory_service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Product.class);
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.save(new Product(null,"Ordinateur", 12000, 2));
            productRepository.save(new Product(null,"TV", 6000, 3));
            productRepository.save(new Product(null,"TELEPHONE", 4000, 7));

            productRepository.findAll().forEach(p->{
                System.out.println(p.toString());
            });
        };
    }



}

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private double quantity;

}

@RepositoryRestResource
@CrossOrigin
interface ProductRepository extends JpaRepository<Product, Long> {
}
