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

	requires javax.json;
	requires javax.jsonb;
//	requires java.json.bind;
	requires java.net.http;
	requires johnzon.jsonb;
	requires transitive java.logging;
	requires transitive jdk.httpserver;
}