import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;

/**
 * The purpose of this program is for utilizing bot commands in a discord server
 * This is the driver class that contains method main.
 */
public class Bot implements EventListener{
	/**
	 * This variable stores the prefix for the bot commands
	 */
	public static final String PREFIX = "!";

	/**
	 * This is the main method which creates the discord API object and allows the bot to listen for commands
	 * @param args are the passed array of Strings to method meain
	 * @throws LoginException provides a detailed message upon failed login to discord
	 */
	public static void main(String[] args) throws LoginException {

		JDA api = JDABuilder.createDefault(Token.TOKEN).addEventListeners(new Bot()).build();
		api.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);

	
		api.addEventListener(new Commands());

	}

	/**
	 * This method runs on event
	 * @param event is the generic event passed to the method
	 */
	public void onEvent(GenericEvent event) {
		
	}
	

}
