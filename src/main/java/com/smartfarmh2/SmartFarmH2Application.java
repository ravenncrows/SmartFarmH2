package com.smartfarmh2;

import com.smartfarmh2.device.Device;
import com.smartfarmh2.device.DeviceService;
import com.smartfarmh2.device.DeviceSetting;
import com.smartfarmh2.device.DeviceSettingService;
import com.smartfarmh2.product.Product;
import com.smartfarmh2.product.ProductRepository;
import com.smartfarmh2.productStock.ProductStock;
import com.smartfarmh2.productStock.ProductStockRepository;
import com.smartfarmh2.role.Role;
import com.smartfarmh2.role.RoleRepository;
import com.smartfarmh2.user.User;
import com.smartfarmh2.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableScheduling
public class SmartFarmH2Application implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductStockRepository productStockRepository;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DeviceSettingService deviceSettingService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SmartFarmH2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Product p = new Product("Banana","kg");
		productRepository.save(p);

		productStockRepository.save(new ProductStock(productRepository.findOne(1L),20));

		Device device = new Device();
		device.setName("espsmartfarm2");
		deviceService.create(device);

		Device device2 = new Device();
		device2.setName("Exp02");
		deviceService.create(device2);

		DeviceSetting deviceSetting = new DeviceSetting();
		deviceSetting.setDevice(device);
		deviceSetting.setWaterThreshold(50.0);
		deviceSettingService.create(deviceSetting);

		Role admin = new Role("admin");
		Role newUser = new Role("newUser");
		Role farmer = new Role("farmer");
		Role farmOwner = new Role("farmOwner");
		roleRepository.save(newUser);
		roleRepository.save(admin);
		roleRepository.save(farmer);
		roleRepository.save(farmOwner);
		Set<Role> roles = new HashSet<>();
		roles.add(admin);
		Set<Role> newRoles = new HashSet<>();
		roles.add(newUser);
		User userAdmin = new User("admin","admin","admin@admin.com","1234",roles);
		User newUserRequest = new User("newUserRequest","newUserRequest","newUserRequest@newUserRequest.com","1234",newRoles);
		userRepository.save(userAdmin);
		userRepository.save(newUserRequest);
	}
}
