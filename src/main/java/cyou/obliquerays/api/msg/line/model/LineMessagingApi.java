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

import java.util.ArrayList;
import java.util.List;

/**
 * Line Messaging APIの共通Object
 */
public class LineMessagingApi {
    ////Fields
    /**
     * 送信先
     */
    private String to;
    /**
     * 送信メッセージ
     */
    private final List<LineMessagingApiMessage> messages = new ArrayList<>(0);
    /**
     * 通知
     */
    private boolean notificationDisabled;

    //// Getters
    /**
     * @return 送信先
     */
    public String getTo() {
        return this.to;
    }
    /**
     * @return 送信メッセージ
     */
    public List<LineMessagingApiMessage> getMessages() {
        return this.messages;
    }
    /**
     * @return 通知
     */
    public boolean isNotificationDisabled() {
        return this.notificationDisabled;
    }

    //// Setters
    /**
     * @param _to 送信先
     */
    public void setTo(String _to) {
        this.to = _to;
    }
    /**
     * @param _messages 送信メッセージ
     */
    public void setMessages(List<LineMessagingApiMessage> _messages) {
        this.messages.clear();
        if (!_messages.isEmpty()) {
            this.messages.addAll(_messages);
        }
    }
    /**
     * @param _notificationDisabled 通知
     */
    public void setNotificationDisabled(boolean _notificationDisabled) {
        this.notificationDisabled = _notificationDisabled;
    }
}
