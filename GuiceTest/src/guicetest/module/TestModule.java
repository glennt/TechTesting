package guicetest.module;

import guicetest.service.Service;
import guicetest.service.ServiceImpl;

import com.google.inject.Binder;
import com.google.inject.Module;

public class TestModule implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bind(Service.class).to(ServiceImpl.class);		
	}

}
