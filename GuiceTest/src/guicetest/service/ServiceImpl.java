package guicetest.service;

import com.google.inject.Inject;

public class ServiceImpl implements Service {

	private ChildService childService;
	
	@Inject
	public ServiceImpl(ChildService childService) {
		this.childService = childService;
	}
	
	@Override
	public void doSometing() {
		System.out.println("DO SOMETHING SERVICE IMPL");
		childService.doSomething();
	}

}
