package guicetest.core;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import guicetest.module.TestModule;
import guicetest.service.Service;

/*
 * Dis is a comment
 */
public class BusinessFacade {

	@Inject
	private Service service;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new TestModule());
		Service service = injector.getInstance(Service.class);
		service.doSometing();
	}
	
	
	
}
