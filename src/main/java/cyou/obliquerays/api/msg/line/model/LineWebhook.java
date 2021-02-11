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
 * Line Webhook イベントオブジェクト
 */
public class LineWebhook {

    /** Webhookイベントを受信すべきボットのユーザーID */
	private String destination;

    /** Webhookイベントのリスト */
	private final List<LineWebhookEvent> events = new ArrayList<>(0);

    //// Getters
    /**
     * @return Webhookイベントを受信すべきボットのユーザーID
     */
    public String getDestination() {
        return this.destination;
    }
    /**
     * @return Webhookイベントのリスト
     */
    public List<LineWebhookEvent> getEvents() {
        return this.events;
    }

    //// Setters
    /**
     * @param _destination Webhookイベントを受信すべきボットのユーザーID
     */
    public void setDestination(String _destination) {
        this.destination = _destination;
    }
    /**
     * @param _events Webhookイベントのリスト
     */
    public void setEvents(List<LineWebhookEvent> _events) {
        this.events.clear();
        if (!_events.isEmpty()) {
            this.events.addAll(_events);
        }
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Webhook [destination=").append(destination)
        .append(", events=").append(events).append("]");
        return builder.toString();
    }
}
