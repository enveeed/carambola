import enveeed.carambola.slf4j.CarambolaSLF4JServiceProvider;

module carambola.slf4j {

    requires carambola.core;
    requires org.slf4j;

    provides org.slf4j.spi.SLF4JServiceProvider with CarambolaSLF4JServiceProvider;

}