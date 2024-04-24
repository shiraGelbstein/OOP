import java.util.*;

/**
 * Base file for the ChatterBot exercise.
 * The bot's replyTo method receives a statement.
 * If it starts with the constant REQUEST_PREFIX, the bot returns
 * whatever is after this prefix. Otherwise, it returns one of
 * a few possible replies as supplied to it via its constructor.
 * In this case, it may also include the statement after
 * the selected reply (coin toss).
 * @author Dan Nirel
 */
class ChatterBot {
	static final String REQUEST_PREFIX = "say ";
	static final String PLACEHOLDER_FOR_REQUESTED_PHRASE = "<phrase>";
	static final String PLACEHOLDER_FOR_ILLEGAL_REQUEST = "<request>";
	Random rand = new Random();
	String[] repliesToIllegalRequest;
	String[] legalRequestsReplies;
	String name;
	ChatterBot(String name, String[] repliesToLegalRequest, String[] repliesToIllegalRequest) {
		this.repliesToIllegalRequest = new String[repliesToIllegalRequest.length];
		for(int i = 0 ; i < repliesToIllegalRequest.length ; i = i+1) {
			this.repliesToIllegalRequest[i] = repliesToIllegalRequest[i];
		}
		this.legalRequestsReplies = new String[repliesToLegalRequest.length];
		for(int i = 0 ; i < repliesToLegalRequest.length ; i = i+1) {
			this.legalRequestsReplies[i] = repliesToLegalRequest[i];
		}
		this.name = name;
	}

	/**
	 * A function that "answers" to a statement. if it starts with the "request prefix" it gives a different
	 * answer - "legal" and "illegal".
	 * @param statement a string to answer on.
	 * @return an answer string.
	 */
	String replyTo(String statement) {
		if(statement.startsWith(REQUEST_PREFIX)) {
			//we don’t repeat the request prefix, so delete it from the reply
			String newstatement = statement.replaceFirst(REQUEST_PREFIX, "");
			return replyToLegalRequest(newstatement);
		}
		return replyToIllegalRequest(statement);
	}

	String replyToIllegalRequest(String statement) {
		return replacePlaceholderInARandomPattern
				(repliesToIllegalRequest,PLACEHOLDER_FOR_ILLEGAL_REQUEST,statement);
	}

	/**
	 * A getter.
	 * @return The name of the bot.
	 */
	String getName() {
		return this.name;
	}

	String replyToLegalRequest(String statement) {
        return replacePlaceholderInARandomPattern
				(legalRequestsReplies,PLACEHOLDER_FOR_REQUESTED_PHRASE,statement);
	}

	/**
	 * A function that randomly choosing a pattern from the given once and then replaces
	 * every word that is a placeholder to the given replacement.
	 * @param patterns the strings to randomly choose.
	 * @param placeholder A string that indicates what we should replace in the pattern
	 * @param replacement A string that indicates what we should change the replacement for in the pattern
	 * @return A pattern (string).
	 */
	String replacePlaceholderInARandomPattern(String[] patterns, String placeholder, String replacement)
	{
		int randomIndex = rand.nextInt(patterns.length);
		String responsePattern = patterns[randomIndex];
		String reply;
		reply = responsePattern.replaceAll(placeholder, replacement);
		return reply;
	}

}
