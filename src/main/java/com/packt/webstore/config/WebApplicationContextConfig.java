package com.packt.webstore.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.validation.Validator;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.springframework.web.util.UrlPathHelper;

import com.packt.webstore.domain.Product;
import com.packt.webstore.interceptor.ProcessingTimeLogInterceptor;
import com.packt.webstore.interceptor.PromoCodeInterceptor;
import com.packt.webstore.validator.ProductImageValidator;
import com.packt.webstore.validator.ProductValidator;
import com.packt.webstore.validator.UnitsInStockValidator;

@Configuration
@EnableWebMvc
@ComponentScan("com.packt.webstore")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter 
{
	
	
	@Override
	public org.springframework.validation.Validator getValidator()
	{
		return validator();
	}
	
	@Bean
	public ProductValidator productValidator()
	{
		Set<Validator> springValidators=new HashSet<>();
		springValidators.add(new UnitsInStockValidator());
		springValidators.add(new ProductImageValidator());
		ProductValidator productValidator=new ProductValidator();
		productValidator.setSpringValidators(springValidators);
		return productValidator;
	}
	
	
	
	@Bean
	public LocalValidatorFactoryBean validator()
	{
		LocalValidatorFactoryBean bean=new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
	
	@Bean
	public HandlerInterceptor promoCodeInterceptor()
	{
		PromoCodeInterceptor promoCodeInterceptor=new PromoCodeInterceptor();
		promoCodeInterceptor.setPromoCode("OFF3R");
		promoCodeInterceptor.setOfferRedirect("market/products");
		promoCodeInterceptor.setErrorRedirect("invalidPromoCode");
		return promoCodeInterceptor;
	}
	
	@Bean
	public LocaleResolver localeResolver()
	{
		SessionLocaleResolver resolver=new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) 
	{
		registry.addInterceptor(new ProcessingTimeLogInterceptor());
		LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		registry.addInterceptor(localeChangeInterceptor);
		registry.addInterceptor(promoCodeInterceptor()).addPathPatterns("/**/market/products/specialOffer");
	}
	
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer)//to enable Matrix Variable support 
	{
		UrlPathHelper urlPathHelper=new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
	}
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}
	
	
	@Bean
	public MappingJackson2JsonView jsonView()
	{
		MappingJackson2JsonView jsonView=new MappingJackson2JsonView();
		jsonView.setPrettyPrint(true);
		return jsonView;
	}
	
	@Bean
	public MarshallingView xmlView()
	{
		Jaxb2Marshaller marsheller=new Jaxb2Marshaller();
		marsheller.setClassesToBeBound(Product.class);
		MarshallingView xmlView=new MarshallingView(marsheller);
		return xmlView;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver()
	{
		CommonsMultipartResolver resolver=new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}
	
	
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager)
	{
			ContentNegotiatingViewResolver resolver=new ContentNegotiatingViewResolver();
			resolver.setContentNegotiationManager(manager);
			ArrayList<View> views=new ArrayList<View>();
			views.add(jsonView());
			views.add(xmlView());
			resolver.setDefaultViews(views);
			return resolver;
	}
	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver()
	{
		InternalResourceViewResolver resolver=new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public MessageSource messageSource()
	{
		ResourceBundleMessageSource resource=new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
	}
}
