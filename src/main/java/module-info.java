/**
 *
 */
/**
 * @author takahiro
 *
 */
module message.api {
	exports cyou.obliquerays.logging;
	exports cyou.obliquerays.api.msg.line.api;
	exports cyou.obliquerays.api.msg.slack.model;
	exports cyou.obliquerays.api.msg.line.webhook;
	exports cyou.obliquerays.api.msg.line.model;
	exports cyou.obliquerays.api.msg.slack.webhook;
	exports cyou.obliquerays.api.msg;
	exports cyou.obliquerays.api.msg.slack.api;

	requires jakarta.json;
	requires jakarta.json.bind;
	requires org.eclipse.yasson;
	requires java.net.http;
	requires transitive java.logging;
	requires transitive jdk.httpserver;
}