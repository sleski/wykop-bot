package modules;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.google.common.base.Preconditions;
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension;
import example.MyUntypedActor;
import play.Configuration;
import play.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Slawomir Leski <slawomir.leski@gmail.com> on 25.12.16.
 */
@Singleton
public class QuartzSchedulerHelper {

	static String SCHEDULED_JOB_CONFIG_PATH = "akka.quartz.schedules";

	private static final Logger.ALogger LOG = Logger.of(QuartzSchedulerHelper.class);

	@Inject
	public QuartzSchedulerHelper(QuartzSchedulerExtension quartzSchedulerExtension, Configuration configuration) {

		final ActorSystem system = ActorSystem.create("MyTestActor");
		final ActorRef myActor = system.actorOf(Props.create(MyUntypedActor.class), "myactor");
		Configuration config = configuration.getConfig(SCHEDULED_JOB_CONFIG_PATH);
		config.asMap().entrySet().stream().forEach(confguEntry -> {
			String jobName = confguEntry.getKey();
			Configuration jobConfig = config.getConfig(jobName);
			System.out.println(jobConfig.getString("expression"));
			schedule(jobName, jobConfig, quartzSchedulerExtension, myActor);
		});
	}

	static void schedule(String jobName, Configuration jobConfig, QuartzSchedulerExtension quartzSchedulerExtension, ActorRef jobDispatcher) {
		Preconditions.checkArgument(jobDispatcher != null, "Job executor not found! Job name = %s", jobName);
		quartzSchedulerExtension.schedule(jobName, jobDispatcher, jobConfig);
		LOG.info("Job {} scheduled, jobDispatcher.path() = {}.", jobName, jobDispatcher.path());
		System.out.println("===============");
	}
}
