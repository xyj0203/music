package com.example.music.Config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger",name = "swagger-enable",havingValue = "true")
public class SwaggerConfiger {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Music API")
                .description(" API By Xuyujie")
                .termsOfServiceUrl("2582504569@qq.com")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket createRestApiForAdmin() {
        return new Docket(DocumentationType.SWAGGER_2).enable(true).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.music.Controller.AdminController"))
                .paths(PathSelectors.any()).build().groupName("管理员管理");
    }

    @Bean
    public Docket createRestApiForUser() {
        return new Docket(DocumentationType.SWAGGER_2).enable(true).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.music.Controller.UserController"))
                .paths(PathSelectors.any()).build().groupName("用户操作");
    }

    @Bean
    public Docket createRestApiForSong() {
        return new Docket(DocumentationType.SWAGGER_2).enable(true).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.music.Controller.SongController"))
                .paths(PathSelectors.any()).build().groupName("歌曲操作");
    }

    @Bean
    public Docket createRestApiForAlbum() {
        return new Docket(DocumentationType.SWAGGER_2).enable(true).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.music.Controller.AlbumController"))
                .paths(PathSelectors.any()).build().groupName("专辑操作");
    }

    @Bean
    public Docket createRestApiForSinger() {
        return new Docket(DocumentationType.SWAGGER_2).enable(true).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.music.Controller.SingerController"))
                .paths(PathSelectors.any()).build().groupName("歌手操作");
    }

    @Bean
    public Docket createRestApiForPlayList() {
        return new Docket(DocumentationType.SWAGGER_2).enable(true).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.music.Controller.PlayListController"))
                .paths(PathSelectors.any()).build().groupName("歌单操作");
    }

    @Bean
    public Docket createRestApiForBasic() {
        return new Docket(DocumentationType.SWAGGER_2).enable(true).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.music.Controller.BasicController"))
                .paths(PathSelectors.any()).build().groupName("基本操作");
    }
}
