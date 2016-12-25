package modules;

import play.Logger;

import javax.inject.Singleton;

/**
 * Created by Slawomir Leski <slawomir.leski@idnow.de> on 25.12.16.
 */
@Singleton
public class QuartzSchedulerHelper {

	static String SCHEDULED_JOB_CONFIG_PATH = "akka.quartz.schedules";

	private static final Logger.ALogger LOG = Logger.of(QuartzSchedulerHelper.class);

//	@Inject
//	public QuartzSchedulerHelper(ActorRefRegistry registry, QuartzSchedulerExtension quartzSchedulerExtension, Configuration configuration) {
//
//		ActorRef actor = registry.getActor(IdentsSftpUploadScheduler.ACTOR_NAME);
//		Configuration config = configuration.getConfig(SCHEDULED_JOB_CONFIG_PATH);
//		config.asMap().entrySet().stream().forEach(confguEntry -> {
//			String jobName = confguEntry.getKey();
//			Configuration jobConfig = config.getConfig(jobName);
//			schedule(jobName, jobConfig, quartzSchedulerExtension, actor);
//		});
//
//	}
}
