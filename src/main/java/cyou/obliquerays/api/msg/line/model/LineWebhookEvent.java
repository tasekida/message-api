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
package cyou.obliquerays.api.msg.line.model;

public class LineWebhookEvent {
    //// Fields
    /**
     *  イベントのタイプを表す識別子
     */
    private String type;
    /**
     *  チャネルの状態(active:チャネルがアクティブ、standby:チャネルが待機状態)
     */
    private String mode;
    /**
     *  イベントの発生時刻（ミリ秒）
     */
    private long timestamp;
    /**
     *  イベントの送信元情報を含むユーザー、グループ、またはトークルームオブジェクト
     */
    private LineWebhookSource source;
    /**
     *  イベントへの応答に使用するトークン
     */
    private String replyToken;
    /**
     *  メッセージの内容を含むオブジェクト
     */
    private LineWebhookMessage message;
    /**
     *  メンバー参加イベント
     */
    private LineWebhookMembers joined;
    /**
     *  メンバー退出イベント
     */
    private LineWebhookMembers members;
    /**
     *  ビーコンイベント
     */
    private LineWebhookBeacon lineWebhookBeacon;
    /**
     *  アカウント連携イベント
     */
    private LineWebhookAccountLink link;
    /**
     *  デバイス連携イベント
     */
    private LineWebhookThings things;

    //// Getters
    public String getType() {
        return this.type;
    }
    public String getMode() {
        return this.mode;
    }
    public long getTimestamp() {
        return this.timestamp;
    }
    public LineWebhookSource getSource() {
        return this.source;
    }
    public String getReplyToken() {
        return this.replyToken;
    }
    public LineWebhookMessage getMessage() {
        return this.message;
    }
    public LineWebhookMembers getJoined() {
        return this.joined;
    }
    public LineWebhookMembers getLeft() {
        return this.members;
    }
    public LineWebhookBeacon getBeacon() {
        return this.lineWebhookBeacon;
    }
    public LineWebhookAccountLink getLink() {
        return this.link;
    }
    public LineWebhookThings getThings() {
        return this.things;
    }

    //// Setters
    public void setType(String _type) {
        this.type = _type;
    }
    public void setMode(String _mode) {
        this.mode = _mode;
    }
    public void setTimestamp(long _timestamp) {
        this.timestamp = _timestamp;
    }
    public void setSource(LineWebhookSource _source) {
        this.source = _source;
    }
    public void setReplyToken(String _replyToken) {
        this.replyToken = _replyToken;
    }
    public void setMessage(LineWebhookMessage _message) {
        this.message = _message;
    }
    public void setJoined(LineWebhookMembers _joined) {
        this.joined = _joined;
    }
    public void setLeft(LineWebhookMembers _left) {
        this.members = _left;
    }
    public void setBeacon(LineWebhookBeacon _beacon) {
        this.lineWebhookBeacon = _beacon;
    }
    public void setLink(LineWebhookAccountLink _link) {
        this.link = _link;
    }
    public void setThings(LineWebhookThings _things) {
        this.things = _things;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Event [type=").append(type).append(", mode=").append(mode)
                .append(", timestamp=").append(timestamp).append(", source=").append(source)
                .append(", replyToken=").append(replyToken).append(", message=").append(message)
                .append(", joined=").append(joined).append(", left=").append(members)
                .append(", beacon=").append(lineWebhookBeacon).append(", link=").append(link)
                .append(", things=").append(things).append("]");
        return builder.toString();
    }
}