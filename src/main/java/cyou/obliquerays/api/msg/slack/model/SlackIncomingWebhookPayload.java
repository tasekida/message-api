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
 * Slack API の Message Payloads Object
 * @see <a href="https://api.slack.com/reference/messaging/payload">Slack API Message Payloads</a>
 */
public class SlackIncomingWebhookPayload {
    //// Fields
    /**
     *  TEXTメッセージ
     */
    private String text;
    /**
     *  Slack Incoming Webhook Payload BLOKオブジェクトの配列
     */
    private final List<Map<String, Object>> blocks = new ArrayList<>(0);
    /**
     *  Slack Incoming Webhook Payload Attachmentオブジェクトの配列
     */
    private final List<Map<String, Object>> attachments = new ArrayList<>(0);
    /**
     *  別スレッドに返信する時のメッセージID
     */
    private String threadTs;
    /**
     *  textがmrkdwnフォーマットの場合はtrue
     */
    private boolean mrkdwn;

    //// Getters
    public String getText() {
        return this.text;
    }
    public List<Map<String, Object>> getBlocks() {
        return this.blocks;
    }
    public List<Map<String, Object>> getAttachments() {
        return this.attachments;
    }
    public String getThreadTs() {
        return this.threadTs;
    }
    public boolean getMrkdwn() {
        return this.mrkdwn;
    }

    //// Setters
    public void setText(String _text) {
        this.text = _text;
    }
    public void setBlocks(List<Map<String, Object>> _blocks) {
        this.blocks.clear();
        if (!blocks.isEmpty()) {
            this.attachments.addAll(_blocks);
        }
    }
    public void setAttachments(List<Map<String, Object>> _attachments) {
        this.attachments.clear();
        if (!_attachments.isEmpty()) {
            this.attachments.addAll(_attachments);
        }
    }
    public void setThreadTs(String _threadTs) {
        this.threadTs = _threadTs;
    }
    public void setMrkdwn(boolean _mrkdwn) {
        this.mrkdwn = _mrkdwn;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SlackIncomingWebhookPayload [text=").append(text).append(", blocks=").append(blocks)
                .append(", attachments=").append(attachments).append(", threadTs=").append(threadTs)
                .append(", mrkdwn=").append(mrkdwn).append("]");
        return builder.toString();
    }
}
