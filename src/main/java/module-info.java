/**
 *  メッセージ交換用のモジュール
 */
module message.api {
	opens cyou.obliquerays.api.msg.slack.model;
	opens cyou.obliquerays.api.msg.line.model;

	exports cyou.obliquerays.logging;
	exports cyou.obliquerays.api.msg.line.api;
	exports cyou.obliquerays.api.msg.slack.model;
	exports cyou.obliquerays.api.msg.line.webhook;
	exports cyou.obliquerays.api.msg.line.model;
	exports cyou.obliquerays.api.msg.slack.webhook;
	exports cyou.obliquerays.api.msg;
	exports cyou.obliquerays.api.msg.slack.api;

	requires java.net.http;
	requires javax.json;
	requires javax.jsonb;
	requires transitive java.logging;
	requires transitive jdk.httpserver;
	requires transitive johnzon.jsonb;
}