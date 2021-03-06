package demo.editor.services;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.ioc.services.RegistryShutdownHub;
import org.apache.tapestry5.ioc.services.ServiceOverride;

import demo.editor.services.cayenne.EditorCayenneService;
import demo.services.ServicesModule;
import demo.services.cayenne.ICayenneService;
import demo.services.cluster.IClusterService;

@SubModule(ServicesModule.class)
public class EditorModule {

	public ICayenneService buildCayenneService(RegistryShutdownHub shutdownHub, IClusterService clusterService) {
		return new EditorCayenneService(shutdownHub, clusterService);
	}

	@Contribute(ServiceOverride.class)
	public void contributeServiceOverrides(MappedConfiguration<Class<?>, Object> config,
			@Local ICayenneService cayenneService) {

		config.add(ICayenneService.class, cayenneService);
	}
}
