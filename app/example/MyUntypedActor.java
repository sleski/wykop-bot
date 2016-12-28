package example;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import play.Configuration;

import java.text.SimpleDateFormat;

/**
 * Created by Slawomir Leski <slawomir.leski@gmail.com> on 25.12.16.
 */
//TODO use extends AbstractLoggingActor instead of UntypedActor
public class MyUntypedActor extends UntypedActor {

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public static final String ACTOR_NAME = "MyUntypedActor";

	public void onReceive(Object message) throws Exception {

		log.info("Actor = {}, is running , {} .", ACTOR_NAME, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));

		System.out.println("Messages = " + message);

		if(message instanceof Configuration){
			System.out.println(((Configuration) message).getString("bot.key"));
		}

		if (message instanceof String) {
			log.info("Received String message: {}", message);
			getSender().tell(message, getSelf());
		} else
			unhandled(message);
	}
}
