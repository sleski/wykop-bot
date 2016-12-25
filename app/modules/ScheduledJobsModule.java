package modules;

import akka.actor.ActorSystem;
import akka.actor.ExtendedActorSystem;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension;
import play.libs.akka.AkkaGuiceSupport;

import javax.inject.Inject;

/**
 * Module to configure all jobs.
 * <p>
 * Created by Slawomir Leski <slawomir.leski@gmail.com> on 25.12.16.
 */
public class ScheduledJobsModule extends AbstractModule implements AkkaGuiceSupport {

	@Override
	protected void configure() {
		bind(QuartzSchedulerHelper.class).asEagerSingleton();
		bind(QuartzSchedulerExtension.class).toProvider(SchedulerJobInitializer.class);
	}

	private static class SchedulerJobInitializer implements Provider<QuartzSchedulerExtension> {
		private final QuartzSchedulerExtension quartzSchedulerExtension;

		@Inject
		public SchedulerJobInitializer(ActorSystem actorSystem) {
			this.quartzSchedulerExtension = new QuartzSchedulerExtension((ExtendedActorSystem) actorSystem);
		}

		@Override
		public QuartzSchedulerExtension get() {
			return quartzSchedulerExtension;
		}
	}


}
