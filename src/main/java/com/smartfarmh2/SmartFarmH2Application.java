package com.smartfarmh2;

import com.smartfarmh2.device.Device;
import com.smartfarmh2.device.DeviceService;
import com.smartfarmh2.product.Product;
import com.smartfarmh2.product.ProductRepository;
import com.smartfarmh2.productStock.ProductStock;
import com.smartfarmh2.productStock.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
public class SmartFarmH2Application implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductStockRepository productStockRepository;
	@Autowired
	private DeviceService deviceService;

	public static void main(String[] args) {
		SpringApplication.run(SmartFarmH2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Product p = new Product("Banana","kg");
		productRepository.save(p);

		productStockRepository.save(new ProductStock(productRepository.findOne(1L),20));

		Device device = new Device();
		device.setName("Exp01");
		deviceService.create(device);

		Device device2 = new Device();
		device2.setName("Exp02");
		deviceService.create(device2);
	}
}
