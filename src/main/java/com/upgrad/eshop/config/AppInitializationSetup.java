package com.upgrad.eshop.config;

import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.Order;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.services.OrderService;
import com.upgrad.eshop.services.ProductService;
import com.upgrad.eshop.services.ShippingAddressService;
import com.upgrad.eshop.services.UserService;
import com.upgrad.eshop.utils.Constants;
import com.upgrad.eshop.utils.DtoToEntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class AppInitializationSetup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private UserService userService;

    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;

    private List<User> users;
    private List<Product> products;
    private List<ShippingAddress> shippingAddresses;

    private static final Logger logger = LoggerFactory.getLogger(AppInitializationSetup.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        logger.debug("Started adding dummy data into the database");

        users = new ArrayList<>();
        products = new ArrayList<>();
        shippingAddresses = new ArrayList<>();

        addUsers();
        addProducts();
        addShippingAddresses();
        addOrders();

        logger.debug("Completed adding dummy data into the database. Added " + users.size() + " users, " +
                products.size() + " products, " + shippingAddresses.size() + " shippingAddresses and " +
                shippingAddresses.size() + " orders.");
    }

    private void addUsers() {
        addAdmins();
        users.add(dtoToEntityConverter.toUser(getDummyRegisterRequest("user1")));
        users.add(dtoToEntityConverter.toUser(getDummyRegisterRequest("user2")));
        users = users.stream()
                .map(userService::saveUser)
                .collect(Collectors.toList());
    }

    private RegisterRequest getDummyRegisterRequest(String username) {
        return new RegisterRequest(
                username, "password", username, username, username+"@upgrad.com", "1234567890");
    }

    private void addAdmins() {
        User user = dtoToEntityConverter.toUser(
                new RegisterRequest(
                        "admin",
                        "password",
                        "admin",
                        "admin",
                        "admin@upgrad.com",
                        "1234567890"
                )
        );
        user.setRole(Constants.Roles.ADMIN);
        userService.saveUser(user);
    }

    private void addProducts() {
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto",
                "Automotive",
                6999,
                "Himmlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) Price: Rs." +
                        " 1 899 Beat the heat this summer and feel like a VIP with Himmlisch Car Window" +
                        " Magnetic Sunshades. These magnetic sunshades create a mesh layer to stops the" +
                        " heat. Magnet border gets easily stick to your car window door edges (No need " +
                        "of Suction cups) Features: Block UV Rays Keeps Car Cool Easy to install and re" +
                        "move Durable and Exact Fit Provides Complete privacy Resists Heat Mesh Type Su" +
                        "nshade Package Contents: 1 x Set Of 4 Magnetic Sunshades Specifications of Him" +
                        "mlisch ST381 Magnetic Sun Shade For Maruti Alto (Side Window) General Brand Hi" +
                        "mmlisch Model Number ST381 Magnetic Placement Position Side Window Color Black" +
                        " Dimensions Weight 4000 g Depth 1.1 cm In the Box Sales Package 4 Sun Shade Pa" +
                        "ck of 4",
                "Himmlisch",
                "http://img5a.flixcart.com/image/sun-shade/5/2/y/pp48-car-magnetic-himmlisch-11" +
                        "00x1100-imaegujvyzrc8eh6.jpeg"
        )));

        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Sathiyas Cotton Bath Towel",
                "Baby Care",
                600,
                "Specifications of Sathiyas Cotton Bath Towel (3 Bath Towel  Red  Yellow  Blue" +
                        ") Bath Towel Features Machine Washable Yes Material Cotton Design Self Design G" +
                        "eneral Brand Sathiyas Type Bath Towel GSM 500 Model Name Sathiyas cotton bath t" +
                        "owel Ideal For Men  Women  Boys  Girls Model ID asvtwl322 Color Red  Yellow  Bl" +
                        "ue Size Mediam Dimensions Length 30 inch Width 60 inch In the Box Number of Con" +
                        "tents in Sales Package 3 Sales Package 3 Bath Towel",
                "Sathiyas",
                "http://img6a.flixcart.com/image/bath-towel/z/u/h/asvtwl322-sathiyas-sathiyas-c" +
                        "otton-bath-towel-original-imaegyryachkkfac.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Babeezworld Dungaree Baby Boy s  Combo",
                "Baby Care",
                999,
                "Specifications of Babeezworld Dungaree Baby Boy s  Combo Apparel Combo Detail" +
                        "s Primary Product Size S Fabric Cotton Pattern Printed Fit Regular Fit Occasion" +
                        " Casual Ideal For Baby Boy s Additional Details Style Code bbz1105a Fabric Care " +
                        "Wash with Similar Colors  Use Detergent for Colors In the Box Number of Contents " +
                        "in Sales Package 2",
                "Babeezworld",
                "http://img6a.flixcart.com/image/apparels-combo/w/m/e/bbz1105a-babeezworld-origin" +
                        "al-imaeght9kggmy64v.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Sathiyas Cotton Bath Towel",
                "Baby Care",
                600,
                "Specifications of Sathiyas Cotton Bath Towel (3 Bath Towel  Red  Yellow  Blue" +
                        ") Bath Towel Features Machine Washable Yes Material Cotton Design Self Design G" +
                        "eneral Brand Sathiyas Type Bath Towel GSM 500 Model Name Sathiyas cotton bath t" +
                        "owel Ideal For Men  Women  Boys  Girls Model ID asvtwl322 Color Red  Yellow  Bl" +
                        "ue Size Mediam Dimensions Length 30 inch Width 60 inch In the Box Number of Con" +
                        "tents in Sales Package 3 Sales Package 3 Bath Towel",
                "Sathiyas",
                "http://img6a.flixcart.com/image/bath-towel/z/u/h/asvtwl322-sathiyas-sathiyas-c" +
                        "otton-bath-towel-original-imaegyryachkkfac.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Babeezworld Dungaree Baby Boy s  Combo",
                "Baby Care",
                999,
                "Specifications of Babeezworld Dungaree Baby Boy s  Combo Apparel Combo Detail" +
                        "s Primary Product Size S Fabric Cotton Pattern Printed Fit Regular Fit Occasion" +
                        " Casual Ideal For Baby Boy s Additional Details Style Code bbz1105a Fabric Care " +
                        "Wash with Similar Colors  Use Detergent for Colors In the Box Number of Contents " +
                        "in Sales Package 2",
                "Babeezworld",
                "http://img6a.flixcart.com/image/apparels-combo/w/m/e/bbz1105a-babeezworld-origin" +
                        "al-imaeght9kggmy64v.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Sathiyas Cotton Bath Towel",
                "Baby Care",
                600,
                "Specifications of Sathiyas Cotton Bath Towel (3 Bath Towel  Red  Yellow  Blue" +
                        ") Bath Towel Features Machine Washable Yes Material Cotton Design Self Design G" +
                        "eneral Brand Sathiyas Type Bath Towel GSM 500 Model Name Sathiyas cotton bath t" +
                        "owel Ideal For Men  Women  Boys  Girls Model ID asvtwl322 Color Red  Yellow  Bl" +
                        "ue Size Mediam Dimensions Length 30 inch Width 60 inch In the Box Number of Con" +
                        "tents in Sales Package 3 Sales Package 3 Bath Towel",
                "Sathiyas",
                "http://img6a.flixcart.com/image/bath-towel/z/u/h/asvtwl322-sathiyas-sathiyas-c" +
                        "otton-bath-towel-original-imaegyryachkkfac.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Babeezworld Dungaree Baby Boy s  Combo",
                "Baby Care",
                999,
                "Specifications of Babeezworld Dungaree Baby Boy s  Combo Apparel Combo Detail" +
                        "s Primary Product Size S Fabric Cotton Pattern Printed Fit Regular Fit Occasion" +
                        " Casual Ideal For Baby Boy s Additional Details Style Code bbz1105a Fabric Care " +
                        "Wash with Similar Colors  Use Detergent for Colors In the Box Number of Contents " +
                        "in Sales Package 2",
                "Babeezworld",
                "http://img6a.flixcart.com/image/apparels-combo/w/m/e/bbz1105a-babeezworld-origin" +
                        "al-imaeght9kggmy64v.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Sathiyas Cotton Bath Towel",
                "Baby Care",
                600,
                "Specifications of Sathiyas Cotton Bath Towel (3 Bath Towel  Red  Yellow  Blue" +
                        ") Bath Towel Features Machine Washable Yes Material Cotton Design Self Design G" +
                        "eneral Brand Sathiyas Type Bath Towel GSM 500 Model Name Sathiyas cotton bath t" +
                        "owel Ideal For Men  Women  Boys  Girls Model ID asvtwl322 Color Red  Yellow  Bl" +
                        "ue Size Mediam Dimensions Length 30 inch Width 60 inch In the Box Number of Con" +
                        "tents in Sales Package 3 Sales Package 3 Bath Towel",
                "Sathiyas",
                "http://img6a.flixcart.com/image/bath-towel/z/u/h/asvtwl322-sathiyas-sathiyas-c" +
                        "otton-bath-towel-original-imaegyryachkkfac.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Babeezworld Dungaree Baby Boy s  Combo",
                "Baby Care",
                999,
                "Specifications of Babeezworld Dungaree Baby Boy s  Combo Apparel Combo Detail" +
                        "s Primary Product Size S Fabric Cotton Pattern Printed Fit Regular Fit Occasion" +
                        " Casual Ideal For Baby Boy s Additional Details Style Code bbz1105a Fabric Care " +
                        "Wash with Similar Colors  Use Detergent for Colors In the Box Number of Contents " +
                        "in Sales Package 2",
                "Babeezworld",
                "http://img6a.flixcart.com/image/apparels-combo/w/m/e/bbz1105a-babeezworld-origin" +
                        "al-imaeght9kggmy64v.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Sathiyas Cotton Bath Towel",
                "Baby Care",
                600,
                "Specifications of Sathiyas Cotton Bath Towel (3 Bath Towel  Red  Yellow  Blue" +
                        ") Bath Towel Features Machine Washable Yes Material Cotton Design Self Design G" +
                        "eneral Brand Sathiyas Type Bath Towel GSM 500 Model Name Sathiyas cotton bath t" +
                        "owel Ideal For Men  Women  Boys  Girls Model ID asvtwl322 Color Red  Yellow  Bl" +
                        "ue Size Mediam Dimensions Length 30 inch Width 60 inch In the Box Number of Con" +
                        "tents in Sales Package 3 Sales Package 3 Bath Towel",
                "Sathiyas",
                "http://img6a.flixcart.com/image/bath-towel/z/u/h/asvtwl322-sathiyas-sathiyas-c" +
                        "otton-bath-towel-original-imaegyryachkkfac.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Babeezworld Dungaree Baby Boy s  Combo",
                "Baby Care",
                999,
                "Specifications of Babeezworld Dungaree Baby Boy s  Combo Apparel Combo Detail" +
                        "s Primary Product Size S Fabric Cotton Pattern Printed Fit Regular Fit Occasion" +
                        " Casual Ideal For Baby Boy s Additional Details Style Code bbz1105a Fabric Care " +
                        "Wash with Similar Colors  Use Detergent for Colors In the Box Number of Contents " +
                        "in Sales Package 2",
                "Babeezworld",
                "http://img6a.flixcart.com/image/apparels-combo/w/m/e/bbz1105a-babeezworld-origin" +
                        "al-imaeght9kggmy64v.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Sathiyas Cotton Bath Towel",
                "Baby Care",
                600,
                "Specifications of Sathiyas Cotton Bath Towel (3 Bath Towel  Red  Yellow  Blue" +
                        ") Bath Towel Features Machine Washable Yes Material Cotton Design Self Design G" +
                        "eneral Brand Sathiyas Type Bath Towel GSM 500 Model Name Sathiyas cotton bath t" +
                        "owel Ideal For Men  Women  Boys  Girls Model ID asvtwl322 Color Red  Yellow  Bl" +
                        "ue Size Mediam Dimensions Length 30 inch Width 60 inch In the Box Number of Con" +
                        "tents in Sales Package 3 Sales Package 3 Bath Towel",
                "Sathiyas",
                "http://img6a.flixcart.com/image/bath-towel/z/u/h/asvtwl322-sathiyas-sathiyas-c" +
                        "otton-bath-towel-original-imaegyryachkkfac.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Babeezworld Dungaree Baby Boy s  Combo",
                "Baby Care",
                999,
                "Specifications of Babeezworld Dungaree Baby Boy s  Combo Apparel Combo Detail" +
                        "s Primary Product Size S Fabric Cotton Pattern Printed Fit Regular Fit Occasion" +
                        " Casual Ideal For Baby Boy s Additional Details Style Code bbz1105a Fabric Care " +
                        "Wash with Similar Colors  Use Detergent for Colors In the Box Number of Contents " +
                        "in Sales Package 2",
                "Babeezworld",
                "http://img6a.flixcart.com/image/apparels-combo/w/m/e/bbz1105a-babeezworld-origin" +
                        "al-imaeght9kggmy64v.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Sathiyas Cotton Bath Towel",
                "Baby Care",
                600,
                "Specifications of Sathiyas Cotton Bath Towel (3 Bath Towel  Red  Yellow  Blue" +
                        ") Bath Towel Features Machine Washable Yes Material Cotton Design Self Design G" +
                        "eneral Brand Sathiyas Type Bath Towel GSM 500 Model Name Sathiyas cotton bath t" +
                        "owel Ideal For Men  Women  Boys  Girls Model ID asvtwl322 Color Red  Yellow  Bl" +
                        "ue Size Mediam Dimensions Length 30 inch Width 60 inch In the Box Number of Con" +
                        "tents in Sales Package 3 Sales Package 3 Bath Towel",
                "Sathiyas",
                "http://img6a.flixcart.com/image/bath-towel/z/u/h/asvtwl322-sathiyas-sathiyas-c" +
                        "otton-bath-towel-original-imaegyryachkkfac.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Babeezworld Dungaree Baby Boy s  Combo",
                "Baby Care",
                999,
                "Specifications of Babeezworld Dungaree Baby Boy s  Combo Apparel Combo Detail" +
                        "s Primary Product Size S Fabric Cotton Pattern Printed Fit Regular Fit Occasion" +
                        " Casual Ideal For Baby Boy s Additional Details Style Code bbz1105a Fabric Care " +
                        "Wash with Similar Colors  Use Detergent for Colors In the Box Number of Contents " +
                        "in Sales Package 2",
                "Babeezworld",
                "http://img6a.flixcart.com/image/apparels-combo/w/m/e/bbz1105a-babeezworld-origin" +
                        "al-imaeght9kggmy64v.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Sathiyas Cotton Bath Towel",
                "Baby Care",
                600,
                "Specifications of Sathiyas Cotton Bath Towel (3 Bath Towel  Red  Yellow  Blue" +
                        ") Bath Towel Features Machine Washable Yes Material Cotton Design Self Design G" +
                        "eneral Brand Sathiyas Type Bath Towel GSM 500 Model Name Sathiyas cotton bath t" +
                        "owel Ideal For Men  Women  Boys  Girls Model ID asvtwl322 Color Red  Yellow  Bl" +
                        "ue Size Mediam Dimensions Length 30 inch Width 60 inch In the Box Number of Con" +
                        "tents in Sales Package 3 Sales Package 3 Bath Towel",
                "Sathiyas",
                "http://img6a.flixcart.com/image/bath-towel/z/u/h/asvtwl322-sathiyas-sathiyas-c" +
                        "otton-bath-towel-original-imaegyryachkkfac.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Babeezworld Dungaree Baby Boy s  Combo",
                "Baby Care",
                999,
                "Specifications of Babeezworld Dungaree Baby Boy s  Combo Apparel Combo Detail" +
                        "s Primary Product Size S Fabric Cotton Pattern Printed Fit Regular Fit Occasion" +
                        " Casual Ideal For Baby Boy s Additional Details Style Code bbz1105a Fabric Care " +
                        "Wash with Similar Colors  Use Detergent for Colors In the Box Number of Contents " +
                        "in Sales Package 2",
                "Babeezworld",
                "http://img6a.flixcart.com/image/apparels-combo/w/m/e/bbz1105a-babeezworld-origin" +
                        "al-imaeght9kggmy64v.jpeg"
        )));

        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Angelfish Silk Potali Potli",
                "Bags  Wallets & Belts",
                999,
                "Angelfish Silk Potali Potli (Multicolor) Price: Rs. 399 Made by silk Fabric with" +
                        " fancy lace adnored and stylish handle also.(set of 2 piece) Specifications of Ang" +
                        "elfish Silk Potali Potli (Multicolor) General Closure Velcro Type Potli Material F" +
                        "abric Style Code AELKABJ01224-A Ideal For Girls Bag Size Small Occasion Party Colo" +
                        "r Code Multicolor Dimensions Weight 200 g Body Features Number of Compartments 1",
                "Angelfish",
                "http://img6a.flixcart.com/image/pouch-potli/u/x/v/aelkabj01224-a-angelfish-potli" +
                        "-silk-potali-original-imaeeprygdj223es.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Vermello Men Casual Brown Genuine Leather Belt",
                "Bags  Wallets & Belts",
                1495,
                "Specifications of Vermello Men Casual Brown Genuine Leather Belt (Brown) General R" +
                        "eversible Belt No Material Genuine Leather Style Code G7001BRN Occasion Casual Ideal" +
                        " For Men Color Code Brown Belt Size 34 Body Features 5 Punched Holes  Pin Buckle Dim" +
                        "ensions Weight 250 g Length 34 inch Width 3.8 cm In the Box 1 Belt",
                "Vermello",
                "http://img6a.flixcart.com/image/belt/t/e/8/40-g7001brn-vermello-belt-g7001brn40-ori" +
                        "ginal-imaeg66zgu9vpjr5.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Ligans NY Men Formal Black Genuine Leather Belt",
                "Bags  Wallets & Belts",
                795,
                "Key Features of Ligans NY Men Formal Black Genuine Leather Belt Genuine Leather Shin" +
                        "ey Buckle Ligans NY Men Formal Black Genuine Leather Belt (Black) Price: Rs. 695 Get r" +
                        "ecognised for your exceptional dressing sense by wearing this black coloured belt with" +
                        " a white shirt and grey trousers. This fabulous belt from Ligans NY is made from genui" +
                        "ne leather  which makes it light in weight and a fine quality shiny buckle for every m" +
                        "an to invest in. Specifications of Ligans NY Men Formal Black Genuine Leather Belt (Bl" +
                        "ack) General Reversible Belt No Belt Pattern Punched Belt with Shiney buckle Material " +
                        "Genuine Leather Style Code LNY-M-MO-1017-FS Ideal For Men Occasion Formal Color Code B" +
                        "lack Belt Size 28 Body Features 6 Puched Holes  1 Loop  Genuine Leather  Shiney Buckle" +
                        " Dimensions Weight 200 g Length 34 inch Width 34 mm In the Box Belt",
                "Ligans NY",
                "http://img6a.flixcart.com/image/belt/h/e/y/34-lny-m-mo-1017-fs-ligans-ny-belt-fae-mo-" +
                        "fs-original-imaedezyn5kkmwae.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Angelfish Silk Potali Potli",
                "Bags  Wallets & Belts",
                999,
                "Angelfish Silk Potali Potli (Multicolor) Price: Rs. 399 Made by silk Fabric with" +
                        " fancy lace adnored and stylish handle also.(set of 2 piece) Specifications of Ang" +
                        "elfish Silk Potali Potli (Multicolor) General Closure Velcro Type Potli Material F" +
                        "abric Style Code AELKABJ01224-A Ideal For Girls Bag Size Small Occasion Party Colo" +
                        "r Code Multicolor Dimensions Weight 200 g Body Features Number of Compartments 1",
                "Angelfish",
                "http://img6a.flixcart.com/image/pouch-potli/u/x/v/aelkabj01224-a-angelfish-potli" +
                        "-silk-potali-original-imaeeprygdj223es.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Vermello Men Casual Brown Genuine Leather Belt",
                "Bags  Wallets & Belts",
                1495,
                "Specifications of Vermello Men Casual Brown Genuine Leather Belt (Brown) General R" +
                        "eversible Belt No Material Genuine Leather Style Code G7001BRN Occasion Casual Ideal" +
                        " For Men Color Code Brown Belt Size 34 Body Features 5 Punched Holes  Pin Buckle Dim" +
                        "ensions Weight 250 g Length 34 inch Width 3.8 cm In the Box 1 Belt",
                "Vermello",
                "http://img6a.flixcart.com/image/belt/t/e/8/40-g7001brn-vermello-belt-g7001brn40-ori" +
                        "ginal-imaeg66zgu9vpjr5.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Ligans NY Men Formal Black Genuine Leather Belt",
                "Bags  Wallets & Belts",
                795,
                "Key Features of Ligans NY Men Formal Black Genuine Leather Belt Genuine Leather Shin" +
                        "ey Buckle Ligans NY Men Formal Black Genuine Leather Belt (Black) Price: Rs. 695 Get r" +
                        "ecognised for your exceptional dressing sense by wearing this black coloured belt with" +
                        " a white shirt and grey trousers. This fabulous belt from Ligans NY is made from genui" +
                        "ne leather  which makes it light in weight and a fine quality shiny buckle for every m" +
                        "an to invest in. Specifications of Ligans NY Men Formal Black Genuine Leather Belt (Bl" +
                        "ack) General Reversible Belt No Belt Pattern Punched Belt with Shiney buckle Material " +
                        "Genuine Leather Style Code LNY-M-MO-1017-FS Ideal For Men Occasion Formal Color Code B" +
                        "lack Belt Size 28 Body Features 6 Puched Holes  1 Loop  Genuine Leather  Shiney Buckle" +
                        " Dimensions Weight 200 g Length 34 inch Width 34 mm In the Box Belt",
                "Ligans NY",
                "http://img6a.flixcart.com/image/belt/h/e/y/34-lny-m-mo-1017-fs-ligans-ny-belt-fae-mo-" +
                        "fs-original-imaedezyn5kkmwae.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Angelfish Silk Potali Potli",
                "Bags  Wallets & Belts",
                999,
                "Angelfish Silk Potali Potli (Multicolor) Price: Rs. 399 Made by silk Fabric with" +
                        " fancy lace adnored and stylish handle also.(set of 2 piece) Specifications of Ang" +
                        "elfish Silk Potali Potli (Multicolor) General Closure Velcro Type Potli Material F" +
                        "abric Style Code AELKABJ01224-A Ideal For Girls Bag Size Small Occasion Party Colo" +
                        "r Code Multicolor Dimensions Weight 200 g Body Features Number of Compartments 1",
                "Angelfish",
                "http://img6a.flixcart.com/image/pouch-potli/u/x/v/aelkabj01224-a-angelfish-potli" +
                        "-silk-potali-original-imaeeprygdj223es.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Vermello Men Casual Brown Genuine Leather Belt",
                "Bags  Wallets & Belts",
                1495,
                "Specifications of Vermello Men Casual Brown Genuine Leather Belt (Brown) General R" +
                        "eversible Belt No Material Genuine Leather Style Code G7001BRN Occasion Casual Ideal" +
                        " For Men Color Code Brown Belt Size 34 Body Features 5 Punched Holes  Pin Buckle Dim" +
                        "ensions Weight 250 g Length 34 inch Width 3.8 cm In the Box 1 Belt",
                "Vermello",
                "http://img6a.flixcart.com/image/belt/t/e/8/40-g7001brn-vermello-belt-g7001brn40-ori" +
                        "ginal-imaeg66zgu9vpjr5.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Ligans NY Men Formal Black Genuine Leather Belt",
                "Bags  Wallets & Belts",
                795,
                "Key Features of Ligans NY Men Formal Black Genuine Leather Belt Genuine Leather Shin" +
                        "ey Buckle Ligans NY Men Formal Black Genuine Leather Belt (Black) Price: Rs. 695 Get r" +
                        "ecognised for your exceptional dressing sense by wearing this black coloured belt with" +
                        " a white shirt and grey trousers. This fabulous belt from Ligans NY is made from genui" +
                        "ne leather  which makes it light in weight and a fine quality shiny buckle for every m" +
                        "an to invest in. Specifications of Ligans NY Men Formal Black Genuine Leather Belt (Bl" +
                        "ack) General Reversible Belt No Belt Pattern Punched Belt with Shiney buckle Material " +
                        "Genuine Leather Style Code LNY-M-MO-1017-FS Ideal For Men Occasion Formal Color Code B" +
                        "lack Belt Size 28 Body Features 6 Puched Holes  1 Loop  Genuine Leather  Shiney Buckle" +
                        " Dimensions Weight 200 g Length 34 inch Width 34 mm In the Box Belt",
                "Ligans NY",
                "http://img6a.flixcart.com/image/belt/h/e/y/34-lny-m-mo-1017-fs-ligans-ny-belt-fae-mo-" +
                        "fs-original-imaedezyn5kkmwae.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Angelfish Silk Potali Potli",
                "Bags  Wallets & Belts",
                999,
                "Angelfish Silk Potali Potli (Multicolor) Price: Rs. 399 Made by silk Fabric with" +
                        " fancy lace adnored and stylish handle also.(set of 2 piece) Specifications of Ang" +
                        "elfish Silk Potali Potli (Multicolor) General Closure Velcro Type Potli Material F" +
                        "abric Style Code AELKABJ01224-A Ideal For Girls Bag Size Small Occasion Party Colo" +
                        "r Code Multicolor Dimensions Weight 200 g Body Features Number of Compartments 1",
                "Angelfish",
                "http://img6a.flixcart.com/image/pouch-potli/u/x/v/aelkabj01224-a-angelfish-potli" +
                        "-silk-potali-original-imaeeprygdj223es.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Vermello Men Casual Brown Genuine Leather Belt",
                "Bags  Wallets & Belts",
                1495,
                "Specifications of Vermello Men Casual Brown Genuine Leather Belt (Brown) General R" +
                        "eversible Belt No Material Genuine Leather Style Code G7001BRN Occasion Casual Ideal" +
                        " For Men Color Code Brown Belt Size 34 Body Features 5 Punched Holes  Pin Buckle Dim" +
                        "ensions Weight 250 g Length 34 inch Width 3.8 cm In the Box 1 Belt",
                "Vermello",
                "http://img6a.flixcart.com/image/belt/t/e/8/40-g7001brn-vermello-belt-g7001brn40-ori" +
                        "ginal-imaeg66zgu9vpjr5.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Ligans NY Men Formal Black Genuine Leather Belt",
                "Bags  Wallets & Belts",
                795,
                "Key Features of Ligans NY Men Formal Black Genuine Leather Belt Genuine Leather Shin" +
                        "ey Buckle Ligans NY Men Formal Black Genuine Leather Belt (Black) Price: Rs. 695 Get r" +
                        "ecognised for your exceptional dressing sense by wearing this black coloured belt with" +
                        " a white shirt and grey trousers. This fabulous belt from Ligans NY is made from genui" +
                        "ne leather  which makes it light in weight and a fine quality shiny buckle for every m" +
                        "an to invest in. Specifications of Ligans NY Men Formal Black Genuine Leather Belt (Bl" +
                        "ack) General Reversible Belt No Belt Pattern Punched Belt with Shiney buckle Material " +
                        "Genuine Leather Style Code LNY-M-MO-1017-FS Ideal For Men Occasion Formal Color Code B" +
                        "lack Belt Size 28 Body Features 6 Puched Holes  1 Loop  Genuine Leather  Shiney Buckle" +
                        " Dimensions Weight 200 g Length 34 inch Width 34 mm In the Box Belt",
                "Ligans NY",
                "http://img6a.flixcart.com/image/belt/h/e/y/34-lny-m-mo-1017-fs-ligans-ny-belt-fae-mo-" +
                        "fs-original-imaedezyn5kkmwae.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Angelfish Silk Potali Potli",
                "Bags  Wallets & Belts",
                999,
                "Angelfish Silk Potali Potli (Multicolor) Price: Rs. 399 Made by silk Fabric with" +
                        " fancy lace adnored and stylish handle also.(set of 2 piece) Specifications of Ang" +
                        "elfish Silk Potali Potli (Multicolor) General Closure Velcro Type Potli Material F" +
                        "abric Style Code AELKABJ01224-A Ideal For Girls Bag Size Small Occasion Party Colo" +
                        "r Code Multicolor Dimensions Weight 200 g Body Features Number of Compartments 1",
                "Angelfish",
                "http://img6a.flixcart.com/image/pouch-potli/u/x/v/aelkabj01224-a-angelfish-potli" +
                        "-silk-potali-original-imaeeprygdj223es.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Vermello Men Casual Brown Genuine Leather Belt",
                "Bags  Wallets & Belts",
                1495,
                "Specifications of Vermello Men Casual Brown Genuine Leather Belt (Brown) General R" +
                        "eversible Belt No Material Genuine Leather Style Code G7001BRN Occasion Casual Ideal" +
                        " For Men Color Code Brown Belt Size 34 Body Features 5 Punched Holes  Pin Buckle Dim" +
                        "ensions Weight 250 g Length 34 inch Width 3.8 cm In the Box 1 Belt",
                "Vermello",
                "http://img6a.flixcart.com/image/belt/t/e/8/40-g7001brn-vermello-belt-g7001brn40-ori" +
                        "ginal-imaeg66zgu9vpjr5.jpeg"
        )));
        products.add(dtoToEntityConverter.toProduct(getDummyProductRequest(
                "Ligans NY Men Formal Black Genuine Leather Belt",
                "Bags  Wallets & Belts",
                795,
                "Key Features of Ligans NY Men Formal Black Genuine Leather Belt Genuine Leather Shin" +
                        "ey Buckle Ligans NY Men Formal Black Genuine Leather Belt (Black) Price: Rs. 695 Get r" +
                        "ecognised for your exceptional dressing sense by wearing this black coloured belt with" +
                        " a white shirt and grey trousers. This fabulous belt from Ligans NY is made from genui" +
                        "ne leather  which makes it light in weight and a fine quality shiny buckle for every m" +
                        "an to invest in. Specifications of Ligans NY Men Formal Black Genuine Leather Belt (Bl" +
                        "ack) General Reversible Belt No Belt Pattern Punched Belt with Shiney buckle Material " +
                        "Genuine Leather Style Code LNY-M-MO-1017-FS Ideal For Men Occasion Formal Color Code B" +
                        "lack Belt Size 28 Body Features 6 Puched Holes  1 Loop  Genuine Leather  Shiney Buckle" +
                        " Dimensions Weight 200 g Length 34 inch Width 34 mm In the Box Belt",
                "Ligans NY",
                "http://img6a.flixcart.com/image/belt/h/e/y/34-lny-m-mo-1017-fs-ligans-ny-belt-fae-mo-" +
                        "fs-original-imaedezyn5kkmwae.jpeg"
        )));
        products = products.stream()
                .map(productService::saveProduct)
                .collect(Collectors.toList());
    }

    private ProductRequest getDummyProductRequest(
            String name, String category, double price, String description, String manufacturer, String imageUrl) {
        return new ProductRequest(name, category, price, description, manufacturer, 25, imageUrl);
    }

    private void addShippingAddresses() {
        for (User user : users) {
            ShippingAddress shippingAddress = new ShippingAddress();
            shippingAddress.setName(user.getFirstName() + " " + user.getLastName());
            shippingAddress.setPhone(user.getPhoneNumber());
            shippingAddress.setStreet(user.getFirstName() + " street");
            shippingAddress.setLandmark(user.getFirstName() + " landmark");
            shippingAddress.setCity(user.getFirstName() + " city");
            shippingAddress.setState(user.getFirstName() + " state");
            shippingAddress.setZipcode("123456");
            shippingAddress.setUser(user);
            shippingAddresses.add(shippingAddress);
        }
        shippingAddresses = shippingAddresses.stream()
                .map(shippingAddressService::saveShippingAddress)
                .collect(Collectors.toList());
    }

    private void addOrders() {
        for (ShippingAddress shippingAddress: shippingAddresses) {
            Order order = new Order();
            Product product = products.get(new Random().nextInt(products.size()));
            order.setUser(shippingAddress.getUser());
            order.setProduct(product);
            order.setShippingAddress(shippingAddress);
            order.setAmount(product.getPrice());
            order.setOrderDate(LocalDateTime.now());
            orderService.saveOrder(order);
        }
    }
}
