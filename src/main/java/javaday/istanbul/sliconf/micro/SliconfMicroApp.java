package javaday.istanbul.sliconf.micro;


import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ttayfur on 7/4/17.
 */

//@SpringBootApplication
@EnableAutoConfiguration(
        exclude={MongoAutoConfiguration.class,
                MongoDataAutoConfiguration.class,
                MongoRepositoriesAutoConfiguration.class})

@EnableConfigurationProperties
@ComponentScan
@Configuration
@SwaggerDefinition(host = "localhost:8090", //
        info = @Info(description = "Sliconf Micro API", //
                version = "V0.0.1", //
                title = "Sliconf Micro API for Web and Mobile", //
                contact = @Contact(name = "Taifuru", url = "http://sliconf.com")), //
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS}, //
        consumes = {"application/json"}, //
        produces = {"application/json"}, //
        tags = {@Tag(name = "swagger")})
public class SliconfMicroApp {

    public static final String APP_PACKAGE = "javaday.istanbul.sliconf.micro";

    public static void main(String[] args) {
        SpringApplication.run(SliconfMicroApp.class, args);
    }
}