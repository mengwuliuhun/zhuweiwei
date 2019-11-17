package cn.kgc.ssm.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()).build();
    }
    public ApiInfo apiInfo()
    {
        Contact ct=new Contact("will","http://www.xtaccp.com","1007493965@qq.com");
        return new ApiInfoBuilder().title("极果网 RESTFUL APIS").
                description("极果网RESTful风格的接口文档，内容详细，有效减少前后端的沟通成本").
                termsOfServiceUrl("127.0.0.1:8080/jg")
                .contact(ct).version("1.0.0").build();
    }
}
