package kr.co.edu.spring.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;

/**
 * Servlet 관련 설정 Class
 *
 * @author 차세훈
 * @since 2022.10.21
 */
@Configuration	//스프링 프레임워크에서 최초구동될때 스프링 설정값들을 찾기위해 붙여놓는 annotation
@EnableWebMvc	//mcv 패턴으로 계발할거라고 스프링 소스가 인지할 수 있도록 하는 annotation
@ComponentScan(basePackages = {"kr.co.edu"})	//mvc패턴으로 개발할 클래스를 어디에 할꺼냐

public class ServletContext implements WebMvcConfigurer {
	/**
     * 1. 특정 URL(Path)에 대한 HTTP Request 처리를 DispatcherServlet이 담당하지 않도록 만들어 주는 설정 (html, js, css, file 등..)
     * 2. Client가 접근하지 못하는 WEB-INF 폴더안에 위치해 있는 정적 자원에 Clien가 접근할 수 있도록 만들어 주는 설정
     *
     * @author 차세훈
     * @since 2022.10.21
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/views/**").addResourceLocations("/views/");
        //registry.addResourceHandler("/WEB-INF/jsp/**").addResourceLocations("/jsp/");
    }
    
    /**
     * JSP ViewResolver를 제공해 주기 위한 Bean
     *
     * @author 차세훈
     * @since 2022.10.21
     * @return InternalResourceViewResolver
     */
    @Bean(name="jspView")		//Bean : static 처럼 singtone pattern 한번만 생성하게 해준다
    public ViewResolver getJspView() {
        InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();	
        jspViewResolver.setPrefix("/views");
        jspViewResolver.setSuffix(".jsp");
        jspViewResolver.setOrder(1);
        return jspViewResolver;
    }
    
    ////
    /**
     * Custom Bean Class ViewResolver를 제공해 주기 위한 Bean (파일 다운로드, JSON 등..)
     *
     * @author 차세훈
     * @since 2022.10.25
     * @return BeanNameViewResolver
     */
    @Bean(name="beanView")
    public BeanNameViewResolver getBeanView () {
        BeanNameViewResolver beanView = new BeanNameViewResolver();
        beanView.setOrder(0);
        return beanView;
    }

    /**
     * JSON ViewResolver를 제공해 주기 위한 Bean
     *
     * @author 차세훈
     * @since 2022.10.25
     * @return MappingJackson2JsonView
     */
    @Bean(name="jsonView")
    public MappingJackson2JsonView getJsonView () {
        ObjectMapper objectMapper = new ObjectMapper();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView(objectMapper);
        jsonView.setExtractValueFromSingleKeyModel(true);
        return jsonView;
    }
}
