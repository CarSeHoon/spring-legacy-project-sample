package kr.co.edu.spring;
import javax.servlet.FilterRegistration; 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;	//스프링 내용을 구동할 수 있음
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
/*
 * SpringFramework context 실행을 위한 class
 * @author 차세훈
 * @since 22-10-20
 */
@Configuration		
public class SpringFrameworkStarter implements WebApplicationInitializer{
	
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContext.class);	//전역자원으로 사용할 애들
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // Web(Servlet) Context 		 
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(kr.co.edu.spring.servlet.ServletContext.class);

        // Dispatcher Servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        // Filter
        FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        filter.setInitParameter("encoding","utf-8");
        filter.addMappingForServletNames(null,false,"dispatcher");
	}
}