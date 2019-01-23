package logan.exemple.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;

/**
 * @author 韩韩
 * @Title: GateWayApplication
 * @ProjectName cloud
 * @Description: TODO
 * @date 2018/10/815:29
 */
@Configuration
@SpringBootApplication
public class GateWayApplication {


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {


        return builder.routes()
                .route(predicateSpec -> predicateSpec.host("localhost:8080" ).and().path("/image/png" )
                        .filters(f ->
                                f.addResponseHeader("X-TestHeader", "foobar" ))
                        .uri("http://httpbin.org:80" )
                )
                .route(r -> r.path("/image/webp" )
                        .filters(f ->
                                f.addResponseHeader("X-AnotherHeader", "baz" ))
                        .uri("http://httpbin.org:80" )
                )
//                .route(r -> r.order(-1)
//                        .host("**.throttle.org").and().path("/get")
//                        .filters(f -> f.filter(throttle.apply(1,
//                                1,
//                                10,
//                                TimeUnit.SECONDS)))
//                        .uri("http://httpbin.org:80")
//                )
                .build();
    }

    /**
     * 自定义限流标志的key，多个维度可以从这里入手
     * exchange对象中获取服务ID、请求信息，用户信息等
     */
    @Bean
    public KeyResolver myKeyResolver() {

        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }

}
