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
import java.util.Map;

/**
 * Slack Events API の Message Chennels Event Object
 * @see <a href="https://api.slack.com/events/message.channels">Slack Events API message channels event</a>
 */
public class SlackReceivingEvent {
    //// Fields
    private String client_msg_id;
    /**
     * 隣接するフィールドで説明されるイベントの特定の名前。
     * このフィールドは、すべての内部イベントタイプに含まれています。
     */
    private String type;
    private String text;
    /**
     * イベントのタイムスタンプ。
     * 組み合わせevent_ts、team_id、user_id、またはchannel_id一意であることを意図しています。
     * このフィールドは、すべての内部イベントタイプに含まれています。
     */
    private String event_ts;
    /**
     * このアクションを引き起こしたユーザーに属するユーザーID 。
     * すべてのイベントがユーザーによって制御されるわけではないため、すべてのイベントには含まれません。
     * authed_usersユーザーごとにイベントの可視性を計算する必要がある場合は、最上位のコールバックオブジェクトを参照してください。
     */
    private String user;
    /**
     * イベントの説明のタイムスタンプ。
     * これは、で説明されているように、イベントがディスパッチされる少し前に発生する可能性がありますevent_ts。
     * 組み合わせts、team_id、user_id、またはchannel_id一意であることを意図しています。
     */
    private String ts;
    /**
     * 記述されている基になるオブジェクトタイプに固有のデータ。
     * 多くの場合、完全なオブジェクトの省略バージョンに遭遇します。
     * たとえば、ファイルオブジェクトが参照される場合、ファイルのIDのみが表示されます。
     * 詳細については、個々のイベントタイプを参照してください。
     */
    private String item;
    private String team;
    private String channel;
    private String channel_type;
    /**
     *  Slack Event API BLOCKオブジェクトの配列
     */
    private final List<Map<String, Object>> blocks = new ArrayList<>(0);

    //// Getters
    public String getType() {
        return this.type;
    }
    public String getEvent_ts() {
        return this.event_ts;
    }
    public String getUser() {
        return this.user;
    }
    public String getTs() {
        return this.ts;
    }
    public String getItem() {
        return this.item;
    }
    public String getClient_msg_id() {
        return this.client_msg_id;
    }
    public String getText() {
        return this.text;
    }
    public String getTeam() {
        return this.team;
    }
    public String getChannel() {
        return this.channel;
    }
    public String getChannel_type() {
        return this.channel_type;
    }
    public List<Map<String, Object>> getBlocks() {
        return this.blocks;
    }

    //// Setters
    public void setType(String _type) {
        this.type = _type;
    }
    public void setEvent_ts(String _event_ts) {
        this.event_ts = _event_ts;
    }
    public void setUser(String _user) {
        this.user = _user;
    }
    public void setTs(String _ts) {
        this.ts = _ts;
    }
    public void setItem(String _item) {
        this.item = _item;
    }
    public void setClient_msg_id(String _client_msg_id) {
        this.client_msg_id = _client_msg_id;
    }
    public void setText(String _text) {
        this.text = _text;
    }
    public void setTeam(String _team) {
        this.team = _team;
    }
    public void setChannel(String _channel) {
        this.channel = _channel;
    }
    public void setChannel_type(String _channel_type) {
        this.channel_type = _channel_type;
    }
    public void setBlocks(List<Map<String, Object>> _blocks) {
        this.blocks.clear();
        if (!_blocks.isEmpty()) {
            this.blocks.addAll(_blocks);
        }
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EventType [client_msg_id=").append(client_msg_id).append(", type=").append(type)
                .append(", text=").append(text).append(", event_ts=").append(event_ts)
                .append(", user=").append(user).append(", ts=").append(ts)
                .append(", item=").append(item).append(", team=").append(team)
                .append(", channel=").append(channel).append(", channel_type=").append(channel_type)
                .append(", blocks=").append(blocks).append("]");
        return builder.toString();
    }
}
