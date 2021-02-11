/**
 *  Copyright 2021 tasekida
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
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