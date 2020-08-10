/**
 *  Copyright 2020 tasekida
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
package cyou.obliquerays.api.msg.slack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Slack Events API の Message Chennels Event Object
 * @see <a href="https://api.slack.com/events/message.channels">Slack Events API message channels event</a>
 */
public class SlackReceivingWebhook {
    //// Fields
    /**
     *  このコールバックをSlackからのものとしてアプリケーションに認証する共有プライベートコールバックトークン。
     *  これを、サブスクリプションの作成時に与えられたものと照合してください。
     *  一致しない場合、イベントを処理せずに破棄します。
     */
    private String token;
    /**
     * このイベントが発生したワークスペース/チームの一意の識別子。
     */
    private String team_id;
    /**
     * このイベントが対象とするアプリケーションの一意の識別子。
     * アプリケーションのIDは、アプリケーションコンソールのURLにあります。
     * リクエストURLが複数のアプリケーションを管理している場合は、
     * このフィールドをフィールドと一緒に使用して、token着信リクエストを検証およびルーティングします。
     */
    private String api_app_id;
    /**
     * 発生しているイベントを表すフィールドの内部セットが含まれています。
     */
    private SlackReceivingEvent event;
    /**
     * これは、受け取っているコールバックのタイプを反映しています。
     * 通常、それはevent_callbackです。
     * url_verification構成プロセス中に発生する可能性があります。
     * 「イベント」フィールドの「内部イベント」には、内部に潜むイベントタイプをtype示すフィールドも含まれます
     */
    private String type;
    /**
     * 文字列ベースのユーザーIDの配列。
     * コレクションの各メンバーは、アプリケーション/ボットをインストールしたユーザーを表し、
     * 説明されたイベントがそれらのユーザーに表示されることを示します。
     * ユーザーごとのメッセージではなく、ワークスペース内の複数のユーザーを対象とするデータの単一のイベントを受け取ります。
     */
    private final List<String> authed_users = new ArrayList<>(0);
    /**
     * この特定のイベントの一意の識別子。すべてのワークスペースでグローバルに一意です。
     */
    private String event_id;
    /**
     * このイベントがいつディスパッチされたかを示す秒単位のエポックタイムスタンプ。
     */
    private long event_time;

    //// Getters
    public String getToken() {
        return this.token;
    }
    public String getTeam_id() {
        return this.team_id;
    }
    public String getApi_app_id() {
        return this.api_app_id;
    }
    public SlackReceivingEvent getEvent() {
        return this.event;
    }
    public String getType() {
        return this.type;
    }
    public List<String> getAuthed_users() {
        return this.authed_users;
    }
    public String getEvent_id() {
        return this.event_id;
    }
    public long getEvent_time() {
        return this.event_time;
    }

    //// Setters
    public void setToken(String _token) {
        this.token = _token;
    }
    public void setTeam_id(String _team_id) {
        this.team_id = _team_id;
    }
    public void setApi_app_id(String _api_app_id) {
        this.api_app_id = _api_app_id;
    }
    public void setEvent(SlackReceivingEvent _event) {
        this.event = _event;
    }
    public void setType(String _type) {
        this.type = _type;
    }
    public void setAuthed_users(List<String> _authed_users) {
        this.authed_users.clear();
        if (!_authed_users.isEmpty()) {
            this.authed_users.addAll(_authed_users);
        }
    }
    public void setEvent_id(String _event_id) {
        this.event_id = _event_id;
    }
    public void setEvent_time(long _event_time) {
        this.event_time = _event_time;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SlackReceivingEvents [token=").append(token).append(", team_id=").append(team_id)
                .append(", api_app_id=").append(api_app_id).append(", event=").append(event)
                .append(", type=").append(type).append(", authed_users=").append(authed_users)
                .append(", event_id=").append(event_id).append(", event_time=").append(event_time).append("]");
        return builder.toString();
    }
}
