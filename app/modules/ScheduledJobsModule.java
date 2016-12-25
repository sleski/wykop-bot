package modules;

import com.google.inject.AbstractModule;

/**
 * Module to configure all jobs.
 * <p>
 * Created by Slawomir Leski <slawomir.leski@gmail.com> on 25.12.16.
 */
public class ScheduledJobsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(QuartzSchedulerHelper.class).asEagerSingleton();
	}
}
